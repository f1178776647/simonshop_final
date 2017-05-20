package net.hw.shop.service;

import net.hw.shop.bean.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by howard on 2017/5/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestOrderService {
    @Autowired OrderService orderService;

    @Test
    public void testFindList() {
        Order order = new Order();
        order.setUsername("郑晓红");
        List<Order> orders = orderService.findList(order);
        for (Order o : orders) {
            System.out.println(o);
        }
    }

    @Test
    public void testDelete() {
        int count = orderService.delete(2);
        if (count > 0) {
            System.out.println("恭喜，删除订单成功！");
        } else {
            System.out.println("遗憾，删除订单失败！");
        }
    }
}
