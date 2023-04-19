package com.RainbowSea.serssion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test/session")
public class TestSessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        // request 和 session 都是在服务端的java对象，都在JVM当中
        // request对象代表一次请求，(一次请求对应一个request对象，再次请求就会对应两个不同的request对象)
        // session对象代表一次会话，（一次会话对应一个session 对象）
        // 获取session，如何服务器当中没有 session 对象就会自动创建一个，
        HttpSession session = request.getSession();
        // 获取到服务器端的 session ，如果没有不会自动创建 session 对象
        //HttpSession session1 = request.getSession(false);

        //session.setAttribute(); 将数据存储到 session 会话当中。
        //session.getAttribute() 将数据从 session 会话当中取出




        // 将session 对象响应到浏览器端
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("会话对象：" + session);



    }
}
