package net.hw.shop.service;

import net.hw.shop.bean.Category;
import net.hw.shop.mapper.CategoryMapper;
import net.hw.shop.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by howard on 2017/5/7.
 */
@Service("categoryService")
@Transactional(readOnly = true)
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;

    /**
     * 保存类别
     *
     * @param category
     * @return
     */
    @Transactional(readOnly = false)
    public int save(Category category) {
        if (category.getId() == null) {
            return categoryMapper.insert(category);
        } else {
            return categoryMapper.update(category);
        }
    }

    /**
     * 删除类别
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = false)
    public int delete(int id) {
        return categoryMapper.delete(id);
    }

    /**
     * 按标识符查询类别
     *
     * @param id
     * @return
     */
    public Category findById(int id) {
        return categoryMapper.findById(id);
    }

    /**
     * 查询类别列表
     *
     * @param category
     * @return
     */
    public List<Category> findList(Category category) {
        return categoryMapper.findList(category);
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Transactional(readOnly = false)
    public boolean deleteBatch(String ids) {
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            int count = categoryMapper.delete(Integer.parseInt(id));
            if (count == 0) {
                return false;
            } else {
                productMapper.deleteByCategoryId(Integer.parseInt(id));
            }
        }
        return true;
    }
}
