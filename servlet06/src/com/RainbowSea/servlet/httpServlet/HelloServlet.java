package com.RainbowSea.servlet.httpServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 通过如下代码分析：
 * 假设前端发送的是 get请求，后端程序员重写的方法是 doPost
 * 假设前端发送的是post请求，后端程序员重写的方法是 doGet
 * 会发生什么呢？
 *   发生405这样的一个错误，
 *   405表示前端的错误，发送的请求方式不对，和服务器不一致，不是服务器需要的请求方式。
 *   通过以上代码可以知道：只要HttpServlet类中的doGet()方法或doPost()方法执行了，必然405
 *
 *   怎么避免405错误呢：
 *   > 后端重写了doGet()方法，前端一定要发get请求
 *   > 后端重写了doPost()方法，前端一定要发post请求
 *   这样就可以避免405错误了。
 *
 *   这种前端到底需要什么样的请求，其实应该由后端说了算，后端让发什么方法，前端就得发什么方式。
 *
 *   有的人，你会看到为了避免405错误，在Servlet类中，将doGet()和d0Post()方法都进行了重写。
 *   这样确实可以避免405的发生，但是不建议，405错误还是有用的，该报错的时候就应该让它报错。
 *   如果你要是同时重写了doGet()和doPost()，哪你还不如直接重写 Servlet()方法好了，这样代码还能少写一点。
 *
 */
public class HelloServlet extends HttpServlet {

    // 没有提供init()方法，那么必然执行父类HttpServlet的init()方法。
    // HttpServlet类中没有init()方法，会继续执行GenericServlet类中的init()方法
    // 没有提供service()方法，那么必然执行父类HttpServlet类。



    //注意：我们编写的 HelloServlet 直接继承HttpServlet ，并直接重写了HttpServlet类中的
    // service()方法行吗？ 可以，只不过，你享受不到 405错误，享受不到HttP协议专属的东西。
    /*@Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,
            IOException {
        // 设置浏览器显示的格式:
        response.setContentType("text/html;setChar=UTF-8");
        PrintWriter writer = response.getWriter();

        writer.println("<h1>Hello World<h1>");
    }*/



    // 当前端发送的请求是get请求的时候，我们这里重写doGet()方法
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType("text/html;charSet=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<h1>doGet<h1>");

    }

    // 当前端发送的请求是 post 请求的时候，我们这里重写的doPost()方法

   /* @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType("text/html;charSet=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<h1>doPost<h1>");
    }*/
}
