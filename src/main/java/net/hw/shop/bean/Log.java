package net.hw.shop.bean;

import java.util.Date;

/**
 * Created by howard on 2017/5/5.
 */
public class Log {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 请求网址
     */
    private String url;
    /**
     * 创建时间
     */
    private Date time;
    /**
     * 操作描述
     */
    private String operation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", url='" + url + '\'' +
                ", time=" + time +
                ", operation='" + operation + '\'' +
                '}';
    }
}
