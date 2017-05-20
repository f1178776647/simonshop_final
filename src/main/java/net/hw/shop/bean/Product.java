package net.hw.shop.bean;

import java.util.Date;

/**
 * Created by howard on 2017/4/23.
 */
public class Product {
    /**
     * 商品标识符
     */
    private Integer id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品单价
     */
    private Double price;
    /**
     * 商品上架时间
     */
    private Date addTime;
    /**
     * 商品图片路径
     */
    private String image;
    /**
     * 商品所属类别
     */
    private Category category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", addTime=" + addTime +
                ", image='" + image + '\'' +
                ", category=" + category +
                '}';
    }
}
