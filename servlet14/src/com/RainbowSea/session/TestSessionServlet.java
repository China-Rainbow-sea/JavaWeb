package com.RainbowSea.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/session")
public class TestSessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        // 该访问获取到 session 对象，如果服务器端没有 session 对象会自动创建出  session 对象
        HttpSession session = request.getSession();


        // 获取到 session 对象，(参数为 false )表示：如果服务器当中没有 session 是不会自动创建的。
        HttpSession session1 = request.getSession(false);


        // 
        response.setContentType("text/html;charSet=UTF-8");
        PrintWriter out = response.getWriter();


        //
        out.println(" session对象:  " + session);

    }
}
