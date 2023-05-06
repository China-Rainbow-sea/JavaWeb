package com.RainbowSea.listener;

import com.RainbowSea.been.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/user")
public class TestHttpSessionBingingListener extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取到 session 应用域对象
        HttpSession session = request.getSession();
        User  user = new User("Jack","123");

        // 向 session 应用域当中添加  实现了 HttpSessionBindingListener 的 JavaBeen
        session.setAttribute("user",user);

        //  将 session 应用域当中数据移除, session 当中的 javabeen
        session.removeAttribute("user");


    }
}
