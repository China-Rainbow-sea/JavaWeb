<%@page contentType="text/html; charset=UTF-8" %>
<%--表示访问 jsp 的时候不生成 session 对象--%>
<%@page session="false" %>  <%--该指令不是警用jsp内置对象当中的 session--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎使用OA系统</title>
</head>
<body>
<%--<!--注意：对应前端的资源获取基本上都是要加项目名的，并且要"/"开始-->--%>
<%--这种方式不好，将 项目名写死了，--%>
<%--<a href="/servlet09/dept/list/">查看部门列表</a>--%>
<%--使用request.getContextPath() 动态获取项目名的根路径带 / 的，
注意哪里可以加 空格，哪里不能加空格--%>
<%--<a href="<%=request.getContextPath()          %>/dept/list">查看部门列表</a>--%>

<%--<%=request.getContextPath()%> &lt;%&ndash;out.print(request.getContextPath()) 获取到该项目的根路径带有/的&ndash;%&gt;--%>


    <h1>Login in</h1>
    <hr>
    <form action="<%=request.getContextPath()%>/user/login" method="post">
        username: <input type="text" name="username" /><br>
        password: <input type="password" name="password" /> <br>
        <input type="submit" value="login" />
    </form>
</body>
</html>