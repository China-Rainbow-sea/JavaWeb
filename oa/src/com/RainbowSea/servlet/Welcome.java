package com.RainbowSea.servlet;

import com.RainbowSea.DBUtil.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/welcome")
public class Welcome extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 获取到 客户端发送的 Cookie 信息
        Cookie[] cookies = request.getCookies();

        boolean success = false;

        // 注意如果为客户端没有发送 cookie 信息，返回的是 null ,不是数组为 0
        String userName = null;
        String userPasswrod = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // Cookie 是一个Map集合类似
                String name = cookie.getName();

                // 获取到Cookie 当中存储的用户名信息
                if ("username".equals(name)) {  // username 服务器创建 Cookie 是的 name key值
                    userName = cookie.getValue();
                } else if ("password".equals(name)) {
                    userPasswrod = cookie.getValue(); // 获取到 Cookie 当中存储用户名密码
                }
            }
        }


        // 判断从 Cookie 当中获取到用户名和密码是否正确
        // 首先判断是获取到该存储到 Cookie 当中的用户名和密码信息，
        // 注意可能用户根本就没有登录过，所以无法获取到是为 null的
        if (userName != null && userPasswrod != null) {
            // 验证用户名和密码是否正确 ,连接数据库
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                // 1. 注册数据库驱动，连接数据库
                connection = DBUtil.getConnection();
                // 2. 获取到操作数据库的对象，预编译SQL语句 sql测试
                // ？ 占位符，不要用 "" '' 单双引号括起来，不然就不是占位符了而是字符或字符串了
                String sql = "select * from t_user where username = ? and password = ?";
                preparedStatement = connection.prepareStatement(sql);

                // 3. 填充占位符 执行sql 语句
                preparedStatement.setString(1,userName);
                preparedStatement.setString(2,userPasswrod);
                resultSet = preparedStatement.executeQuery();

                // 4. 处理查询结果集
                if (resultSet.next()) {
                    // 登录成功
                    success = true;

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                // 5. 关闭资源：最后使用的最先关闭:
                DBUtil.close(connection, preparedStatement, resultSet);
            }

            if (success) {
                // 存储到cookie当中的用户名和密码正确
                // 获取session ，主要是为了，防止用户没有通过路径就直接访问了。
                HttpSession session = request.getSession();
                session.setAttribute("username", userName);
                // 重定向到用户列表当中
                response.sendRedirect(request.getContextPath() + "/dept/list");
            } else {   // Cookie 当中存储的用户名和密码错误
                // 跳转到登录页面 (重定向)
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }

        } else {  // 用户压根就没有登录过，所以Cookie 当中没有数据 更没有存储到用户名和用户的密码
            // 重定向
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }

    }
}
