package com.RainbowSea.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


/**
 * ServletContextListener 是用来监视 ServletContext 应用域的状态的监听器
 */

@WebListener
public class TestServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 现在这个特殊的时刻写代码，你写就是了，他会被服务器自动调用了
        // 这个方法是在ServletContext 对象被创建的时候调用
        System.out.println("ServletContext  应用域对象创建了");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //这个方法是 在 ServletContext 对象被销毁的时候调用的
        System.out.println("ServletContext 应用域对被销毁了");
    }
}
