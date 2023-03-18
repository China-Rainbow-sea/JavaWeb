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
    // 成员变量
    private ServletConfig config;

    /**
     * init 方法中的ServileConfig 对象是小猫咪创建好的
     * 这个ServletConfig对象目前在init 方法的参数上，属于局部变量
     * 那么ServletConfig 对象肯定以后要在Service 方法中使用，怎么才能保证ServletConfig 对象在Service方法中能够使用呢？
     * 可以定义一个类的属性，再通过 init 方法的方式赋值上去
     * @param servletConfig
     *            a <code>ServletConfig</code> object containing the servlet's
     *            configuration and initialization parameters
     *
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("servletConfig 对象，小喵咪创建好的: " + servletConfig);
        // org.apache.catalina.core.StandardWrapperFacade@700fccbc

        this.config = servletConfig;
        // 调用给予子类重写的 init()方法
        this.init();
    }


    // 提供给子类重写，
    public void init(){

    }

    // 再通过getConfig 方法返回这个值，用于 service 方法中使用
    public ServletConfig getConfig() {
        return this.config;
    }


    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    // 适配器方法

    /**
     * 抽象方法：这个方法最常用，所以要求子类必须实现Service 方法。
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public abstract void service(ServletRequest request, ServletResponse response) throws ServletException,
            IOException ;

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }


}
