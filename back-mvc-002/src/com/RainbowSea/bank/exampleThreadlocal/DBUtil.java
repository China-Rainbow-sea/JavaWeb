package com.RainbowSea.bank.exampleThreadlocal;

public class DBUtil {

    // 静态变量特点：类加载时执行，并且只执行一次
    // 全局的大Map集合
    private static MyThreadLocal<Connection> local = new MyThreadLocal<Connection>();


    /**
     * 每一次都调用这个方法来获取Connection 对象
     */
    public static Connection getConnection() {
        Connection connection = local.get();  // 从 MyThreadLocal 这个大Map当中获取

        // 如果是第一次：获取到的话这个 大MyThreadLocal 是没有存储到 Connection 对象的
        // 所有我们需要向 MyThreadLocal 添加上
        if(connection == null) {
            connection = new Connection();
            local.set(connection);
        }
        return connection;
    }

}
