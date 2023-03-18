package com.RainbowSea.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class AServlet implements Servlet {

    // 无参构造器
    /*public AServlet() {
        System.out.println("AServlet 无参构造器方法执行");
    }*/

   /* public AServlet(String s) {
        // 我们知道，当我们手动编写了一个类中有参数的的构造器，其无参构造器就会消失。除非手动写出
    }*/

    // init 被翻译为初始化
    // init 方法只会被执行一次，基本上和 Servlet构造器的调用同时执行，在Servlet 对象第一次被创建只会执行
    // init 方法通常是完成初始化操作的。
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("AServlet is init method execute!");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    // service 方法：是处理用户请求的核心方法
    // 只要用户发送一次请求,service 方法必然会执行一次
    // 发送100次请求，service方法执行100次
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("AServlet is service method execute");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    // destroy （）方法也是只执行一次
    // Tomcat 服务器在销毁AServlet 对象之前会调用一次destroy 方法
    // destroy()方法在执行的时候，AServlet 对象的内存还没有被销毁，即将被销毁，因为destroy 是对象方法，需要通过对象调用
    // destroy 方法中可以编写销毁前的准备
    // 比如：服务器关闭的时候，AServelt 对象开启了一些资源，这些资源可能是流，也可能是数据库连接
    // 那么关闭服务器的时候，要关闭这些流，关闭这些数据库连接，那么这些关闭资源的代码旧可以写到destroy()
    @Override
    public void destroy() {
        System.out.println("AServlet is destroy method execute");
    }
}
