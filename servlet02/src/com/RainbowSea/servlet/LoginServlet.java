package com.RainbowSea.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class LoginServlet extends GenericServlet{


    /**
     * 思考一个问题：
     * 有没有一种可能：需要我们再LoginServlet 类中重写 init()方法呢?
     * public void init(ServletConfig servletConfig) throws ServletException {
     *     }
     * 注意：如果我们重写其中 init()方法，那么其中的 config 可就是 null 了。因为多态性，会调用我们子类中的
     * 重写的init()方法，但是该方法中没有对父类中的 config 属性赋值，当我们再想使用父类中的 config 属性，该变量可就是null
     * 的 。我们为了防止 config 没有赋值而导致的 null,所以我们将 init()被 final 修饰无法重写。
     * 但是注意了: 父类将原始 init()方法 final 了，我们子类没有办法重写这个 init()方法了
     * 如果这个时候，我还是希望能够重写 init()方法，该怎么办
     */

    @Override
    public void init() {
        System.out.println("LoginServlet is init() method excute");
    }

    /**
     * 抽象方法：这个方法最常用，所以要求子类必须实现Service 方法。
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("正在处理用户请求.........");
        // 想在LoginServlet 子类中使用ServletConfig 对象怎么做：我们继承的这个父类中定义了这个属性，
        // 并且值是由 Tomcat 服务器创建的值是一样的
        ServletConfig config = super.getConfig();
        System.out.println("service 方法中是否可以获取到ServletConfig对象 " + config);
    }
}
