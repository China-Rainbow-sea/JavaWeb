package com.RainbowSea.servlet;


import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BServlet extends GenericServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        // 设置在浏览器页面显示的格式类型，必须在输出前设置
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        // 获取到GenericServlet父类适配器当中的 servletContext 对象
        ServletContext servletContext = super.getServletContext();
        writer.print("BServlet 下的 servletContext的值: " + servletContext);

    }
}


