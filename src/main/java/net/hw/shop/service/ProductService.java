package net.hw.shop.service;

import jdk.management.resource.internal.TotalResourceContext;
import net.hw.shop.bean.Page;
import net.hw.shop.bean.Product;
import net.hw.shop.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by howard on 2017/5/7.
 */
@Service("productService")
@Transactional(readOnly = true)
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    /**
     * 保存商品
     *
     * @param product
     * @return
     */
    @Transactional(readOnly = false)
    public int save(Product product) {
        if (product.getId() == null) {
            return productMapper.insert(product);
        } else {
            return productMapper.update(product);
        }
    }

    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = false)
    public int delete(Integer id) {
        return productMapper.delete(id);
    }

    /**
     * 按类别删除商品
     *
     * @param categoryId
     * @return
     */
    public int delteByCategoryId(Integer categoryId) {
        return productMapper.deleteByCategoryId(categoryId);
    }

    /**
     * 按标识符查询商品
     *
     * @param id
     * @return
     */
    public Product findById(Integer id) {
        return productMapper.findById(id);
    }

    /**
     * 查询记录数
     *
     * @param page
     * @return
     */
    public int count(Page<Product> page) {
        return productMapper.count(page);
    }

    /**
     * 查询商品列表
     *
     * @param product
     * @return
     */
    public List<Product> findList(Product product) {
        return productMapper.findList(product);
    }

    /**
     * 分页查询商品
     *
     * @param page
     * @return
     */
    public Page<Product> findPage(Page<Product> page) {
        // 总记录数
        page.setTotalCount(productMapper.count(page));
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
        page.setList(productMapper.findPage(page));
        return page;
    }
}
