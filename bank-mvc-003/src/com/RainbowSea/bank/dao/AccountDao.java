package com.RainbowSea.bank.dao;

import com.RainbowSea.bank.javabeen.Account;

import java.util.List;

public interface AccountDao {


    /**
     * 插入数据
     *
     * @param account
     * @return
     */
    public int insert(Account account);


    /**
     * 通过Id删除数据
     *
     * @param id
     * @return
     */
    public int deleteById(String id);

    /**
     * 更新数据
     *
     * @param account
     * @return
     */
    public int update(Account account);



    /**
     * 通过 actno 查找账户信息
     *
     * @param actno
     * @return
     */
    public Account selectByActno(String actno);

    /**
     * 查询所有的账户信息
     *
     * @return
     */
    public List<Account> selectAll();

}
