package com.RainbowSea.been;

import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

import java.util.Objects;

public class User   {
    private String name ;
    private String password;

/*
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        // 将该 User 实现了 HttpSessionBindingListener 接口的 javabeen 添加到 session 会话域调用;
        System.out.println("Session  绑定 JavaBeen 数据");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        // 将该 User 实现了 HttpSessionBindingListener 接口的 javabeen的从 session 会话域当中移除被调用
        System.out.println("Session 解绑 JaveBeen 数据");
    }*/

    public User() {
    }


    public User(String name, String password) {
        this.name = name;
        this.password = password;
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
