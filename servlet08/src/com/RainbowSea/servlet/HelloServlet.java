package com.RainbowSea.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        // 设置在浏览器上显示的字符集格式以及，显示的格式。
        response.setContentType("text/html;charSet=utf-8");
        PrintWriter writer = response.getWriter();

        writer.println("<h1>你好世界，Hello World <h1>");



    }
}
