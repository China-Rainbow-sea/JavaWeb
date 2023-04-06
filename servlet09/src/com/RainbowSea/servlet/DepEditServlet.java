package com.RainbowSea.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DepEditServlet extends HttpServlet {
    // 超链接是 doGet()请求

    // http://127.0.0.1:8080/servlet09/dept/edit?deptno=10
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType("text/html;charSet=UTF-8");
        PrintWriter writer = response.getWriter();

        /*
        思路:
        获取到提交的过来的 部门编号
        根据部门编号修改信息，注意：部门编号是唯一的不要被修改了
        修改成功返回: 部门列表页面，
        修改失败返回: 失败页面:
         */

        String deptno = request.getParameter("deptno");

        // 连接数据库
        Connection connection = null;
        PreparedStatement preparedStatement = null;



    }
}
