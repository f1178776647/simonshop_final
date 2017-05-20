package net.hw.shop.webmvc;

import net.hw.shop.bean.Order;
import net.hw.shop.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by howard on 2017/2/18.
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @RequestMapping("/makeOrder")
    public String makeOrder(@RequestParam("username") String username,
                            @RequestParam("telephone") String telephone,
                            @RequestParam("totalPrice") double totalPrice,
                            @RequestParam("deliveryAddress") String deliveryAddress,
                            Model model) {
        Order order = new Order();
        order.setUsername(username);
        order.setTelephone(telephone);
        order.setTotalPrice(totalPrice);
        order.setDeliveryAddress(deliveryAddress);
        order.setOrderTime(new Date());
        int count = orderService.save(order);
        if (count > 0) {
            List<Order> orders = orderService.findList(null);
            Order lastOrder = orders.get(orders.size()-1);
            model.addAttribute("lastOrder", lastOrder);
            return "frontend/showOrder";
        } else {
            model.addAttribute("orderMsg", "订单生成失败！");
            return "frontend/showProduct";
        }
    }

    @RequestMapping("/pay")
    public String pay(HttpSession session) {
        session.removeAttribute("shoppingTable");
        session.removeAttribute("totalPrice");
        return "index";
    }

    @RequestMapping("/toMakeOrder")
    public String toMakeOrder(@RequestParam("totalPrice") double totalPrice,
                              Model model) {
        if (totalPrice == 0.0) {
            model.addAttribute("orderMsg", "您还未购物呢！请您选购商品！");
            return "frontend/showProduct";
        } else {
            model.addAttribute("totalPrice", totalPrice);
            return "frontend/makeOrder";
        }
    }

    @RequestMapping("/listAll")
    public
    @ResponseBody
    List<Order> listAll() {
        List<Order> orders = orderService.findList(null);
        return orders;
    }
}
