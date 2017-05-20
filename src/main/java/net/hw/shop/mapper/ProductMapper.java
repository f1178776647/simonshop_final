package net.hw.shop.mapper;

import net.hw.shop.bean.Page;
import net.hw.shop.bean.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by howard on 2017/5/7.
 */
@Mapper
public interface ProductMapper {
    // 插入商品
    int insert(Product product);

    // 删除商品
    int delete(Integer id);

    // 按类别删除商品
    int deleteByCategoryId(Integer categoryId);

    // 更新商品
    int update(Product product);

    // 按标识符查询商品
    Product findById(Integer id);

    // 查询记录数
    int count(Page<Product> page);

    // 查询商品列表
    List<Product> findList(Product product);

    // 分页查询商品
    List<Product> findPage(Page<Product> page);
}
