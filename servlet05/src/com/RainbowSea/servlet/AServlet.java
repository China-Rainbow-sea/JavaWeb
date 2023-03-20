package com.RainbowSea.servlet;


import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class AServlet extends GenericServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        // 设置在浏览器页面显示的格式类型，必须在输出前设置
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        // 获取到GenericServlet父类适配器当中的 servletContext 对象
        ServletContext servletContext = super.getServletContext();

        // 创建一个 User 实例对象
        User user = new User("Tom","123456");

        // 存
        servletContext.setAttribute("userObj",user); // map<K,V>
        // 取 注意参数是我们 ,setAttribute设置的 K 值
        //Object userObj = servletContext.getAttribute("userObj");
        // 因为我们这里知道我们存储的是什么类型的数据所以可以直接强制类型转换
        User userObj = (User) servletContext.getAttribute("userObj");

        writer.print(userObj); // 浏览器页面输出

    }

}
