package com.RainbowSea.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;


import java.io.IOException;



public class FilterB implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("FilterB doFilter() 执行了");

        // 表示执行后面的(映射路径相同的Filter 过滤器)，如果后面没有的话执行(映射路径相同的 Servlet)
        chain.doFilter(request,response);

    }
}
