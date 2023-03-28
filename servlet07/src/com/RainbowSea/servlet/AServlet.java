package com.RainbowSea.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class AServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 获取系统当前时间:
        Date nowTime = new Date();

        // 向request 请求域当中绑定数据
        request.setAttribute("sysTime",nowTime);

        // 从request域当中取出绑定的数据.
        Object sysTime = request.getAttribute("sysTime");

        // 输出到浏览器上
        response.setContentType("text/html;charSet=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println("request域当中以获取的系统当前时间: " + sysTime);


        // 这样做可以吗?
        // 在AServlet当中new 一个BServlet对象,然后调用BServlet 对象的doGet()方法,把request 对象传过去
        // 注意:这个代码虽然可以实现功能,到那时Servlet 对象不能自己由程序员来new,自己new 的Servlet 的对象
        // 的生命周期不受Tomcat 服务器的管理.
        /*BServlet bServlet = new BServlet();
        bServlet.doGet(request,response);*/

        // 可以使用转发机制:
        // 执行了AServlet之后,跳转到BServlet ,(这个资源跳转可以使用转发机制来完成)
        // 第一步:获取到请求转发对象.
        // 相当于把 "/B" 这个路径包装到请求转发器当中,实际上是把下一跳转的资源路径告知给Tomcat服务器了.
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/b");

        // 第二步: 调用请求转发器RequestDispatcher的forward()方法,进行转发:
        // 转发的时候:这两个参数很重要,request和response都是要传递给下一个资源的
        requestDispatcher.forward(request,response);

        // 第一步和第二步可以联合在一起写
        //request.getRequestDispatcher("/B2").forward(request,response);

    }
}
