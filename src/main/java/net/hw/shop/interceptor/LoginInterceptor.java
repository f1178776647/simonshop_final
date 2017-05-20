package net.hw.shop.interceptor;

import net.hw.shop.bean.Category;
import net.hw.shop.bean.Product;
import net.hw.shop.service.CategoryService;
import net.hw.shop.service.ProductService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by howard on 2017/4/23.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private CategoryService categoryService;
    @Resource
    private ProductService productService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
        String url = request.getRequestURL().toString();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username == null) {
            if (url.contains("view") || url.contains("add")
                    || url.contains("edit") || url.contains("delete")
                    || url.contains("operateCart")) {
                List<Category> categories = categoryService.findList(null);
                session.setAttribute("categories", categories);
                List<Product> products = productService.findList(null);
                session.setAttribute("products", products);
                modelAndView.setViewName("frontend/login");
            }
        } else {
//            System.out.println("登录用户：" + username);
        }
    }
}
