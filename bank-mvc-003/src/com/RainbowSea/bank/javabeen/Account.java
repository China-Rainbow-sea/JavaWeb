package com.RainbowSea.bank.javabeen;

import java.io.Serializable;
import java.util.Objects;


/**
 * 账户实体类，封装账户信息的
 * 一般是一张表一个。
 * pojo 对象
 * 有的人也会把这种专门封装数据的对象，称为："bean对象" (javabean对象，咖啡豆)
 * 有的人也会把这种专门封装数据的对象，称为领域模型对象，domain对象
 * 不同的程序员不同的习惯。
 */
public class Account implements Serializable {  // 这种普通的简单的对象被成为pojo对象
    // 注意我们这里定义的数据类型，使用引用数据类型
    // 因为我们数据库中可能存在 null 值，而基本数据类型是不可以存储 null值的

    private Long id = null;  // id
    private String actno;  // 账号
    private Double balance; // 余额

    // 反序列化
    private static final long serialVersionUID = 1L;

    public Account() {
    }


    public Account(Long id, String actno, Double balance) {
        this.id = id;
        this.actno = actno;
        this.balance = balance;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActno() {
        return actno;
    }

    public void setActno(String actno) {
        this.actno = actno;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(getId(), account.getId()) && Objects.equals(getActno(), account.getActno()) && Objects.equals(getBalance(), account.getBalance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getActno(), getBalance());
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", actno='" + actno + '\'' +
                ", balance=" + balance +
                '}';
    }
}
