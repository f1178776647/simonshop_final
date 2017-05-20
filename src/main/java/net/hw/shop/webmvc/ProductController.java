package net.hw.shop.webmvc;

import net.hw.shop.bean.Category;
import net.hw.shop.bean.Page;
import net.hw.shop.bean.Product;
import net.hw.shop.service.CategoryService;
import net.hw.shop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by howard on 2017/4/24.
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;
    @Resource
    private CategoryService categoryService;

    @RequestMapping("/toAddProduct")
    public String toAddProduct(HttpSession session) {
        List<Category> categories = categoryService.findList(null);
        session.setAttribute("categories", categories);
        return "backend/addProduct";
    }


    @RequestMapping("/addProduct")
    public String addCategory(@RequestParam("name") String name,
                              @RequestParam("price") double price,
                              @RequestParam("imageFile") MultipartFile file,
                              @RequestParam("category") int category,
                              Model model) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setAddTime(new Date());
        product.setCategory(categoryService.findById(category));
        // 获取上传文件原文件名
        String filename = file.getOriginalFilename();
        // 获取最后一件商品的id
        int lastId = productService.findList(null).get(productService.count(new Page<Product>()) - 1).getId();
        // 设置图片路径
        String image = "/images/product" + (lastId + 1) + ".jpg";
        product.setImage(image);
        // 上传图片到指定位置
        try {
            Files.copy(file.getInputStream(), Paths.get("src/main/resources/static", image));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int count = productService.save(product);
        Map<String, Object> map = new HashMap<String, Object>();
        if (count > 0) {
            model.addAttribute("productMsg", "恭喜，添加商品成功！");
        } else {
            model.addAttribute("productMsg", "遗憾，添加商品失败！");
        }
        return "backend/addProduct";
    }

    @RequestMapping("/showProduct")
    public String showProduct(@RequestParam("categoryId") int categoryId,
                              @RequestParam("currentPage") int currentPage,
                              HttpSession session, Page<Product> page, Model model) {
        String categoryName = "全部类别";
        if (categoryId > 0) {
            Map<String, Object> extra = new HashMap<String, Object>();
            extra.put("categoryId", categoryId);
            Page<Product> pageParam = new Page<Product>();
            pageParam.setExtra(extra);
            pageParam.setCurrentPage(currentPage);
            page = productService.findPage(pageParam);
            Category category = categoryService.findById(categoryId);
            categoryName = category.getName();
            if (page.getList().size() == 0) {
                model.addAttribute("productMsg", "[" + categoryName + "]类没有商品");
            }
        } else {
            Page<Product> pageParam = new Page<Product>();
            pageParam.setCurrentPage(currentPage);
            page = productService.findPage(pageParam);
        }
        if (page.getList().size() > 0) {
            session.setAttribute("categoryId", categoryId);
            session.setAttribute("categoryName", categoryName);
            session.setAttribute("page", page);
        }
        return "frontend/showProduct";
    }

    @RequestMapping("/operateCart")
    public String operateCart(
            @RequestParam("operation") String operation,
            @RequestParam("id") int id,
            HttpSession session, Page<Product> page) {

        // 从session里获取购物车（键：商品标识符；值：购买数量）
        LinkedHashMap<Integer, Integer> cart = (LinkedHashMap<Integer, Integer>) session.getAttribute("cart");
        // 判断购物车是否为空
        if (cart == null) {
            // 创建购物车
            cart = new LinkedHashMap<Integer, Integer>();
            // 将购物车保存到session里，便于用户在不同页面访问购物车
            session.setAttribute("cart", cart);
        }

        if (operation.equals("add")) {
            // 将商品添加到购物车
            if (cart.containsKey(id)) { // 该商品已购买过
                // 购买数量增加1
                cart.put(id, cart.get(id) + 1);
            } else { // 该商品未曾购买
                // 购买数量设置为1
                cart.put(id, 1);
            }
        } else if (operation.equals("delete")) {
            // 将商品从购物车删除
            if (cart.get(id) > 1) {
                // 购买数量减少1
                cart.put(id, cart.get(id) - 1);
            } else {
                // 从购物车里删除该商品
                cart.remove(id);
            }
        }

        // 获取该商品的类别标识符
        int categoryId = productService.findById(id).getCategory().getId();
        // 判断购物车是否为空
        if (cart != null) {
            // 定义购物表
            List<HashMap<String, Object>> shoppingTable = new ArrayList<HashMap<String, Object>>();
            // 购物总金额
            double totalPrice = 0.0;
            // 遍历购物车
            for (Integer p_id : cart.keySet()) {
                // 获取商品对象
                Product product = productService.findById(p_id);
                // 生成购物表记录
                HashMap<String, Object> shoppingItem = new HashMap<String, Object>();
                shoppingItem.put("id", product.getId());
                shoppingItem.put("name", product.getName());
                shoppingItem.put("price", product.getPrice());
                shoppingItem.put("amount", cart.get(p_id));
                shoppingItem.put("sum", product.getPrice() * cart.get(p_id));
                // 将购物表记录添加到购物表中
                shoppingTable.add(shoppingItem);
                // 累加购买总金额
                totalPrice = totalPrice + (Double) shoppingItem.get("sum");
            }

            // 将购物表和购买总金额保存到session里
            session.setAttribute("shoppingTable", shoppingTable);
            session.setAttribute("totalPrice", totalPrice);
        }

        Category category = categoryService.findById(categoryId);
        Map<String, Object> extra = new HashMap<String, Object>();
        extra.put("categoryId", categoryId);
        Page<Product> pageParam = new Page<Product>();
        pageParam.setExtra(extra);
        page = productService.findPage(pageParam);
        String categoryName = category.getName();

        session.setAttribute("categoryId", categoryId);
        session.setAttribute("categoryName", categoryName);
        session.setAttribute("page", page);

        return "frontend/showProduct";
    }

    @RequestMapping("/listAll")
    public
    @ResponseBody
    List<Product> listAll() {
        List<Product> products = productService.findList(null);
        return products;
    }
}
