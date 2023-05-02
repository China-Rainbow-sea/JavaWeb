package com.RainbowSea.servlet.Listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;


@WebListener  // 不用写映射路径
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {

    // 向Session 会话域当中添加存储数据的时候，以下方法被服务器调用执行
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        System.out.println("session add 添加数据了");
    }


    // 将Session 会话域当中的数据删除的时候，以下方法被服务器调用执行了
    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        System.out.println("session removed 移除数据了");
    }


    // 将Session 会话当中某个数据被替换修改了，以下方法被服务器调用执行
    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        System.out.println("session replace 数据被修改替换了");
    }
}
