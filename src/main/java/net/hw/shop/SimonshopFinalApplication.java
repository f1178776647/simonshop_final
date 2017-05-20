package net.hw.shop;

import net.hw.shop.bean.Category;
import net.hw.shop.bean.Product;
import net.hw.shop.service.CategoryService;
import net.hw.shop.service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SpringBootApplication
public class SimonshopFinalApplication {
	@Resource
	private CategoryService categoryService;
	@Resource
	private ProductService productService;

	@RequestMapping("/")
	public String index(HttpSession session) {
		List<Category> categories = categoryService.findList(null);
		session.setAttribute("categories", categories);
		List<Product> products = productService.findList(null);
		session.setAttribute("products", products);
		return "index";
	}

	public static void main(String[] args) {
		SpringApplication.run(SimonshopFinalApplication.class, args);
	}
}
