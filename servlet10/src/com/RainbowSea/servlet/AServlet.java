package com.RainbowSea.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 创建一个用户对象
        User user = new User();
        user.setId("111");
        user.setName("张三");

        // 将用户对象存储到请求域当中
        request.setAttribute("userObj",user);

        // 转发机制，服务端，内部的操作，"/" 不要加项目名
        //request.getRequestDispatcher("/B").forward(request,response);
        // 转发的时候是一次请求，不管你转发了多少次，都是一次请求，
        // AServlet 转发到BServlet ,再转发到CServlet ，再转发到DServlet,不管服务器内部，到底
        // 转发了多少次，都在同一个request请求当中
        // 这是因为调用forward()方法的时候，会将当前的request和response对象传递给下一个Servlet
        // 这个跳转的动作操作是由Tomcat服务器内部完成的



        // 重定向： (重新定方向)
        //response.sendRedirect("/项目名/");
        // 重定向时的路径当中需要以项目名开始，或者说需要添加项目名的
        // 因为所谓的重定向是将，新的路径交给浏览器的地址栏上，然后自动执行的，而前端的信息获取是需要指明项目名的
        // response对将这个路径: /servlet10/B 响应给了浏览器了。
        // 浏览器又自发的向服务器发送了一个全新的请求: http://127.0.0.1:8080/servlet10/B
        // 所以浏览器一共发送了 "两次"请求:
        // 第一次请求: http://127.0.0.1:8080/servlet10/A
        // 第二次请求: http://127.0.0.1:8080/servlet10/B
        // 最终浏览器地址栏上显示的地址信息当然就是最后那一次请求的地址，所以重定向会导致浏览器
        // 地址栏上的地址发生改变。
        // 但是重定向是一次新的请求，是无法获取到请求域当中（只在一次请求中有效）信息的
        // 重定向操作是由：跳转到哪个资源，是由浏览器的地址栏说的算的。

        // 注意: request.getContextPath()返回的根路径是带有了 "/"了的
        response.sendRedirect(request.getContextPath()+"/B");

        // 转发机制 和 重定向机制如何选择;
        // 如果在上一个Servlet 当中向request请求域当中绑定了数据，希望从下一个Servlet当中把request请求域当中
        // 的数据取出（共享）来，使用转发机制，转发刷新的BUG
        // 剩下所有的请求均使用重定向。/
        // 重定向可以是其他资源：html，只要是服务器内部合法的资源就可以了。
    }
}
