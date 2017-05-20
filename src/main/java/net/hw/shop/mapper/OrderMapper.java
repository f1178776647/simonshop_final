package net.hw.shop.mapper;

import net.hw.shop.bean.Order;
import net.hw.shop.bean.Page;
import org.apache.ibatis.annotations.Mapper;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by howard on 2017/5/7.
 */
@Mapper
public interface OrderMapper {
    // 插入订单
    int insert(Order order);

    // 按标识符删除订单
    int delete(Integer id);

    // 更新订单
    int update(Order order);

    // 按标识符查询订单
    Order findById(Integer id);

    // 查询订单列表
    List<Order> findList(Order order);
}
