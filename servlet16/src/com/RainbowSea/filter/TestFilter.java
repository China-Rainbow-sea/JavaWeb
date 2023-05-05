package com.RainbowSea.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;


@WebFilter("/a.do")
public class TestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("TestFilter 中的 doFilter() 方法 begin 开始执行了");

        request.setCharacterEncoding("UTF-8");  // 设置获取到的请求信息的字符编码:

        // 获取到用户的请求信息
        String name = request.getParameter("user");
        String passwrod = request.getParameter("password");


        // 过滤器:判断用户登录的账号和密码是否正确
        if ("admin".equals(name) && "123".equals(passwrod)) {
            // 正确:放行

            // 表示:执行下一个 Filter(同一个 映射的路径,如果有下一个Filter 的话),没有就执行(同一个映射的路径的 Servlet )
            chain.doFilter(request,response);

        }

        System.out.println("TestFilter 中的 doFilter() 方法 end 执行结束");
    }

}
