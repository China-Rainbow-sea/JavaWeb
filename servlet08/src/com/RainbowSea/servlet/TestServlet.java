package com.RainbowSea.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 获取 客户端的IP地址
        String ip = request.getRemoteAddr();
        System.out.println("客户端的ip地址: " + ip);

        // 获取用户的请求方式
        String method = request.getMethod();
        System.out.println("用户的请求方式: " + method);

        // 获取webapp的根路径
        String contextPath = request.getContextPath();
        System.out.println("webapp的根路径: " + contextPath);

        // 获取请求的URI
        String requestURI = request.getRequestURI();
        System.out.println("请求的URI:" + requestURI);

        // 获取 Servlet path 路径
        String servletPath = request.getServletPath();
        System.out.println("Servlet 的路径: " + servletPath);



    }
}
