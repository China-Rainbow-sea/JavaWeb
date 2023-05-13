package com.RainbowSea.bank.exampleThreadlocal;


public class Test {

    public static void main(String[] args) {

        // 创建Connection对象
        Connection connection = DBUtil.getConnection(); // 从大 MyThreadLocal 中获取
        Thread thread = Thread.currentThread(); // 获取到当前对象
        System.out.println("Test " + thread);
        System.out.println("Test: " + connection);


        // 调用service
        UserService userService = new UserService();
        userService.save();
    }
}
