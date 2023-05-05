package com.RainbowSea.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebFilter("/login")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
            ServletException {


        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse  response= (HttpServletResponse) resp;

        request.setCharacterEncoding("UTF-8");  // 设置获取到的请求信息的字符编码:

        // 获取到用户的请求信息

        String name = request.getParameter("user");
        String password = request.getParameter("password");


        // 过滤器:判断用户登录的账号和密码是否正确
        if ("admin".equals(name) && "123".equals(password)) {
            // 正确:放行
            // 表示:执行下一个 Filter(同一个 映射的路径,如果有下一个Filter 的话),没有就执行(同一个映射的路径的 Servlet )
            chain.doFilter(request,response);

        } else {
            // 跳转至登录失败的页面
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }
    }
}
