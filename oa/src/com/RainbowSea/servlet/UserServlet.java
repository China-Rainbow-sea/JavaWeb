package com.RainbowSea.servlet;

import com.RainbowSea.DBUtil.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet({"/user/login", "/user/exit"})
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        // 获取到浏览器地址栏上的URL路径
        String servletPath = request.getServletPath();

        if ("/user/login".equals(servletPath)) {
            doLogin(request, response);
        } else if ("/user/exit".equals(servletPath)) {
            doExit(request, response);
        }


    }

    private void doExit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取到客户端发送过来的 sessoin
        HttpSession session = request.getSession();

        if (session != null) {
            // 手动销毁 session 对象
            // 注意：会话销毁的了，自然需要重写登录了，没有登录过，无法进行一个路径的访问的
            // 因为会话集。
            session.invalidate();


            // 跳转会登录的页面
            response.sendRedirect(request.getContextPath() + "/index.jsp");  // 项目名路径默认就是访问的index.html 的欢迎页面
            // 注意：这里修改了，需要指明index.jsp登录页面了，因为局部优先
        }
    }

    protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        // 一个用户登录验证的方式：验证用户名和密码是否正确
        // 获取用户名和密码
        // 前端提交是数据是：username=111&password=fads
        // 注意：post 提交的数据是在请求体当中，而get提交的数据是在请求行当中

        boolean success = false;  // 标识登录成功

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String exempt = request.getParameter("exempt");

        // 连接数据库验证用户名和密码
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 1. 获取连接，注册驱动
            connection = DBUtil.getConnection();

            // 2. 获取操作数据对象，预编译sql语句, ? 占位符不要加，“”,'' 单双引号，成了字符串了，无法识别成占位符了。
            String sql = "select username,password from t_user where username = ? and password = ?";
            preparedStatement = connection.prepareStatement(sql);

            // 3. 填充占位符，真正执行sql语句
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            // 4. 处理查询结果集
            // 只有一条结果集
            if (resultSet.next()) {
                // 登录成功
                success = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 5. 关闭资源，最后使用的最先关闭，
            DBUtil.close(connection, preparedStatement, resultSet);
        }


        // 登录成功与否
        // 设置登录成功十天的免登录
        if (success) {
            // 成功，跳转到用户列表页面
            // 这里使用重定向(没有资源的共享)：重定向需要加/项目名 +

            // 获取session 对象(这里的要求是： 必须获取到 session ,没有session 也要新建一个 session 对象)
            HttpSession session = request.getSession();  // 服务器当中没有 session 会话域自动创建
            session.setAttribute("username", username);  // 将用户名存储到 session 会话域当中

            // 判断用户是否选择了免十天登录的选择
            if ("true".equals(exempt)) {
                // 创建 Cookie 对象存储登录名
                Cookie cookie = new Cookie("username", username);
                // 创建Cookie 对象存储登录密码
                Cookie cookie2 = new Cookie("password", password);

                // 设置 cookie 的有效期为 10 天
                cookie.setMaxAge(60 * 60 * 24 * 10);// 单位时 s 秒
                cookie2.setMaxAge(60 * 60 * 24 * 10);

                // 设置Cookie 关联的path(只要访问时这个应用，这个项目，浏览器一定要携带两个 cookie信息发送给服务器)
                cookie.setPath(request.getContextPath());
                cookie2.setPath(request.getContextPath());  // 动态获取到项目名，的根路径


                // 将服务器生成的 cookie 信息 响应到浏览器端
                response.addCookie(cookie);
                response.addCookie(cookie2);  // 注意是 response 服务器端将 cookie的信息响应到客户端，客户端接收并存储起来
                // session 会话时，服务器创建的 ，客户端发送request  请求获取到该 session 对象的 sessionID

            }

            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
            // 失败，跳转到失败页面
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }


    }
}
