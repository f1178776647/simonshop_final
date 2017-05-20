package net.hw.shop.bean;

/**
 * Created by howard on 2017/4/23.
 */
public class Category {
    /**
     * 类别标识符
     */
    private Integer id;
    /**
     * 类别名称
     */
    private String name;

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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
