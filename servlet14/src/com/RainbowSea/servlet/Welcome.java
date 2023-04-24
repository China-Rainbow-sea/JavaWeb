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


/**
 * 注意一点就是；我们这里的免十天登录，是访问对应 oa/ 这个登录的欢迎页面，我们web.xml 当中，
 * 只有当oa/根项目的根路径的时候，才可以免10天登录的。其他是无法跳转到免登录的。
 */
@WebServlet("/welcome")
public class Welcome extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        boolean success = false;


        String userName = null;
        String userPasswrod = null;


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
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPasswrod);
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
            // 重定向到用户列表当中
            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
            // 跳转到登录页面 (重定向)
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }


    }
}
