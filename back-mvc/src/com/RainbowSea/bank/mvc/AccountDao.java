package com.RainbowSea.bank.mvc;


import com.RainbowSea.bank.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * AccountDao 是负责Account 数据的增上改查
 * <p>
 * 1. 什么是DAO ？
 * Data Access Object （数据访问对象）
 * 2. DAO实际上是一种设计模式，属于 JavaEE的设计模式之一，不是 23种设计模式
 * 3.DAO只负责数据库表的CRUD ,没有任何业务逻辑在里面
 * 4.没有任何业务逻辑，只负责表中数据增上改查的对象，有一个特俗的称谓：DAO对象
 * 5. 为什么叫做 AccountDao 呢？
 * 这是因为DAO是专门处理t_act 这张表的
 * 如果处理t_act 表的话，可以叫做：UserDao
 * 如果处理t-student表的话，可以叫做 StudentDao
 * <p>
 * int insert() ;
 * int deleteByActno();
 * int update() ;
 * Account selectByActno();
 * List<Account> selectAll();
 */
public class AccountDao {


    /**
     * 插入数据
     *
     * @param account
     * @return
     */
    public int insert(Account account) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            String sql = "insert into t_act(actno,balance) values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getActno());
            preparedStatement.setDouble(2, account.getBalance());
            count = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }


        return count;

    }


    /**
     * 通过Id删除数据
     *
     * @param id
     * @return
     */
    public int deleteById(String id) {
        Connection connection = DBUtil.getConnection();
        int count = 0;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "delete from t_act where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }

        return count;

    }


    /**
     * 更新数据
     *
     * @param account
     * @return
     */
    public int update(Account account) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;

        Thread thread = Thread.currentThread(); // 获取当前线程
        System.out.println("AccountDao Thread :" + thread);

        int count = 0;

        try {
            String sql = "update t_act set balance = ?, actno = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);

            //注意设置的 set类型要保持一致。
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setString(2, account.getActno());
            preparedStatement.setLong(3, account.getId());

            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }

        return count;
    }


    /**
     * 通过 actno 查找账户信息
     *
     * @param actno
     * @return
     */
    public Account selectByActno(String actno) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Account account = new Account();

        try {
            String sql = "select id,actno,balance from t_act where actno = ?";
            preparedStatement = connection.prepareStatement(sql);

            //注意设置的 set类型要保持一致。
            preparedStatement.setString(1, actno);

           resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Double balance = resultSet.getDouble("balance");
                // 将结果集封装到java 对象中
                account.setActno(actno);
                account.setId(id);
                account.setBalance(balance);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }

        return account;
    }


    /**
     * 查询所有的账户信息
     *
     * @return
     */
    public List<Account> selectAll() {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Account> list = null;

        try {
            String sql = "select id,actno,balance from t_act";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String actno = resultSet.getString("actno");
                Long id = resultSet.getLong("id");
                Double balance = resultSet.getDouble("balance");
                // 将结果集封装到java 对象中
                Account account = new Account(id,actno,balance);

                // 添加到List集合当中
                list.add(account);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }

        return list;
    }


}
