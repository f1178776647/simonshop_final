package net.hw.shop.service;

import net.hw.shop.bean.Page;
import net.hw.shop.bean.User;
import net.hw.shop.mapper.UserMapper;
import net.hw.shop.util.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by howard on 2017/5/7.
 */
@Service("userService")
@Transactional(readOnly = true)
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @Transactional(readOnly = false)
    public int save(User user) {
        user.setPassword(new Encrypt().SHA512(user.getPassword()));
        if (user.getId() == null) {
            return userMapper.insert(user);
        } else {
            return userMapper.update(user);
        }
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = false)
    public int delete(int id) {
        return userMapper.delete(id);
    }

    /**
     * 按标识符查询用户
     *
     * @param id
     * @return
     */
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    /**
     * 查询用户列表
     *
     * @param user
     * @return
     */
    public List<User> findList(User user) {
        return userMapper.findList(user);
    }

    /**
     * 分页查询用户
     *
     * @param page
     * @return
     */
    public Page<User> findPage(Page<User> page) {
        // 总记录数
        page.setTotalCount(userMapper.count(page));
        // 总页数
        page.setTotalPageCount((page.getTotalCount() - 1) / page.getPageSize() + 1);
        // 如果页码大于了总页数，直接显示最后一页
        if (page.getCurrentPage() < 1) {
            // 如果页码小于1，则显示首页
            page.setCurrentPage(1);
        } else if (page.getCurrentPage() > page.getTotalPageCount()) {
            page.setCurrentPage(page.getTotalPageCount());
        }
        // 从第几条记录开始查询
        page.setOffset((page.getCurrentPage() - 1) * page.getPageSize());
        page.setList(userMapper.findPage(page));
        return page;
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password) {
        return userMapper.login(username, new Encrypt().SHA512(password));
    }
}
