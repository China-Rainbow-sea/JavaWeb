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
 * 部门详情
 */
public class DeptDetailServlet extends HttpServlet {

    // 前端超连接是 doGet()请求
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 设置前端浏览器格式类型和字符集编码
        response.setContentType("text/html;charSet=UTF-8");
        PrintWriter writer = response.getWriter();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //中文思路（思路来源于：你要做什么？目标：查看部门详细信息。）
        // 第一步：获取部门编号
        // 第二步：根据部门编号查询数据库，获取该部门编号对应的部门信息。
        // 第三步：将部门信息响应到浏览器上。（显示一个详情。）


        // 1.
        // http://127.0.0.1:8080/servlet09/dept/detail?deptno=40
        String deptno = request.getParameter("deptno");  // 注意是我们前端提交的数据，建议复制name

        try {
            // 2. 连接数据库，根据部门编号查询数据库
            // 1.注册驱动，连接数据库
            connection = DBUtil.getConnection();

            // 2. 预编译SQL语句,sql要测试
            String sql = "select dname,loc from dept where depton = ?";  // ? 占位符
            preparedStatement = connection.prepareStatement(sql);

            // 3. 填充占位符,真正执行sql语句
            preparedStatement.setString(1, deptno);
            resultSet = preparedStatement.executeQuery();

            // 4. 处理查询结果集
            while (resultSet.next()) {
                String dname = resultSet.getString("dname");
                String loc = resultSet.getString("loc");


                // 注意将 “双引号转换为单引号，因为在Java当中不可以嵌套多个双引号，除非是字符串的拼接
                // 所以使用 '单引号
                writer.println("    <body>");
                writer.println("  <h1>部门详情</h1>");
                writer.println("              部门编号: " + deptno + " <br>");
                writer.println("          部门名称: " + dname + "<br>");
                writer.println("     部门位置: " + loc + "<br>");
                writer.println("  <input type='button' value='后退' onclick='window.history.back()'  />");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 5. 释放资源
            DBUtil.close(connection, preparedStatement, resultSet);
        }


        writer.println("</body>");
        writer.println("</html>");
    }
}
