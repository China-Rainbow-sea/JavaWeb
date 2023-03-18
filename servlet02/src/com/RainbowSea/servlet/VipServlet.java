package com.RainbowSea.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class VipServlet extends GenericServlet{
    /**
     * 抽象方法：这个方法最常用，所以要求子类必须实现Service 方法。
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("Vip可以享受更好的服务......");
    }
}
