package net.hw.shop.service;

import net.hw.shop.bean.Order;
import net.hw.shop.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by howard on 2017/5/7.
 */
@Service("orderService")
@Transactional(readOnly = true)
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 保存订单
     *
     * @param order
     * @return
     */
    @Transactional(readOnly = false)
    public int save(Order order) {
        if(order.getId() == null) {
            return orderMapper.insert(order);
        } else {
            return orderMapper.update(order);
        }
    }

    /**
     * 按标识符删除订单
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = false)
    public int delete(int id) {
        return orderMapper.delete(id);
    }


    /**
     * 按标识符查询订单
     *
     * @param id
     * @return
     */
    public Order findById(Integer id) {
        return orderMapper.findById(id);
    }

    /**
     * 查询订单列表
     *
     * @param order
     * @return
     */
    public List<Order> findList(Order order) {
        return orderMapper.findList(order);
    }
}
