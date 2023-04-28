package com.RainbowSea.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/method")
public class CookieMethod extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        // 创建 cookie 对象，并封装信息
        Cookie cookie = new Cookie("test", "time");

        // 设置cookie 的有效时间为： 0 表示：删除同名的 cookie 信息，删除 cookie 信息，
        // 实际上表示 cookie 的有效时间为 1970年1月1日 0 时 0 分 10秒
        cookie.setMaxAge(-1);  // 设置有效时间： 单位是 s(秒)

        // 将cookie 的信息响应给客户端，客户端接受到以后，存储起来，这里cookie 设置的是 < 0 ,
        // 则客户端接受到的cookie 信息会存储到，浏览器运行内存当中，浏览器关闭 cookie 消失
        response.addCookie(cookie);


        // request.getContextPath() 返回项目的根路径，注意是: 该返回的路径是带有 "/" 的，所有不用多写 / 了
        cookie.setPath(request.getContextPath()); // 将项目的根路径作为 cookie 的path 映射/关联路径
        // 表示只要是这个web项目的请求路径，都会提交这个cookie给服务器

        cookie.setSecure(true);
    }
}
