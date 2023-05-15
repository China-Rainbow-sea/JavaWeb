package com.rainbowSea.testThreadLocal;

public class UserDao {

    public void insert(){
        // 从大Map MyThreadLocal中获取Connection对象
        MyConnection myConnection = DBUtil.getConnection();
        System.out.println("UserDao Connection : " + myConnection);
    }
}
