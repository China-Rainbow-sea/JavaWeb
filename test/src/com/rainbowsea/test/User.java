package com.rainbowsea.test;

public class User {

    int id;
    String username;
    String password;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

//编程题：以上是User的实体类，请创建一个User对象，并给username和password赋值，用servlet3实现用户登录功能。(忽略数据库连接内容，该实体类对应数据库中user表)