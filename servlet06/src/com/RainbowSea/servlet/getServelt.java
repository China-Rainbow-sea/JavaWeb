package com.RainbowSea.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class getServelt extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {

        // 响应一些内容到浏览器上
        response.setContentType("text/html;charSet=UTF-8");  // 设置显示格式
        PrintWriter writer = response.getWriter();

        // 注意 println() 和 print()的区别：
        // println() 会从源码上进行换行，但是不用在页面当中换行。
        // print()源码上不会进行换行。
        writer.print("<!doctype html>");
        writer.print("<html>");
        writer.print("   <head>");
        writer.print("             <title>from get servlet </title>");
        writer.print("   </head>");
        writer.print("   <body>");
        writer.print("            <h1>from get servlet <h1>");
        writer.print("   </body>");
        writer.print("</html>");
    }
}
