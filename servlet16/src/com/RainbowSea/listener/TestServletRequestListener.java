package com.RainbowSea.listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;


/**
 * ServletRequestLister 是用来监听 Request 请求域对象的销毁和创建两种状态的。
 */

//@WebListener // 使用注解的方式,不要赋值，使用 默认值
public class TestServletRequestListener implements ServletRequestListener {

    // 该方法当 request 请求域对象要被销毁的时候，tomcat 服务器调用该方法
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("request 请求对象被销毁了");
    }

    // 该方法当 request 请求域对象创建的时候，Tomcat 服务器调用该方法
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("request 请求对象创建了");
    }
}
