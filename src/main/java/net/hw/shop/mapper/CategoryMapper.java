package net.hw.shop.mapper;

import net.hw.shop.bean.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by howard on 2017/5/7.
 */
@Mapper
public interface CategoryMapper {
    // 插入类别
    int insert(Category category);

    // 按标识符删除类别
    int delete(int id);

    // 更新类别
    int update(Category category);

    // 按标识符查询类别
    Category findById(Integer id);

    // 查询类别列表
    List<Category> findList(Category category);
}
