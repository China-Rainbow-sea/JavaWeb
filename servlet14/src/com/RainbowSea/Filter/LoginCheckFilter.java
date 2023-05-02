package com.RainbowSea.Filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        // 需要强制类型转换以下，因为 下面的一些getServletPath 方法是来自 HttpServletRequest的
        //不是 来自 ServletRequest 的
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String servletPath = request.getServletPath();  // 获取到浏览器当中的uri

        // 获取session 这个 session 是不不需要新建的
        // 只是获取当前session ,获取不到这返回null,
        HttpSession session = request.getSession(false);  // 获取到服务器当中的session ，没有不会创建的



        // 过滤器：
        /*
        有什么情况下不能拦截: 要过滤通过：
          1. 目前编写的路径是/* 表示所有请求均拦截:

          用户访问 index.jsp 的时候不能拦截, 要放行:
          用户登录过了，这个时候，需要放行。
          用户要去登录，这个也是不能拦截，要放行：让用户登录。
          其他情况：比如，没有登录过，就想访问资源的话，过滤拦截，
          登录失败，过滤拦截
          用户退出，不能拦截，要让其过去


           if ("/dept/list".equals(servletPath)) {
                doList(request, response);
            } else if ("/dept/detail".equals(servletPath)) {
                doDetail(request, response);
            } else if ("/dept/delete".equals(servletPath)) {
                doElete(request,response);
            } else if("/dept/save".equals(servletPath)) {
                doSave(request,response);
            } else if("/dept/modify".equals(servletPath)) {
                doModify(request,response);


         */

        if("/index.jsp".equals(servletPath) || (("/welcome").equals(servletPath)) ||
                ( session != null && session.getAttribute("username") != null)
        || "/user/login".equals(servletPath) || "/user/exit".equals(servletPath)) {
            // 双重的判断，一个是 session 会话域要存在，其次是 会话域当中存储了名为 "username" 的信息

            chain.doFilter(request,response);  // 放行，让其向下一个过滤器，或者是Servlet 执行
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");  // 访问的web 站点的根即可，自动找到的是名为 index.jsp
            // 的欢迎页面（注意这里被优化修改了：局部优先）注意：这里修改了，需要指明index.jsp登录页面了，因为局部优先
        }
    }
}
