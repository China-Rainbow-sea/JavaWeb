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
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 修改部门
 */
public class DepEditServlet extends HttpServlet {
    // 超链接是 doGet()请求

    // http://127.0.0.1:8080/servlet09/dept/edit?deptno=10
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType("text/html;charSet=UTF-8");
        PrintWriter writer = response.getWriter();

        writer.println("     <!DOCTYPE html>");
        writer.println("<html lang='en'>");

        writer.println("<head>");
        writer.println("    <meta charset='UTF-8'>");
        writer.println("   <title>部门列表页面</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("    <h1>修改部门</h1>");


        writer.println("   <form action='"+request.getContextPath()+"/dept/modify' method='post'>");



        /*
        思路:
        获取到提交的过来的 部门编号
        根据部门编号修改信息，注意：部门编号是唯一的不要被修改了
        连接数据库，查询到相关信息显示到浏览器页面当中，方便用户修改
         */

        String deptno = request.getParameter("deptno");

        // 连接数据库
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 1. 注册驱动，连接数据库
            connection = DBUtil.getConnection();

            // 2. 获取到操作数据库对象，预编译SQL语句，sql测试
            String sql = "select dname,loc from dept where depton = ?";

            preparedStatement = connection.prepareStatement(sql);

            // 3. 填充占位符，真正执行sql语句
            preparedStatement.setString(1, deptno);
            resultSet = preparedStatement.executeQuery();

            // 4. 处理查询结果集
            while (resultSet.next()) {
                String dname = resultSet.getString("dname");  // 查询使用的别名，要用别名
                String loc = resultSet.getString("loc");

                // <!-- readonly 表示只读，不可修改的作用
                writer.println("      部门编号: <input type='text' name='deptno' value='" + deptno + "' readonly /><br>");
                writer.println("     部门名称: <input type='text' name='dname' value=" + dname + " /><br>");
                writer.println("    部门位置: <input type='text' name='loc' value=" + loc + " /><br>");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 5.释放资源，最后使用的优先关闭（因为如果是关闭优先使用的话，再最后面使用的可能需要前面的资源，才能执行）
            DBUtil.close(connection, preparedStatement, resultSet);
        }

        writer.println(" <input type='submit' value='修改' />");
        writer.println(" </form>");
        writer.println("</body>");
        writer.println("</html>");


    }
}
