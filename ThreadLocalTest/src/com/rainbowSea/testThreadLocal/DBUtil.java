package com.rainbowSea.testThreadLocal;



public class DBUtil {

    // 静态变量特点：类加载时执行，并且只执行一次
    // 全局的大Map集合
    public static MyThreadLocal<MyConnection> local = new MyThreadLocal<MyConnection>();


    /**
     * 每一次都调用这个方法来获取Connection 对象
     */
    public static MyConnection getConnection() {
        // 从这个大的Map当中获取 Connection 对象
        MyConnection connection = local.get();

        // 如果是第一次：获取到的话这个 大MyThreadLocal 是没有存储到 Connection 对象的
        // 所有我们需要向 MyThreadLocal 添加上
        if (connection == null) {
            connection = new MyConnection();
            // 添加到 这个大Map当中
            local.set(connection);
        }

        // 返回从这个大Map当中获取到的Connection对象
        return connection;
    }

}
