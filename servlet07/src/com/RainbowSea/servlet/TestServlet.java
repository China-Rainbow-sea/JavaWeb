package com.RainbowSea.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 转发的下一个资源不一定是Servlet 资源，
        // 只要是Tomcat服务器当中合法的资源，都是可以转发的，例如: html...
        // 注意：转发的时候，路径的写法要注意，转发的路径以 “/” 开始，不加项目名
        // 默认是从项目的中的web目录开始的，如果是转发web的目录下的子目录的话，需要指定对应的子目录
        /*RequestDispatcher requestDispatcher = request.getRequestDispatcher("/test.html");
        requestDispatcher.forward(request,response);
        */
        // 如下是含有子目录的, / 表示 web目录
        request.getRequestDispatcher("/test/test2.html").forward(request,response);

    }
}
