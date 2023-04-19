package com.RainbowSea.Cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cookie/generate")
public class GenerateCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {


        response.setContentType("text/html;charSet=UTF-8");
        // 服务器端：创建Cookie 对象,Cookie是一个对象，只有一个构造器()
        Cookie cookie = new Cookie("productid", "123");
        Cookie cookie2 = new Cookie("username", "zhangsan");
        // Set-Cookie: productid=123

        // 设置Cookie在一个小时后失效
        // Set-Cookie: productid=123; Max-Age=3600; Expires=Wed, 19-Apr-2023 13:50:25 GMT
        //cookie.setMaxAge(60*60);// 单位是 秒 s 注意时间是本初子午线的规定的。我们这里是东八区是有一个 7 小时的时差的。

        //cookie.setMaxAge(0);
        // Set-Cookie: productid=123; Max-Age=0; Expires=Thu, 01-Jan-1970 00:00:10 GMT
        // 失效时间为 1970年1月1日0时0分0秒: 这个失效点，cookie早就失效了。
        // 所以设置cookie的有效期为0 ,表示该cookie 被删除，主要应用在，使用这种方式删除浏览器上的同名cookie


        // Set-Cookie: productid=123
        cookie.setMaxAge(-1);
        // 设置cookie 的有效期 < 0 表示该cookie 不会被存储。(表示不会被存储到硬盘文件当中，会放到浏览器运行内存当中)
        // 和不设置 setMaxAge()时一个效果。

        // 设置cookie 关联的路径：如果没有设置的在其生成的cookie的映射路径下将其子路径的范围内都会携带cookie信息发送给服务器。
        // cookie.setPath("/servlet13");  // /servlet13 该webapp项目名的映射路径
        // 优化设置：
        cookie.setPath(request.getContextPath());  // 直接通过动态获取都项目名的根路径
        cookie2.setPath(request.getContextPath());  // 要这是该 Cookie信息当中的关联路径
        // 这个设置表示只要是这个Servlet13项目的请求路径下的，都会携带这个cookie信息给服务器

        //Set-Cookie: JSESSIONID=FE265D8C3008EF440C6C58036C552A23; Path=/servlet13;
        //Set-Cookie: productid=123; Path=/servlet13 访问会生成 cookie 的信息的 Servlet
        // 将我们服务器端创建的 Cookie信息 响应到浏览器，浏览器存储起来。
        response.addCookie(cookie);
        response.addCookie(cookie2); // 将服务器当中的创建的的cookie信息，响应到客户端

    }
}
