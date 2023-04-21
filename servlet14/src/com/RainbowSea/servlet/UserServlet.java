package com.RainbowSea.servlet;

import com.RainbowSea.DBUtil.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet({"/user/login", "/user/exit"})
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        // 获取到浏览器地址栏上的URL路径
        String servletPath = request.getServletPath();

        if ("/user/login".equals(servletPath)) {
            doLogin(request, response);
        } else if ("/user/exit".equals(servletPath)) {
            doExit(request, response);
        }


    }

    private void doExit(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        // 一个用户登录验证的方式：验证用户名和密码是否正确
        // 获取用户名和密码
        // 前端提交是数据是：username=111&password=fads
        // 注意：post 提交的数据是在请求体当中，而get提交的数据是在请求行当中

        boolean success = false;  // 标识登录成功

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String exempt = request.getParameter("exempt");

        // 连接数据库验证用户名和密码
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 1. 获取连接，注册驱动
            connection = DBUtil.getConnection();

            // 2. 获取操作数据对象，预编译sql语句, ? 占位符不要加，“”,'' 单双引号，成了字符串了，无法识别成占位符了。
            String sql = "select username,password from t_user where username = ? and password = ?";
            preparedStatement = connection.prepareStatement(sql);

            // 3. 填充占位符，真正执行sql语句
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            // 4. 处理查询结果集
            // 只有一条结果集
            if (resultSet.next()) {
                // 登录成功
                success = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 5. 关闭资源，最后使用的最先关闭，
            DBUtil.close(connection, preparedStatement, resultSet);
        }

        // 登录成功与否
        if (success) {
            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
            // 失败，跳转到失败页面
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }


    }
}
