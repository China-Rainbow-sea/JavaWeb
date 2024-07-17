package com.rainbowsea.test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException {
        // 获取用户输入的用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 创建User对象并赋值
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        // 进行登录验证
        boolean isValid = validateUser(user);

        if (isValid) {
            // 登录成功，将用户信息存储到Session中
            request.getSession().setAttribute("user", user);
            response.sendRedirect("dashboard.jsp");
        } else {
            // 登录失败，返回登录页面并显示错误消息
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private boolean validateUser(User user) {
        // TODO: 验证用户的用户名和密码（忽略具体实现）
        return true;
    }
}
