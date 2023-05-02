package com.RainbowSea.servlet.Listener;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/test")
public class TestHttpSessionAttiobuteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 获取到session 会话
        HttpSession session = request.getSession();  // 没有自动创建

        // 向session 会话域当中添加数据
        session.setAttribute("user","zhangsan");


        // 修改 session 会话域当中的数据  session Map类似
        session.setAttribute("user","jack");


        // 删除 session 会话域当中的数据
        session.removeAttribute("user");
    }
}
