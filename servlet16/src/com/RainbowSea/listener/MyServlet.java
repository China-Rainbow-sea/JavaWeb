package com.RainbowSea.listener;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/test")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        // session 会话域对象是来自服务器的，所有是通过 request 请求获取到的
        // 该方法如果服务器中没有 session 会话域对象，则会自动创建
        HttpSession session = request.getSession();

        // 向 session 会话域当中添加数据
        session.setAttribute("test",001);

        // 向 session 会话域当中替换数据
        session.setAttribute("test",100);

        // session 会话域当中移除数据
        session.removeAttribute("test");
    }
}
