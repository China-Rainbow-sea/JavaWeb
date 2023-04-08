package com.RainbowSea.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

// 注意: urlPatterns 属性值以 "/" 开始
//@WebServlet(value = {"/test"})
// 可以省略为如下方式: 注解当只对名为 value属性名赋值，可以省略 value 属性名，数组如果只有一个值，可以省略{}
@WebServlet(value = "/test", initParams = {@WebInitParam(name = "user", value = "root"),
        @WebInitParam(name = "password", value = "11235813")}
        , loadOnStartup = 1) // 同样url 映射路径是以 "/" 开始的
public class TestWebServlet extends HttpServlet {

    public TestWebServlet() {
        System.out.println("TestWebServlet 的无参构造器执行");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 设置在浏览器端显示的格式类型，以及字符集编码
        response.setContentType("text/html;charSet=UTF-8");
        PrintWriter writer = response.getWriter();

        // 获取到该类当中 web.xml 中的 url-pattern 的值
        // 如果有多个的话，获取到的是你使用的那一个(在浏览器地址栏上显示的那一个url)
        String servletPath = request.getServletPath();
        writer.println("该类注解当中的urlPatterns也就是web.xml 中的 url-pattern的值:" + servletPath + "<br>");


        // 获取到初始化参数,对应的一个Servlet 标签当中的 <init-param> 标签当中设置
        String username = getInitParameter("user"); // 根据对应的配置的 name 获取到对应的 value 值
        writer.println("该类注解当中initParams的值也就是本Servlet当中的 配置对象的为 user的值: " + username + "<br>");

        String password = getInitParameter("password");
        writer.println("该类注解当中initParams的值也就是本Servlet当中的 配置对象的为 password的值: " + password + "<br>");


    }
}
