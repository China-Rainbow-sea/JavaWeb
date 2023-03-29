package com.RainbowSea.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RequestMethod extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType("text/html;charSet=utf-8");
        PrintWriter writer = response.getWriter();

        // 获取到客户端的IP地址
        String remoteAddr = request.getRemoteAddr();
        writer.println("客户端的IP地址: " + remoteAddr);

        // 设置请求体的字符集:
        /*
        注意：这里设置的是 post请求的字符集格式:
        如下这种方式并不能解决get请求的乱码问题。
        Tomcat 10 之后,request请求体当中的字符集默认就是UTF-8,不需要设置字符集，不会出现乱码问题，
        Tomcat 9 之前(包括9在内)，如果前端请求提交的是中文，后端获取之后出现乱码，怎么解决这个乱码哪？
         */
        //request.setCharacterEncoding("UTF-8");

        // 获取到用户提交的数据：
        // 这里我们直接在浏览器地址栏上输入: http://127.0.0.1:8080/servlet07/Method?username=zhangsan 内容
        // 上面的浏览器的输入重点是 ？ 分隔
       /* String username = request.getParameter("username");
        // 控制台输出
        System.out.println(username);*/

        // 这个方法使用比较多。（动态获取应用的根路径）就是我们Tomcat当中配置的项目名的
        String contextPath = request.getContextPath();
        System.out.println(contextPath);

        // 获取到该Servlet在web.xml 当中的映射路径
        String servletPath = request.getServletPath();
        System.out.println("web.xml 当中的映射路径: " + servletPath);  // /Method

        // 获取请求方式
        String method = request.getMethod();
        System.out.println(method);  // get

        // 获取请求的urI
        String requestURI = request.getRequestURI();
        System.out.println("uri：" + requestURI);  // /servlet07//Method

        // 获取请求的URL
        StringBuffer requestURL = request.getRequestURL();
        System.out.println("URL: " + requestURL);  //  http://127.0.0.1:8080/servlet07//Method

    }
}
