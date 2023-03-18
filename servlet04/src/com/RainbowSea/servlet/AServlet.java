package com.RainbowSea.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;


/**
 * ServletContext
 * 1.ServletContext 是什么？
 * ServletContext 是接口，是Servlet规范中的一员
 * 2.ServletContext是谁实现的
 *  Tomcat服务器(WEB服务器) 实现了ServletContext 接口
 *  public class ApplicationContextFacade implements ServletContext {}
 * 3. ServletContext 对象是谁创建的？ 在什么时候创建的
 *  ServletContext 对象在WEB服务器启动的时候创建
 *  ServletContext 对象是WEB服务器创建的
 *  对于一个webapp来说，ServletContext 对象只有一个:
 *  context 是什么意思:
 *    ServletContext 对象的环境对象，(Servlet对象的上下文对象)
 *    其实说白了: ServletContext 对象其实一个webapps中在 WEB-INF目录下的 web.xml文件。当中的配置环境
 *    比如：50个学生，每个学生都是一个Servlet，这50个学生都在同一个教室当中，那么这个教室就相当于ServletContext对象
 *    放在ServletContext对象当中的数据，所有Servlet一定是共享的
 *    比如: 一个教室中的空调是所有学生共享的，一个教室中的语文老师就是所有学生共享的
 *    Tomcat 是一个容器，一个容器当中可以放多个webapp，一个webapp对应一个ServletContext对象。
 *    假设有100个webapp,那么就有100个ServletContext对象，但是，总之，一个应用，一个webapp肯定是只有一个ServleContext对象
 *    因为只有一个web.xml文件
 *
 */
public class AServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        // 设置输出在html页面当中的格式
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        // 获取ServetContext对象
        ServletContext application = super.getServletContext();
        //ServletContest : org.apache.catalina.core.ApplicationContextFacade@51c3b2bc
        out.print("ServletContest : " + application);


        // 获取web.xml当中上下文的初始化参数
        Enumeration<String> initParameterNames = application.getInitParameterNames();
        while(initParameterNames.hasMoreElements()) {
            String name = initParameterNames.nextElement();
            String value = application.getInitParameter(name);
            out.print("<br> " + name);
            out.print("<br> " + value);
        }


        // 通过:  ServletContext 获取文件的绝对路径
        // 注意 / 表示当前web的目录
        // 就算你不加 “/” 默认也是从 web 根目录下开始找的。
        // E:\Java\JavaWeb\out\artifacts\servlet04_war_exploded\index.html
        String realPath = application.getRealPath("/index.html");
        out.print("<br>" + realPath);

        // 如果在web目录下，因为是从web目录开始找的，所以如果 其web下面还有子目录，则需要通过说明该子目录的
        String realPath2 = application.getRealPath("common/index.html");
        out.print("<br> " + realPath2);


        Object userObj = application.getAttribute("userObj");
        out.print("<br> " + userObj);
    }


}

