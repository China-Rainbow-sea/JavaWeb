package com.RainbowSea.MyServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public interface Servlet {
    void init(ServletConfig var1);

    ServletConfig getServletConfig();

    void service(ServletRequest request, ServletResponse response);

    String getServletInfo();

    void destroy();
}
