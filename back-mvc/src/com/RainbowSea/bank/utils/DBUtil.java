package com.RainbowSea.bank.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtil {

    // resourceBundle 只能读取到 properties 后缀的文件，注意不要加文件后缀名
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources/jdbc");
    private static String driver = resourceBundle.getString("driver");
    private static String url = resourceBundle.getString("url");
    private static String user = resourceBundle.getString("user");
    private static String password = resourceBundle.getString("password");


    // DBUtil 类加载注册驱动
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
           e.printStackTrace();
        }

    }


    // 将构造器私有化，不让创建对象，因为工具类中的方法都是静态的，不需要创建对象
    // 为了防止创建对象，故将构造方法私有化
    private DBUtil() {

    }

    /**
     * 这里没有使用数据库连接池，直接创建连接对象
     */
    public static Connection getConnection()  {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  connection;
    }


    /**
     * 资源的关闭
     * 最后使用的最先关闭，逐个关闭，防止存在没有关闭的
     */
    public static void close(Connection connection , PreparedStatement preparedStatement, ResultSet resultSet) {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        if (preparedStatement!=null) {
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
