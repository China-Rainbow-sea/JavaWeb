package com.RainbowSea.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/cookie02")
public class TestCookie2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        // 获取到该Cookie path 映射路径下的客户端发送过来的所有的Cookie 对象
        // 因为是客户端通过 request(请求)的方式发送过来的，所有需要使用 request 来获取信息
        Cookie[] cookies = request.getCookies();  // 如果客户端没有发送cookie信息过来，则返回null

        // 遍历Cookie 数组信息
        for (Cookie cookie : cookies) {
            String name = cookie.getName();  // 获取到 Cookie 当中的 name 值
            String value = cookie.getValue();  // 获取到Cookie 当中的value 值

            System.out.println(name + "-->" + value);

        }

        System.out.println("****************************************");

        // 可以通过数组下标的方式遍历:
        for (int i = 0; i < cookies.length; i++) {
            String name = cookies[i].getName();  // 获取到对于Cookie下标的name值
            String value = cookies[i].getValue(); // 获取到对于Cookie 下标的value值

            System.out.println(name + "==>" + value);
        }

    }
}
