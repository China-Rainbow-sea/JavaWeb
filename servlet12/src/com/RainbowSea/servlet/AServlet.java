package com.RainbowSea.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        // 重定向一个名为 index.html 的资源
        // request.getContextPath() 返回的是该webapp的项目的根路径：也就是/项目名，注意是带 “/”的，不要多写了
        response.sendRedirect(request.getContextPath()+"/index.html");


    }
}
