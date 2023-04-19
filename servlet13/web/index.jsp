<%--
  Created by IntelliJ IDEA.
  User: huo
  Date: 2023/4/19
  Time: 8:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Cookie</title>
  </head>
  <body>
  <a href="<%=request.getContextPath()%>/cookie/generate">服务器生成Cookie 信息，然后将该信息响应给浏览器，浏览器接收cookie,
  并将cookie信息存储到客户端的上</a> <br>

  <a href="<%=request.getContextPath()%>/sendCookie">浏览器发送Cookie给服务器</a>
  </body>
</html>
