package com.RainbowSea.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class AServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 设置，在浏览器上响应的格式类型
        Date nowTime = new Date();  // 创建当前时间的 Date 对象

        // 将 nowTime 的数据存储(绑定)到请求域当中
        request.setAttribute("sysTime",nowTime);

        // 第一步: 获取到转发对象，注意：/ 开始，不家项目名 , / + 对应跳转的 Servlet 当中的 web.xml 当中的url映射的路径
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/B");

        // 第二步: 调用转发器的forward方法完成跳转/转发
        requestDispatcher.forward(request,response);

    }
}
