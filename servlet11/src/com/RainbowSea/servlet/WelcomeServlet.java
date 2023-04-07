package com.RainbowSea.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;



/*
    String[] value() default {};
    String[] urlPatterns() default {};
     这里两个是表示的都是一样的，都是表示的是 web.xml 中的 url-pattern 可以设置多个。 注意同样是需要以 "/" 开始的
     注意: 当注解的属性是一个数组的，并且为该数组赋值的时候，仅仅赋一个值的话，大括号是可以审理的
 */
//@WebServlet(urlPatterns ="/welcome" )
//@WebServlet(value = "/welcome") 当注解的赋值中，只为一个属性赋值，并且该属性名为 value的时候，可以省略属性名
@WebServlet( "/welcome")
public class WelcomeServlet extends HelloServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charSet=UTF-8");
        PrintWriter writer = response.getWriter();

        writer.println("Hello  World");
    }
}
