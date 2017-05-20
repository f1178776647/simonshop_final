package net.hw.shop.config;

import net.hw.shop.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by howard on 2017/4/28.
 */
@Configuration
@ComponentScan("net.hw.shop.webmvc")
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    /**
     * 添加视图控制器
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/toShowCart").setViewName("frontend/showCart");
        registry.addViewController("/user/toLogin").setViewName("frontend/login");
        registry.addViewController("/user/toRegister").setViewName("frontend/register");
        registry.addViewController("/todo").setViewName("backend/todo");
        registry.addViewController("/toMain").setViewName("backend/main");
        registry.addViewController("/user/viewUser").setViewName("backend/viewUser");
        registry.addViewController("/category/viewCategory").setViewName("backend/viewCategory");
        registry.addViewController("/category/editCategory").setViewName("backend/editCategory");
        registry.addViewController("/category/toAddCategory").setViewName("backend/addCategory");
        registry.addViewController("/category/toEditCategory").setViewName("backend/editCategory");
        registry.addViewController("/category/toDeleteCategory").setViewName("backend/deleteCategory");
        registry.addViewController("/product/viewProduct").setViewName("backend/viewProduct");
        registry.addViewController("/order/viewOrder").setViewName("backend/viewOrder");
        registry.addViewController("/log/viewLog").setViewName("backend/viewLog");
    }

    /**
     * 定义拦截器
     */
    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor());
    }
}
