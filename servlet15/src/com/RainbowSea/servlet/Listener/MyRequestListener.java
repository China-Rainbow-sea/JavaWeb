package com.RainbowSea.servlet.Listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;


@WebListener  // 不用写映射路径
public class MyRequestListener implements ServletRequestListener {

    // request 对象创建的时候销毁之前，执行
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("request 对象销毁了");

    }

    // request对象创建时间点，执行
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("request 对象创建的时候");
    }
}
