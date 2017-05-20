package net.hw.shop.webmvc;

import jdk.nashorn.internal.runtime.JSONFunctions;
import net.hw.shop.bean.Category;
import net.hw.shop.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by howard on 2017/4/23.
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @RequestMapping("/addCategory")
    @ResponseBody
    public Map<String, Object> addCategory(@RequestParam("categoryName") String categoryName) {
        Category category = new Category();
        category.setName(categoryName);
        int count = categoryService.save(category);
        Map<String, Object> map = new HashMap<String, Object>();
        if (count > 0) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }

    @RequestMapping("/getCategoryName")
    @ResponseBody
    public Map<String, Object> getCategoryName(@RequestParam("categoryId") int categoryId) {
        Category category = new Category();
        category.setId(categoryId);
        String categoryName = categoryService.findList(category).get(0).getName();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("categoryName", categoryName);
        return map;
    }

    @RequestMapping("/listAll")
    public
    @ResponseBody
    List<Category> listAll() {
        List<Category> categories = categoryService.findList(null);
        return categories;
    }

    @RequestMapping("/editCategory")
    @ResponseBody
    public Map<String, Object> editCategory(@RequestParam("id") int id,
                                            @RequestParam("name") String name) {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        System.out.println(category);
        int count = categoryService.save(category);
        Map<String, Object> map = new HashMap<String, Object>();
        if (count > 0) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }

    @RequestMapping("/deleteCategory")
    public
    @ResponseBody
    Map<String, Object> deleteCategory(@RequestParam("ids") String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (categoryService.deleteBatch(ids)) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }
}
