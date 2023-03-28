package com.RainbowSea.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PostServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        // 响应一些内容到浏览器上
        response.setContentType("text/html;charSet=UTF-8");  // 设置显示格式
        PrintWriter writer = response.getWriter();

        writer.println("<!doctype html>");
        writer.println("<html>");
        writer.println("   <head>");
        writer.println("             <title>from post servlet </title>");
        writer.println("   </head>");
        writer.println("   <body>");
        writer.println("            <h1>from post servlet <h1>");
        writer.println("   </body>");
        writer.println("</html>");
    }
}
