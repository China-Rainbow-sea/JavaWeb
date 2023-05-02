package com.RainbowSea.servlet.Filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;


import java.io.IOException;

//
//@WebFilter({"/A","/B"})

//@WebFilter("/*")  // 表示任何的 路径

//@WebFilter("*.test")   // 表示以test结尾的任何路径，注意 * 前面不要加 /
//@WebFilter("/test/*")  // 表示以 test 开始的路面

@WebFilter("/A")
public class Filter01 implements Filter {



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init()2方法执行");
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println(" Filter1 doFilter() begin");
        // 执行下一个过滤器，如果下一个不是过滤器了，则执行对应目标的 Servlet
        // 向下走，没有它是不行的。
        chain.doFilter(request,response);

        System.out.println("Filter1 doFilter() end");
    }

    @Override
    public void destroy() {
        System.out.println("destroy()2执行");
    }
}
