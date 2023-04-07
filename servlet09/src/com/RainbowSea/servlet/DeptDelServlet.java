package com.RainbowSea.servlet;

import com.RainbowSea.DBUtil.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * 删除部门
 */
public class DeptDelServlet extends HttpServlet {


    /*
    注意前端是超链接的方式：是get请求
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType("text/html;charSet=UTF-8");
        PrintWriter writer = response.getWriter();
        request.setCharacterEncoding("UTF-8");

        // 思路:
        /*
        根据部门编号删除信息，
        删除成功，跳转回原来的部门列表页面
        删除失败，跳转删除失败的页面
         */

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        // 记录删除数据库的行数
        int count = 0;

        // 获取到前端提交的数据
        String deptno = request.getParameter("deptno");


        // 连接数据库进行删除操作

        try {
            // 1.注册驱动，连接数据库
            connection = DBUtil.getConnection();

            // 开启事务（取消自动提交机制）,实现可回滚
            connection.setAutoCommit(false);

            // 2. 预编译sql语句,sql测试
            String sql = "delete from dept where depton = ?"; // ? 占位符
            preparedStatement = connection.prepareStatement(sql);

            // 3. 填充占位符，真正的执行sql语句
            preparedStatement.setString(1, deptno);
            // 返回影响数据库的行数
            count = preparedStatement.executeUpdate();
            connection.commit();  // 手动提交数据
        } catch (SQLException e) {
            // 遇到异常回滚
            if (connection != null) {
                try {
                    // 事务的回滚
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(e);
        } finally {
            // 4. 释放资源
            // 因为这里是删除数据，没有查询操作，所以 没有 ResultSet 可以传null
            DBUtil.close(connection, preparedStatement, null);
        }

        if (count == 1) {
            // 删除成功
            // 仍然跳转到部门列表页面
            // 部门列表页面的显示需要执行另外一个Servlet，怎么办，可以使用跳转，不过这里最后是使用重定向
            // 注意：转发是在服务器间的，所以不要加“项目名” 而是 / + web.xml 映射的路径即可
            //request.getRequestDispatcher("/dept/list/").forward(request,response);

            // 优化：使用重定向机制 注意: 重定向是自发到前端的地址栏上的，前端所以需要指明项目名
            // 注意: request.getContextPath() 返回的根路径是，包含了 "/" 的
            response.sendRedirect(request.getContextPath() + "/dept/list/");
        } else {
            // 删除失败
            // web当中的 html资源，这里的 "/" 表示 web 目录
            //request.getRequestDispatcher("/error.html/").forward(request, response);

            // 优化，使用重定向
            response.sendRedirect(request.getContextPath() + "/error.html/");
        }


    }
}
