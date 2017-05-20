package net.hw.shop.webmvc;

import net.hw.shop.bean.Category;
import net.hw.shop.bean.Order;
import net.hw.shop.bean.Product;
import net.hw.shop.bean.User;
import net.hw.shop.service.CategoryService;
import net.hw.shop.service.OrderService;
import net.hw.shop.service.ProductService;
import net.hw.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.resources.Messages_pt_BR;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by howard on 2017/4/28.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private ProductService productService;
    @Resource
    private OrderService orderService;

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession session) {
        User user = userService.login(username, password);
        // 判断是否登录成功
        if (user != null) {
            session.setAttribute("username", username);
            // 判断用户的角色
            if (user.getPopedom() == 0) {
                return "backend/management";
            } else {
                List<Category> categories = categoryService.findList(null);
                session.setAttribute("categories", categories);
                return "index";
            }
        } else {
            model.addAttribute("loginMsg", "用户名或密码错误！");
            return "frontend/login";
        }
    }

    @RequestMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("telephone") String telephone,
                           Model model) {
        // 设置注册时间（时间戳对象）
        Timestamp registerTime = new Timestamp(System.currentTimeMillis());
        // 设置用户为普通用户
        int popedom = 1;
        // 创建用户对象
        User user = new User();
        // 设置用户对象信息
        user.setUsername(username);
        user.setPassword(password);
        user.setTelephone(telephone);
        user.setRegisterTime(registerTime);
        user.setPopedom(popedom);
        // 调用UserService对象添加用户方法
        int count = userService.save(user);
        // 判断是否注册成功
        if (count > 0) {
            model.addAttribute("registerMsg", "恭喜，注册成功！");
            // 映射到登录页面
            return "frontend/login";
        } else {
            model.addAttribute("registerMsg", "遗憾，注册失败！");
            // 映射到前台注册页面
            return "frontend/register";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("username");
        session.removeAttribute("cart");
        session.removeAttribute("shoppingTable");
        session.removeAttribute("totalPrice");
        List<Category> categories = categoryService.findList(null);
        session.setAttribute("categories", categories);
        List<Product> products = productService.findList(null);
        session.setAttribute("products", products);
        return "index";
    }

    @RequestMapping("/infoCenter")
    public String infoCenter(HttpSession session) {
        String username = (String) session.getAttribute("username");
        User user = new User();
        user.setUsername(username);
        List<User> users = userService.findList(user);
        session.setAttribute("user", users.get(0));

        Order order = new Order();
        order.setUsername(username);
        List<Order> orders = orderService.findList(order);
        session.setAttribute("orders", orders);

        return "frontend/infoCenter";
    }

    @RequestMapping("/changePassword")
    @ResponseBody
    public Map<String, Object> changePassword(@RequestParam("password") String password,
                                              HttpSession session) {
        String username = (String) session.getAttribute("username");
        User userParam = new User();
        userParam.setUsername(username);
        User user = userService.findList(userParam).get(0);
        user.setPassword(password);
        int count = userService.save(user);
        Map<String, Object> map = new HashMap<String, Object>();
        if (count > 0) {
            map.put("success", true);
        }
        return map;
    }

    @RequestMapping("/listAll")
    public
    @ResponseBody
    List<User> listAll() {
        List<User> users = userService.findList(null);
        return users;
    }
}
