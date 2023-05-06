package com.RainbowSea.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;


/**
 * HttpSessionAttributeListener 监视 session 会话域当中的数据的添加，移除，修改(替换) 事件的监听
 */
//@WebListener // 使用注解：使用注解当中的默认值，不需要赋值
public class TestHttpSessionAttributeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        // 向 session 会话域当中添加数据时，该方法被服务器调用，并执行了
        System.out.println("session add 添加数据了");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        // 向 session 会话域当中移除数据时，该方法被服务器调用，并执行了
        System.out.println("session removed 移除数据了");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        // 向 session 会话域当中的数据 被 替换时，该方法被服务器调用，并执行了
        System.out.println("session replaced 替换数据了");
    }
}
