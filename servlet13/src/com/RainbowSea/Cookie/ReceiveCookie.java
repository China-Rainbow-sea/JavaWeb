package com.RainbowSea.Cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/sendCookie")
public class ReceiveCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        // 通过java程序怎么接收到浏览器发送过来的cookie 信息呢
        // 当然通过request 对象了 (返回值是一个数组，因为浏览器可能会提交多个Cookie的服务器)
        // 注意一个细节：这个方法可能会返回null,如果浏览器没有提交 Cookie ,这个方法返回值是null,并不是返回一个长度为0的数组。
        // 源码上写明的。
        Cookie[] cookies = request.getCookies();

        // 所以只要 cookies 不为null ,就一定有cookies

        // 测试的时候注意：我们要先访问一个Servlet服务器，然它给我们创建好一个向对应上的Cookies 信息
        // 不然是没有cookies 信息携带的，注意一个知识点：就是我们的Cookies 的关联的路径信息的知识点:


        if(cookies != null) {
            // 遍历数组
            for(Cookie cookie : cookies) {
                // 获取cookiename和value
                String name = cookie.getName();
                String value = cookie.getValue();
                System.out.println(name + "=" + value);
            }
        }


    }
}
