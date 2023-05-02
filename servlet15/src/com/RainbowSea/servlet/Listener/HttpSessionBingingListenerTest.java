package com.RainbowSea.servlet.Listener;

import com.RainbowSea.servlet.been.User1;
import com.RainbowSea.servlet.been.User2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;



@WebServlet("/test2")
public class HttpSessionBingingListenerTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        // 获取到session 会话对象
        HttpSession session = request.getSession();

        // 向 session 会话域当中添加 User01 been 数据
        User1 user1 = new User1("zhangsan01","1");
        session.setAttribute("user1",user1);
        session.removeAttribute("user1");  // 移除session 数据


        // 向 session 会话域当中添加 User02 been 对象数据
        User2 user2 = new User2("jack","2");
        session.setAttribute("user2",user2);
        session.removeAttribute("user2");  // 移除session 数据;


    }
}
