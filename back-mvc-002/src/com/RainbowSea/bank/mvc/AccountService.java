package com.RainbowSea.bank.mvc;


import com.RainbowSea.bank.utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * service 翻译为：业务。
 * AccountService 专门处理Account业务的一个类
 * 在该类中应该编写纯业务代码。（只专注域业务处理，不写别的，不和其他代码混合在一块）
 * 只希望专注业务，能够将业务完美实现，少量bug.
 * <p>
 * 业务类一般起名：XXXService,XXXBiz...
 */
public class AccountService {

    // 这里的方法起名，一定要体现出，你要处理的是什么业务：
    // 我们要提供一个能够实现转账的业务的方法（一个业务对应一个方法）
    // 比如：UserService StudentService OrderService

    // 处理Account 转账业务的增删改查的Dao
    private AccountDao accountDao = new AccountDao();

    /**
     * 完成转账的业务逻辑
     *
     * @param fromActno 转出账号
     * @param toActno   转入账号
     * @param money     转账金额
     */
    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, AppException {

        Thread thread = Thread.currentThread();  // 获取当前线程
        System.out.println("seervice tranfer: " + thread);

        Connection connection = DBUtil.getConnection(); // 从ThreadLocal获取到的
        // service 层控制事务：
        // 事务的控制需要 Connection 对象
        try {
            // 开启事务
            connection.setAutoCommit(false);

            System.out.println("service transfer: "+connection);


            // 查询余额是否充足
            Account fromAct = accountDao.selectByActno(fromActno);
            if (fromAct.getBalance() < money) {
                throw new MoneyNotEnoughException("对不起，余额不足");
            }

            // 程序到这里说明余额充足
            Account toAct = accountDao.selectByActno(toActno);

            // 修改金额,先从内存上修改，再从硬盘上修改
            fromAct.setBalance(fromAct.getBalance() - money);
            toAct.setBalance(toAct.getBalance() + money);

            // 从硬盘数据库上修改
            int count = accountDao.update(fromAct);

            // 模拟异常
            /*String s = null;
            s.toString();*/


            count += accountDao.update(toAct);

            if(count != 2) {
                throw new AppException("账户转账异常，请联系管理员");
            }



            // 提交事务
            connection.commit();

        } catch (SQLException e) {
            // 事务的回滚
            // 因为我们这里是失败了，是不会提交数据的，数据库也就不会发生改变了。
            throw new AppException("账户异常，请联系管理员");
        } finally {
            // 关闭资源，移除ThreadLocal当中绑定的 Connection 对象
            DBUtil.close(connection,null,null);
        }


    }
}
