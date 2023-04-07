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


/**
 * 更新数据
 */
public class DeptModifyServlet extends HttpServlet {

    // form 表单提交是 doPost 请求
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        /*
        思路:
        获取到提交的数据信息，
        根据提交的信息，连接数据库修改
        修改成功，跳转到部门列表页面，
        修改失败：跳转到失败的页面
         */

        request.setCharacterEncoding("UTF-8");  // 设置获取的的信息的编码集
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        // 影响数据库的行数
        int count = 0;


        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");


        try {
            // 1. 注册驱动，连接数据库
            connection = DBUtil.getConnection();

            // 2. 获取到操作数据库的对象，预编译sql语句，sql测试
            String sql = "update dept set dname = ?,loc = ? where depton = ?";
            preparedStatement = connection.prepareStatement(sql);

            // 3. 填充占位符，真正执行sql语句
            // 从下标 1开始
            preparedStatement.setString(1, dname);
            preparedStatement.setString(2, loc);
            preparedStatement.setString(3, deptno);

            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 4. 释放资源，最后使用的优先被释放
            DBUtil.close(connection, preparedStatement, null);
        }

        if (count == 1) {
            // 更新成功
            // 跳转到部门列表页面（部门列表表面是通过java程序动态生成的，所以还需要再次执行另一个Servlet）
            // 转发是服务器内部的操作，“/” 不要加项目名
            // request.getRequestDispatcher("/dept/list/").forward(request,response);

            // 优化使用重定向,自发前端(需要指明项目名)
            response.sendRedirect(request.getContextPath() + "/dept/list/");

        } else {
            // 更新失败
            // request.getRequestDispatcher("/error.html").forward(request,response);

            // 优化重定向
            // request.getContextPath()  获取的根路径是，带了 "/" 的
            response.sendRedirect(request.getContextPath() + "/error.html");
        }

    }
}
