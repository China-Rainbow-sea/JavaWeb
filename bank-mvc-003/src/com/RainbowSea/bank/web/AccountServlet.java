package com.RainbowSea.bank.web;

import com.RainbowSea.bank.exceptions.AppException;
import com.RainbowSea.bank.exceptions.MoneyNotEnoughException;
import com.RainbowSea.bank.service.AccountService;
import com.RainbowSea.bank.service.impl.AccountServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


/**
 * 账户小程序
 * AccountServlet 是一个司令官，他负责调度其他组件来完成任务。
 *
 */
@WebServlet("/transfer")
public class AccountServlet extends HttpServlet { // AccountServlet 作为一个 Controller 司令官

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        // 获取数据
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        double money = Double.parseDouble(request.getParameter("money"));

        // 调用业务方法处理业务（调度Model处理业务,其中是对应数据表的 CRUD操作）
        AccountService accountService = new AccountServiceImpl();  // 多态 父类的引用指向子类
        try {
            accountService.transfer(fromActno,toActno,money);
            // 执行到这里说明，成功了，
            // 展示处理结束(调度 View 做页面展示)

            response.sendRedirect(request.getContextPath()+"/success.jsp");
        } catch (MoneyNotEnoughException e) {
            // 执行到种类，说明失败了，（余额不足
            // 展示处理结束（调度 View 做页面展示）
            response.sendRedirect(request.getContextPath()+"/error.jsp");

        } catch (AppException e) {
            // 执行到种类，说明失败了，转账异常
            // 展示处理结束（调度 View 做页面展示）
            response.sendRedirect(request.getContextPath()+"/error.jsp");

        }

        // 页面的展示 （调度View做页面展示）


    }
}
