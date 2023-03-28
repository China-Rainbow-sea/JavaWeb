package com.RainbowSea.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//Map<String,String[]> getParameterMap() 这个是获取Map
//Enumeration<String> getParameterNames() 这个是获取Map集合中所有的key值
//String[] getParameterValues(String name) 根据Key获取Map集合的value值
// String getParameter(String name) 获得value这个一维数组当中第一个元素
// 以上4个方法,和获取用户提交的数据有关系.
// 注意:前端表单提交的数据的时候
public class RequestTestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 面向接口编程:HttpServletRequest 接口
        // 获取前端提交的数据
        // 前端会提交什么数据呢?
        // 以上的数据会被小猫咪封装到request对象中.

        // 获取参数集合:就是我们从前端获取到的数据信息
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 遍历Map集合(读取Map集合中所有的Key,遍历)
        Set<String> set = parameterMap.keySet();
        Iterator<String> iterator = set.iterator(); // 迭代器
        while (iterator.hasNext()) {
            String key = iterator.next();  // 获取到 key 值
            String[] value = parameterMap.get(key); // 通过key值获取到value值
            System.out.print(key + "-->");
            for (String s : value) {
                System.out.print(s);
            }

            System.out.println();
        }

        System.out.println("******************************");
        // 方式2:
        Enumeration<String> parameterNames = request.getParameterNames();  // 获取到所有的form表单name值
        while(parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();  // name
            System.out.println(key);
        }

        System.out.println("************************");

        // 直接通过name获取到value这个一维数组,注意:这里的值通过向前端复制过来,避免出错.
        String[] usernames = request.getParameterValues("username");

        for(String s : usernames) {
            System.out.println(s);
        }

        System.out.println("*************");

        String[] userpasswords = request.getParameterValues("userpassword");
        for(String s : userpasswords) {
            System.out.println(s);
        }

        System.out.println("****************");
        String[] aihaos = request.getParameterValues("aihao");
        for(String s : aihaos) {
            System.out.println(s);
        }

        System.out.println("****************");
        // 通过name获取value这个一维数组的第一个元素:
        // 这个方法使用最多,因为这个一维数组中一般只有一个元素.
        String username = request.getParameter("username");
        System.out.println(username);
        String userpassword = request.getParameter("userpassword");
        System.out.println(userpassword);

        String[] aihaos1 = request.getParameterValues("aihao");
        // 如果是前端的信息是一维数组使用request.getParameter()方法的话
        // 就是获取到的是该一维数组的第一个元素.
        String aihao = request.getParameter("aihao");
        System.out.println(aihao);
        for(String s : aihaos1) {
            System.out.println(s);
        }


    }

}
