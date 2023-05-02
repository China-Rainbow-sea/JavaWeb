package com.RainbowSea.servlet.Listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;


@WebListener  // 监听器不用写: 映射路径
public class MyHttpSessionListener implements HttpSessionListener {


    // Session 会话创建的时候，执行
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Session 会话创建了");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Session 会话销毁了");
    }

    // 这里我们调用jsp 文件中，内置的 Session 对象
}
