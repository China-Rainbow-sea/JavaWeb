package com.rainbowSea.testThreadLocal;

public class UserService {

    public UserDao userDao = new UserDao();

    public void save() {
        // 从大Map MyThreadLocal中获取Connection对象
        MyConnection myConnection = DBUtil.getConnection();
        System.out.println("UserService Connection :" + myConnection);

        userDao.insert();

    }
}
