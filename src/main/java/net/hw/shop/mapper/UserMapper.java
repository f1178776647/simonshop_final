package net.hw.shop.mapper;

import net.hw.shop.bean.Page;
import net.hw.shop.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by howard on 2017/5/7.
 */
@Mapper
public interface UserMapper {
    // 插入用户
    int insert(User user);

    // 删除用户
    int delete(Integer id);

    // 更新用户
    int update(User user);

    // 按标识符查询用户
    User findById(Integer id);

    // 查询用户列表
    List<User> findList(User user);

    // 分页查询用户
    List<User> findPage(Page<User> page);

    // 记录数
    int count(Page<User> page);

    // 用户登录
    User login(@Param("username") String username, @Param("password") String password);
}
