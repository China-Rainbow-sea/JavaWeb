package com.RainbowSea.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class ConfigTestServlet extends GenericServlet {


    /**
     * ServletConfig
     * 1. ServletConfig 是什么？
     * package javax.servlet;
     * 显然是 Servlet 规范中的一员
     * ServletConfig 是一个接口，(Jakarta.servlet.Servlet 是一个接口)
     *
     * 2.谁去实现了这个接口呢？
     *  org.apache.catalina.core.StandardWrapperFacade
     *  public final class StandardWrapperFacade
     *     implements ServletConfig {}
     *
     *  结论：Tomcat 服务器实现了ServletConfig 接口
     *  思考: 如果把Tomcat 服务器换成jetty服务器，输出ServletConfig 对象的时候，还是这个结果吗?
     *  不一定一样，包含类名可能还是Tomcat 不一样，但是他们都实现了ServletConfig 这个规范
     *
     *  注意： 一个Servlet 对象中有一个ServletConfig对象，(Servlet 和 ServletConfig 对象是一对一的)
     *  100 个Servlet, 就应该有100个ServletConfig 对象
     *
     *  4.ServletConfig对象是谁创建的，在什么时候创建的?
     *  Tomcat 服务器(WEB服务器) 创建了ServletConfig 对象
     *  在创建Servlet 对象的时候，同时创建ServletConfig 对象.
     *  5.ServletConfig 接口到底是干什么的？ 有什么用？
     *     Config 是哪个单词的缩写？
     *      Configuration
     *    ServletConfig 对象翻译为: Servlet 对象的配置信息对象。
     *    一个Servlet对象就有一个配置信息对象： web.xml <servlet></servlet>
     *    两个Servlet 对象就有两个配置信息对象。
     *
     *   6. ServletConfig对象中创建包装了什么信息?
     *      web.xml <servlet></servlet> 标签配置信息。
     *
     *   7. Tomcat 小喵咪解析web.xml 文件，将web.xml文件中 <servlet></servlet> 标签中的配置信息自动
     *   包装到ServletConfig 对象中
     *    <servlet>
     *         <!--        注意： 两个name 要保持一致-->
     *         <servlet-name>ConfigTestServlet</servlet-name>
     *         <servlet-class>com.RainbowSea.servlet.ConfigTestServlet</servlet-class>
     * <!--       这里是可以配置一个Servlet对象的初始化信息的 注意：这里的配置对应的是上述<servlet-name>为ConfigTestServlet
     * 所以需要通过该ConfigTestServlet的Servlet中的cofig方法获取到如下的init-param配置信息-->
     *         <init-param>
     *             <param-name>driver</param-name>
     *             <param-value>com.mysql.cj.jdbc.Driver</param-value>
     *         </init-param>
     *         <init-param>
     *             <param-name>url</param-name>
     *             <param-value>jdbc:mysql://localhost:3306/dbtest9</param-value>
     *         </init-param>
     *         <init-param>
     *             <param-name>usert</param-name>
     *             <param-value>root</param-value>
     *         </init-param>
     *
     *         <init-param>
     *             <param-name>password</param-name>
     *             <param-value>root123</param-value>
     *         </init-param>
     *     </servlet>
     *     以上是 <servlet-name>ConfigTestServlet</servlet-name> <init-param></init-param>
     *     是初始化参数，这个初始化参数信息被小喵咪封装到 ServletConfig 对象当中。
     *
     *   8. ServletConfig 接口中有4个方法:
     *   第1个方法:
     *     public String getInitParameter(String name)
     *
     *   第2个方法:
     *     public Enumeration<String> getInitParameterNames();
     *   第3个方法:
     *     public ServletContext getServletContext();
     *   第4个方法:
     *     public String getServletName();
     *
     *   以上的4个方法: 在自己的编写的Servlet表当中也可以使用this,/super去调用（这个Servlet继承了GenericServet）
     *
     */

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        // 设置在页面上当中显示的类型: 注意：必须在输出前使用
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        // 获取ServletConfig 对象
        ServletConfig config = super.getServletConfig();

        // 输出对象
        // ServletConfig对象是: org.apache.catalina.core.StandardWrapperFacade@770af11
        out.print("ServletConfig对象是: " + config);

        // 获取<servlet-name><servlet-name>
        String servletName = config.getServletName();
        out.print("<br>");
        out.print("<servlet-name>" + servletName + "</servlet-name>");

        // 通过ServletConfig对象的两个方法，可以获取到web.xml文件中的初始化参数配置信息
        // public abstract java.util.Enumeration<String> getInitParameterNames() 获取所以的初始化参数的name
        //public abstract String getInitParameter(String name ) 通过初始化参数(<init-param>)的name获取value值

        Enumeration<String> initParameterNames = config.getInitParameterNames();
        out.print("<br>");
        // 遍历集合
        while(initParameterNames.hasMoreElements()) {  // 是否有更多元素
            String parameterName = initParameterNames.nextElement();// 取元素
            out.print(parameterName);
            out.print("<br>");
            String initParameter = config.getInitParameter(parameterName);
            out.println(initParameter);
            out.print("<br>");
        }

        out.print("******************************");

        // public java.util.Enumeration<String> getInitParameterNames() 一次性获取到该Servlet对象<init-param>
        // 标签当中配置信息的 name 值
        Enumeration<String> names = super.getInitParameterNames();

        while(names.hasMoreElements()) {
            String s = names.nextElement();
            String value = super.getInitParameter(s);

            // 打印到后台:
            System.out.println(s + "-->" + value);
        }

        // 怎么获取到ServletContext对象
        // 第一中方式：通过ServletConfig对象获取ServletContext对象
        ServletContext servletContext = config.getServletContext();
        // 输出
        // org.apache.catalina.core.ApplicationContextFacade@5a2d47f
        out.print("<br>" + servletContext);

        // 第二中方式： 通过this,super 也可以获取到ServletContest
        // org.apache.catalina.core.ApplicationContextFacade@481f90a8
        ServletContext application = super.getServletContext();
        out.print("<br>" + application);


    }
}
