package com.rainbowSea.testThreadLocal;

public class Test {
    public static void main(String[] args) {
        // 从大Map MyThreadLocal中获取Connection对象
        MyConnection myConnection = DBUtil.getConnection();
        System.out.println("Test Connection : " + myConnection);


        UserService userService = new UserService();
        userService.save();
    }
}
