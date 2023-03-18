package com.RainbowSea.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        // 设置response 在页面中的显示设置
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 连接数据库
            // 1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver"); // 执行其中的静态代码块

            // 2. 获取连接
            String url = "jdbc:mysql://localhost:3306/dbtest9";  // 连接各个位置上的数据库
            String user = "root";
            String password = "MySQL123";
            connection = DriverManager.getConnection(url, user, password);
            // 3. 获取预编译对象
            String sql = "select no,name from t_student";
            preparedStatement = connection.prepareStatement(sql);
            // 4. 执行预编译sql
            resultSet = preparedStatement.executeQuery();

            // 5. 处理查询结果集
            while(resultSet.next()) {
                String no = resultSet.getString("no");
                String name = resultSet.getString("name");
                out.print(no + "->" + name + "<br>");

            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 6. 关闭资源,最后使用的最先关闭
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
