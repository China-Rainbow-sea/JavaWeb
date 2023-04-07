package com.RainbowSea.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/*
name 对应 <servlet-name>
urlPattens 对应 <url-pattern> 注意: url-pattern 可以设置多个。 注意同样是需要以 "/" 开始的
initParams = {@WebInitParam(name="username",value="root") 表示的是 Servlet对象的配置信息对象的信息，
一个Servlet 就有一个 Servlet 配置对象的信息，封装在了 <servlet>标签当中的   <init-param> 标签当中设置
 */
@WebServlet(name="hello",urlPatterns = {"/hello1","/hello2","/hello3"} , loadOnStartup = 1,
initParams = {@WebInitParam(name="username",value="root"),@WebInitParam(name="password",value="123")})
public class HelloServlet extends HttpServlet {
    public HelloServlet() {
        System.out.println("无参构造方法");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType("text/html;charSet=UTF-8");
        PrintWriter writer = response.getWriter();

        // 获取到本Servlet Name
        String servletName = getServletName();

        writer.println("servlet name : " + servletName + "<br>");

        // 获取到该类当中 web.xml 中的 url-pattern 的值
        // 如果有多个的话，获取到的是你使用的那一个
        String servletPath = request.getServletPath();
        writer.println("该类中 web.xml 中的 url-pattern的值:" + servletPath + "<br>");

        // 获取到该webapps项目的项目名
        String contextPath = request.getContextPath();
        writer.println("该webapps项目的项目名: " + contextPath +"<br>" );

        // 获取到初始化参数,对应的一个Servlet 标签当中的 <init-param> 标签当中设置
        String username = getInitParameter("username"); // 根据对应的配置的 name 获取到对应的 valuue 值
        writer.println("本Servlet当中的 配置对象的为 username的值: " + username + "<br>");

        String password = getInitParameter("password");
        writer.println("本Servlet当中的 配置对象的为 password的值: " + password + "<br>");



    }
}
