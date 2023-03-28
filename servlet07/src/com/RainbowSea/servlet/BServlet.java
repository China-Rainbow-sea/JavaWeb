package com.RainbowSea.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        // 从request域当中取出绑定的数据.
        Object sysTime = request.getAttribute("sysTime");
        // 输出到浏览器上
        response.setContentType("text/html;charSet=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println("request域当中以获取的系统当前时间: " + sysTime);
    }
}
