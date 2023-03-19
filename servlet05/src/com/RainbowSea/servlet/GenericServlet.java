package com.RainbowSea.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;


/**
 * 编写一个标准通用的Servlet
 * 以后所有的Servlet 类都不要直接实现Servlet 接口了。
 * 以后所有的Servlet 类都继承 GenericServlet 类。
 * GenericServlet 就是一个适配器
 */
public abstract class GenericServlet implements Servlet {
    private ServletConfig config ;

    // 这样我们在我们的子类当中的 service()方法当中也可以使用 Tomcat 服务器创建的
    // ServletConfig 对象了
    @Override
    public final void init(ServletConfig config) throws ServletException {
        // 赋值依旧存在，config 不会为 null
        this.config = config;
        // 调用子类重写后的 init()方法
        this.init();
    }

    // 用于提供给子类重写
    public void init() {

    }

    public ServletConfig getConfig() {
        return config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 抽象方法：这个方法最常用，所以要求子类必须实现Service 方法。
     */
    @Override
    public abstract void service(ServletRequest request, ServletResponse response) throws ServletException, IOException;

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
