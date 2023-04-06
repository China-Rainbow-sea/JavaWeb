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

public class DeptSaveServlet extends HttpServlet {
    // 前端是注册信息，是post 请求
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        /*
        思路:
         获取到前端的提交的数据，注意 编码设置post 请求
         连接数据库: 进行添加数据
         添加成功: 返回部门列表页面
         添加失败: 返回失败的页面
         */

        request.setCharacterEncoding("UTF-8");

        // 获取到前端的数据，建议 name 使用复制
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        // 连接数据库，添加数据
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        // 影响数据库的行数
        int count = 0;

        try {
            // 1. 注册驱动，连接数据库
            connection = DBUtil.getConnection();

            // 2. 获取操作数据库对象，预编译sql语句，Sql测试
            String sql = "insert into dept(depton,dname,loc) values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            // 3. 填充占位符, 真正执行sql语句，
            // 注意： 占位符的填充是从 1 开始的，基本上数据库相关的起始下标索引都是从 1下标开始的
            preparedStatement.setString(1, deptno);
            preparedStatement.setString(2, dname);
            preparedStatement.setString(3, loc);

            // 返回影响数据库的行数
            count = preparedStatement.executeUpdate();

            // 5.释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }

        // 保存成功，返回部门列表页面
        if (count == 1) {
            // 这里应该使用，重定向
            // 这里用的转发，是服务器内部的，不要加项目名
            request.getRequestDispatcher("/dept/list/").forward(request, response);
        } else {
            // 保存失败
            // web当中的 html资源，这里的 "/" 表示 web 目录
            request.getRequestDispatcher("/error.html").forward(request, response);
        }


    }
}
