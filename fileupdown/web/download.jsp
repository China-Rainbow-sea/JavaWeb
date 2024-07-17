<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>文件下载</title>
    <base href="<%=request.getContextPath()+"/"%>>">
</head>
<body>
<h1>文件下载</h1>
<a href="fileDownLoadServlet?name=java.png">点击下载Java图片</a><br/><br/>
<a href="fileDownLoadServlet?name=13-第十二章网络编程.pptx">点击下载 13-第十二章 网络编程.pptx</a><br/><br/>
</body>
</html>