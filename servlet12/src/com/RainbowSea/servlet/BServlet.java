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

        // 无法取出 AServlet 请求域当中的数据
        Object userObj = request.getAttribute("userObj");

        // 设置浏览器端显示响应的，格式类型，以及字符集
        response.setContentType("text/html;charSet=UTF-8");
        PrintWriter writer = response.getWriter();

        writer.println("从AServlet 获取的数据：" + userObj);

    }
}
