package com.RainbowSea.servlet.Listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;


/*
ServletContextListener 监听器要监听的是,ServletContext的对象的状态
 */
public class MyListener01 implements ServletContextListener {


    /**
     * 监听器中的方法不需要程序员手动调用，是发生某个特殊事件之后，被服务器调用。
     * @param sce Information about the ServletContext that was initialized
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 现在这个特殊的时刻写代码，你写就是了，他会被服务器自动调用了
        // 这个方法是在ServletContext 对象被创建的时候调用
        System.out.println("ServletContext 对象创建了");


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 这个方法是ServletContext 对象被销毁的时候执行
        System.out.println("ServletContext对象被销毁了");
    }
}
