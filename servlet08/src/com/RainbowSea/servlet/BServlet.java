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

        // 设置，在浏览器上响应的格式类型
        response.setContentType("text/html;charSet=utf-8");
        PrintWriter writer = response.getWriter();

        // 取出请求域当中的数据: 这里的name值与上面setAttribute(String name,Object obj) 保持一致。
        Object sysTime = request.getAttribute("sysTime");

        writer.println("sysTime = " + sysTime);  // 显示到浏览器页面当中的数据

    }
}
