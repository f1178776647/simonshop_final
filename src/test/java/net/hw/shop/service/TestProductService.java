package net.hw.shop.service;

import net.hw.shop.bean.Category;
import net.hw.shop.bean.Page;
import net.hw.shop.bean.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by howard on 2017/5/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProductService {
    @Autowired
    private ProductService productService;
    @Resource
    private CategoryService categoryService;

    @Test
    public void testFindList() {
        Category category = categoryService.findById(1);
        Product product = new Product();
        product.setCategory(category);
        List<Product> products = productService.findList(product);
        System.out.println("count: " + products.size());
        for (Product p : products) {
            System.err.println(p);
        }
    }

    @Test
    public void testFindPage() {
        Page<Product> pageParam = new Page<Product>();
        pageParam.setCurrentPage(1);
        Map<String, Object> extra = new HashMap<String, Object>();
        extra.put("categoryId", 2);
        pageParam.setExtra(extra);
        Page<Product> page = productService.findPage(pageParam);
        List<Product> products = page.getList();
        for (Product product : products) {
            System.err.println(product);
        }
    }

    @Test
    public void testFindById() {
        Product product = productService.findById(5);
        System.out.println(product);
    }

    @Test
    public void testCount() {
        int count = productService.count(new Page<Product>());
        System.out.println("count = " + count);
    }

    @Test
    public void testSave() {
        Product product = new Product();
        product.setName("哇哈哈矿泉水");
        Category category = new Category();
        category.setId(4);
        category.setName("休闲食品");
        product.setCategory(category);
        int count = productService.save(product);
        if (count > 0) {
            System.out.println("恭喜，保存商品成功！");
        } else {
            System.out.println("遗憾，保存商品失败！");
        }
    }
}
