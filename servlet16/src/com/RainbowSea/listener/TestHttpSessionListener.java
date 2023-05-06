package com.RainbowSea.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;


/**
 * HttpSessionListener 是用来监视 session 会话域对象的创建和销毁的状态的。
 */

// @WebListener // 使用注解配置，
public class TestHttpSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
       // 该方法 session 创建的时候，被Tomcat 服务器调用并执行
        System.out.println("session 会话域对象创建了");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session 会话域对象被销毁了");
    }
}
