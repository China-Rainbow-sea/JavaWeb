package com.RainbowSea.servlet;


import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class BServlet extends GenericServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = super.getServletContext();

        servletContext.log("你好世界");  // 添加日志信息

        // 达到某个条件，计入日志信息：异常
        int age = 17;
        if( age < 17) {
            servletContext.log("对不起，您未成年，请绕行",new RuntimeException("小屁孩，快走开，不适合你"));
        }
    }
}


