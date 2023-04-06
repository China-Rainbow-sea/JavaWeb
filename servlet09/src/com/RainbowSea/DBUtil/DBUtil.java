package com.RainbowSea.DBUtil;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * JDBC工具类
 */
public class DBUtil {

    // 静态变量，在类加载时执行
    // 都是一些从 jdbc.properties 读取到的配置文件的信息
    // 该方法仅仅只会读取 “.properties" 的后缀的文件，注意：默认是从src目录开始的，有子目录需要写明子目录
    private static ResourceBundle bundle = ResourceBundle.getBundle("com/RainbowSea/resources/jdbc");
    private static String driver = bundle.getString("driver"); // 根据properties中的name读取对应的value值
    private static String url = bundle.getString("url");
    private static String user = bundle.getString("user");
    private static String password = bundle.getString("password");


    static {
        // 注册驱动(注册驱动只需要注册一次，放在静态代码当中，DBUtil类加载的时候执行)
        // "com.mysql.jdbc.Driver"是连接数据库的驱动，不能写死，因为以后可能还会连接Oracle数据库。
        // OCP开闭原则: 对扩展开放，对修改关闭（什么是符合 OCP呢？在进行功能扩展的时候，不需要修改java源代码）
        // Class.forName("com.mysql.jdbc.Driver")

        try {
            Class.forName(driver);  // 加载驱动
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * 获取数据库连接
     * @return Connection
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }


    /**
     * 关闭连接
     * @param connection
     * @param statement
     * @param resultSet
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        // 注意：分开try,最后使用的资源，优先关闭
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
