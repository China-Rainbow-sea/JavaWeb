package com.RainbowSea.bean;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

import java.util.Objects;

public class User implements HttpSessionBindingListener {

    private String name;
    private String password;


    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        // 用户登录了
        // Dept 类型的对象向session 中存放了
        // 获取到ServletContext 应用域对象，一个应用只要一个
        ServletContext application = event.getSession().getServletContext();

        Object onlinecount = application.getAttribute("onlinecount");

        // 用户第一次登录，一定是 onlinecout 是不存在的
        if(onlinecount == null) {
            // 向ServletContext 应用域对象，添加该用户，第一个用户的登录
            application.setAttribute("onlinecount", 1);
        } else {
            // 第二个用户的登录，就存在这个 onlinecount 应用域的对象信息了
            int count = (Integer) onlinecount;  // 将从 ServletContext 应用存储的信息用户的个数取出

            count++;
            application.setAttribute("onlinecount",count); // 修改，增加用户个数信息。
        }

    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        // 用户退出了
        // Dept 类型的对象从 session 对象中删除了。
        // 存储在 ServletContext中的 onlinecount key 对应的 value 用户个数 --

        // 1. 获取到存储 ServletContext中的用户个数
        ServletContext servletContext = event.getSession().getServletContext();
        Object onlinecount = servletContext.getAttribute("onlinecount");

        int count = (Integer) onlinecount;
        count --;

        servletContext.setAttribute("onlinecount",count);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getName(), user.getName()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
