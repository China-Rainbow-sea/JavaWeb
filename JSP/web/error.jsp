
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
 在错误页面可以启用JSP九大内置对象，exception
 exeption内置对象就是刚刚发生的异常对象
--%>
<%@page isErrorPage="true" %>
<%--isErrorPage 有两个参数一个是为：false,true ：true 表示开启 isErrorPage ，false 表示禁用，默认不设置也是禁用状态的--%>

<html>
<head>
    <title>程序错误</title>
</head>
<body>
<h2>网络繁忙，稍后再试</h2>

<%
    // 配置了 isErrorPage 内置的异常对象则可以
    // 打印异常堆栈信息，输出到后台控制台上
    // exception 是JSP 九大内置对象之一。
    exception.printStackTrace();
%>


</body>
</html>
