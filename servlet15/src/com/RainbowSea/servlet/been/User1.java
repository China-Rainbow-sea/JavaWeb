package com.RainbowSea.servlet.been;

import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

import java.util.Objects;


/**
 * HttpSessionBindingListener 该监听器不需要使用 @WebListSteer 注解进行标注
 * 假设User类实现了该监听器，那么User对象在被放入session的时候，触发bind事件，
 * User对象从session 中删除的时候，触发unbind事件，假设Customer类没有实现该监听器，
 * 那么Customer 对象放入 session 或者从 session 删除的时候，不会触发bind和unbind事件。
 *
 * */
public class User1 implements HttpSessionBindingListener {

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("绑定数据");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("解除数据的绑定");
    }


    private String name;
    private String id;

    public User1() {
    }

    public User1(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User1)) return false;
        User1 user1 = (User1) o;
        return Objects.equals(name, user1.name) && Objects.equals(id, user1.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "User1{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
