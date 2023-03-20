package com.RainbowSea.servlet;

public class User {
    private String name;
    private String password;

    public User() {

    }

    public User(String name,String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
