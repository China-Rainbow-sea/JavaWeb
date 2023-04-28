package com.RainbowSea.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/cookie")
public class TestCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 创建cookie 对象，注意：Cookie 没有无参构造器，必须传参数，分别为: name, value
        Cookie cookie = new Cookie("test","01");
        Cookie cookie2 = new Cookie("test02","02");
        Cookie cookie3 = new Cookie("test03","03");

        // cookie 信息数据是从服务器产生的，服务器将cookie 信息响应给客户端,客户端存储到Cookie(缓存当中)
        response.addCookie(cookie);   // 将cookie 响应到浏览器的客户端
        response.addCookie(cookie2);  // 将cookie2 响应到浏览器客户端
        response.addCookie(cookie3);  // 将cookie3 响应到浏览器客户端

        cookie.setPath("/test1");  // 该 cookie 对象的 path 映射路径是 /test1,只有访问的是这个 /test1的以及子路径才会
                                   // 将该当前的 cookie 信息发送给服务器。其他路径不会将 cookie 发送给服务器
        cookie2.setPath("/test2");
        cookie3.setPath("/test3"); // 同理: 只有访问的路径为 /test3路径以及其子路径,才会将当前cookie3的信息发送给服务器

    }
}
