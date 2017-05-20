package net.hw.shop.bean;

import java.util.Date;

/**
 * Created by howard on 2017/4/23.
 */
public class User {
    /**
     * 用户标识符
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 电话号码
     */
    private String telephone;
    /**
     * 注册时间
     */
    private Date registerTime;
    /**
     * 权限（0：管理员；1：普通用户）
     */
    private Integer popedom;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getPopedom() {
        return popedom;
    }

    public void setPopedom(Integer popedom) {
        this.popedom = popedom;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", telephone='" + telephone + '\'' +
                ", registerTime=" + registerTime +
                ", popedom=" + popedom +
                '}';
    }
}
