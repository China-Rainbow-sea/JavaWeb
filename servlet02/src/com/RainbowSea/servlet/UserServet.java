package com.RainbowSea.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class UserServet extends GenericServlet {
    /**
     * @param request the <code>ServletRequest</code> object that contains the
     *            client's request
     * @param response the <code>ServletResponse</code> object that will contain the
     *            servlet's response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("UserServet is service excuete " + super.getServletConfig());
    }
}
