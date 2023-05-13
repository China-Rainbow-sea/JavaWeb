package com.RainbowSea.bank.exampleThreadlocal;

public class UserDao {

    public void insert() {
        System.out.println("User Dao insert");

        Thread thread = Thread.currentThread();
        Connection connection = DBUtil.getConnection(); // 从 MyThreadLocal中获取
        System.out.println("UserDao " + connection);


        System.out.println("UserDao " + thread);
    }
}
