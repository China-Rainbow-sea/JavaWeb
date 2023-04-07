package com.RainbowSea.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class BServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 从请求域当中获取出存储的数据
        Object userObj = request.getAttribute("userObj");

        // 设置浏览器响应的格式类型和字符集
        response.setContentType("text/html;charSet=UTF-8");
        PrintWriter writer = response.getWriter();

        writer.println("请求域当中的用户对： " + userObj);

        writer.println(request.getContextPath());  //


    }
}
