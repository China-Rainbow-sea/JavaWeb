package com.RainbowSea.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RequestServletTest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        // 注意：这里直接指明对应的name 值，我们最好去前端编写的html当直接复制其中的name 值
        // 不然你可以会手动编写错误，哪怕只有其中的一个字母错误了，该方法都是无法获取到对应
        // 前端提交的value数据的。所以为了避免错误，建议直接复制。
        String username = request.getParameter("username2");  // 因为该name的value值只有一个
        System.out.println("username =" + username);
    }
}