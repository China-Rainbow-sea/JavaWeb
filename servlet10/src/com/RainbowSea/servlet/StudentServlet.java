package com.RainbowSea.servlet;

import com.RainbowSea.DBUtil.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        /*
        思路： 获取到前端的提交的数据
        连接数据库，操作数据库
         */
        // 获取到前端提交的数据
        request.setCharacterEncoding("UTF-8");
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        // 表示影响数据库的行数
        int count = 0;

        try {
            // 连接数据库
            connection = DBUtil.getConnection();

            // 注意： ? 不要加 '', ""单双引号，不然无法识别到该占位符的
            String sql = "INSERT INTO studnet (`no`,`name`) VALUES(?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,no);
            preparedStatement.setString(2,name);

            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            DBUtil.close(connection,preparedStatement,null);
        }

        if(count == 1) {
            // 添加成功
            // 先用 转发机制 ,转发服务器内部，不要加项目名
            request.getRequestDispatcher("/succeed.html").forward(request,response);
        } else {
            request.getRequestDispatcher("/error.html").forward(request,response);
        }
    }
}
