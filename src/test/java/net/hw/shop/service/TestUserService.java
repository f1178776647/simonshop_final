package net.hw.shop.service;

import net.hw.shop.SimonshopFinalApplication;
import net.hw.shop.bean.Page;
import net.hw.shop.bean.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by howard on 2017/5/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService {
    @Autowired
    private UserService userService;

    @Test
    public void testLogin() {
        String username = "admin";
        String password = "12345";
        User user = userService.login(username, password);
        if (user != null) {
            System.out.println("恭喜，登录成功！");
        } else {
            System.out.println("遗憾，登录失败！");
        }
    }

    @Test
    public void testFindList() {
        List<User> users = userService.findList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindById() {
        User user = userService.findById(2);
        System.out.println(user);
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setId(5);
        user.setUsername("艾文碧");
        user.setPassword("34567");
        user.setTelephone("12345678");

        int count = userService.save(user);
        if (count > 0) {
            System.out.println("恭喜，保存用户成功！");
        } else {
            System.out.println("遗憾，保存用户失败！");
        }
    }

    @Test
    public void testDelete() {
        int count = userService.delete(5);
        if (count > 0) {
            System.out.println("恭喜，删除用户成功！");
        } else {
            System.out.println("遗憾，删除用户失败！");
        }
    }

    @Test
    public void testFindPage() {
        Page<User> pageParam = new Page<User>();
        Page<User> page = userService.findPage(pageParam);
        List<User> users = page.getList();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
