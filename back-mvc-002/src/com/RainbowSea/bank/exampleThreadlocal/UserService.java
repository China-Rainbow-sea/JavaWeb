package com.RainbowSea.bank.exampleThreadlocal;

public class UserService {

    private UserDao userDao = new UserDao();

    public void save() {
        userDao.insert();

        Thread thread = Thread.currentThread();
        System.out.println("UserService " + thread);
        Connection connection = DBUtil.getConnection();
        System.out.println("UserService :" + connection);

    }
}
