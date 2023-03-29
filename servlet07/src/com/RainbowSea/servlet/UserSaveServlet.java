package com.RainbowSea.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserSaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 设置请求体的字符串
        request.setCharacterEncoding("UTF-8");

        // 获取用户提交的用户名
        String username = request.getParameter("username");

        // 输出username
        System.out.println(username);

        response.setContentType("text/html;charSet=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println("大家好，我是一名Java程序员");

    }
}
