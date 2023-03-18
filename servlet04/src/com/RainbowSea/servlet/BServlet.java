package com.RainbowSea.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 通过ServletContext对象也是可以记录日志信息的
 * public void log(String message)
 * public void log(String message,Throwable t)
 */
public class BServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        // 设置在html显示的文本格式
        response.setContentType("text/html;charset=utf-8");
        // 获取输出对象
        PrintWriter out = response.getWriter();

        // 获取到
        ServletContext application = super.getServletContext();
        // ServletContext: org.apache.catalina.core.ApplicationContextFacade@51c3b2bc
        out.print("ServletContext: " + application);


        //log
        // 这个日志会自动记录到哪里?
        // "C:\Users\huo\AppData\Local\JetBrains\IntelliJIdea2022.1\tomcat\
        // logs目录下
        /*application.log("大家好，我是 RainbowSea");
        int age = 17;
        if( age < 17) {
            application.log("对不起，您未成年，请绕行",new RuntimeException("小屁孩，快走开，不适合你"));
        }*/


        /**
         * ServletContext对象还有另外一个名字: 应用域：（后面还有其他域：例如：请求域，会话域）
         * 如果所有的用户共享一份数据，并且这个数据很少被修改，以及这个数量很少，可以将这些数据放到ServletContext这个应用域当中
         * 为什么是所有用户共享的数据，不是共享的没有意义，因为ServletContext 这个对象只有一个，
         * 只有共享的数据放进去才有意义。
         * 为什么要数据量小？因为数据量比较大的话，太占用堆内存，并且这个对象的生命周期比较长，服务器关闭的时候，这个
         * 对象才会被销毁，并且其数据是只读的。
         * 数据量少，所有的用户共享，又不修改，这样的数据放到ServletContext这个应用域当中，会大大提升效率，
         * 因为应用域相当于一个缓存，放到缓存中的数据，下次在用的时候，不需要从数据库中再次获取，大大提升执行效率。
         * // 存
         * public void setAttribute(String name,Object value); // map.put(k,v)
         * // 取
         * public Object getAttrContext(String name);  // Object v = map.get(K)
         * // 删
         * public void removeAttribute(String name);  // map.remove(k)
         *
         */
        // 向ServletContext应用域当中存储数据
        User user = new User("Tom","123");
        // 存
        application.setAttribute("userObj",user);
        // 取
        //Object userObj = application.getAttribute("userObj");
        // 因为我们知道存储的是什么类型的数据所以可以直接强制转换
        User user2 = (User)application.getAttribute("userObj");

        // 也可以从 AServlet服务器当中将数据取出，因为都是在一个webapps当的

        // 输出到浏览器
        out.print("<br>" + user2);

    }
}
