package com.RainbowSea.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ConfigTestServlet2 extends GenericServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        // 设置在Html页面中显示的格式
        response.setContentType("text/html;charset=utf-8");
        // 获取到输出到页面中的对象
        PrintWriter out = response.getWriter();


        ServletConfig config = super.getServletConfig();
        // ServletConfig对象: org.apache.catalina.core.StandardWrapperFacade@66d383df
        out.println("ServletConfig对象: " + super.getServletConfig());

        // 获取到该ConfigTestServlet 的 Servlet 对象中的 wed.xml中的<init-param>标签当中的配置信息
        String value = config.getInitParameter("key");
        out.print("<br>" + value);


    }
}
