package com.RainbowSea.web.servlet;


import com.RainbowSea.web.exception.AppException;
import com.RainbowSea.web.exception.MoneyNotEnoughException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 在不使用 MVC 架构模式的前提下，完成银行账户的转账
 * 分析这个程序存在的问题
 * 分析以下： AccountTransferServlet 他都负责了什么？
 * 缺点: 1> 代码的复用性差，(代码的重用性太差了)
 *
 * 导致缺1的原因：
 *  > 因为没有进行“职能分工” 没有独立组件的概念，所哟没有办法进行代码复用。
 *  代码和代码之间的耦合度太高，扩展力太差。
 *
 *  缺点2: 耦合度高，导致了代码很难扩展
 *缺点3：操作数据库大代码和业务逻辑处混杂在一起，很容易出错，编写代码
 * 的时候很容易出错，无法专注业务逻辑的编写。
 *
 * 1> 负责了数据接收
 * 2> 负责了核心的业务处理
 * 3> 负责了数据库表中数据的CRUD操作(Creat(增)，Retrieve Update Delete)
 * 4> 负责了页面的数据展示
 *
 * 公司中：一般都有很多员工，每个员工都各司其职，为什么要这样？
 * 保洁阿姨负责打扫卫生，
 * 杜老师负责教学大纲的指定。
 * 郭老师负责上课
 * 王老师负责学生就业。
 *
 *
 * 假如：我们公司只有一个员工，这个员工负责所有的事情，生病了，-->公司倒闭了。
 *
 */

@WebServlet("/transfer")
public class AccountTransferServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        response.setContentType("text/html;charSet=UTF-8");
        PrintWriter out = response.getWriter();


        // 获取转账的相关信息
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        // Double.parseDouble 将字符串转换为 Double 类型
        double money = Double.parseDouble(request.getParameter("money"));


        // 编写转账的业务逻辑代码，连接数据苦，进行转账操作
        Connection connection = null;
        PreparedStatement p1 = null;
        PreparedStatement p2 = null;
        PreparedStatement p3 = null;
        ResultSet resultSet = null;

        int count = 0;

        try {
            // 1. 转账之前要判断账户的余额是否充足
            // 注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 获取连接
            String url = "jdbc:mysql://localhost:3306/mvc";
            String user = "root";
            String password = "MySQL123";
            connection = DriverManager.getConnection(url, user, password);

            // 开启事务
            connection.setAutoCommit(false);

            // 获取操作数据库对象
            String sql1 = "select balance from t_act where actno = ?";
            p1 = connection.prepareStatement(sql1);

            // 填充占位符，执行sql语句，
            p1.setString(1,fromActno);
            resultSet = p1.executeQuery();


            // 处理查询结果集
            if(resultSet.next()) {
                Double balance = Double.parseDouble(resultSet.getString("balance"));

                if(balance < money) {
                    // 余额不足异常 （使用异常机制）
                    throw new MoneyNotEnoughException("对不起，余额不足");

                }

                // 程序能够执行导这里，说明余额充足，
                //  开始转账
                // act001 账户减去 10000
                // act002 账户增加 10000

                String sql2 = "update t_act set balance = balance - ? where actno = ?";
                // 通过prepareStatement 修改其中的 setDouble 是从内存当中修改的
                p2 = connection.prepareStatement(sql2);
                p2.setDouble(1,money);
                p2.setString(2,fromActno);
                count = p2.executeUpdate();

                String sql3 = "update t_act set balance = balance + ? where actno = ?";
                p3 = connection.prepareStatement(sql3);
                p3.setDouble(1,money);
                p3.setString(2,toActno);

                // 注意：是影响数据库的叠加，计数
                count += p3.executeUpdate();

                String test = null;
                System.out.println(test.toString());

                if (count != 2) {
                    // 转账失败
                    throw new AppException("App 异常，请联系管理员");
                }

                connection.commit(); // 手动提交事务

                // 转账成功
                out.println("转账成功");

            }
        } catch (Exception e) {
            // 异常处理，发生异常之后，你准备怎么做
            out.println(e.getMessage());
            try {

                if (connection != null) {
                    connection.rollback();  // 事务的回滚
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            // 关闭资源,最后使用去最先关闭，逐个关闭
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (p1 != null) {
                try {
                    p1.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (p2 != null) {
                try {
                    p2.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (p3 != null) {
                try {
                    p3.close();
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
}
