<%@ page import="com.RainbowSea.bean.Dept" %>
<%@ page import="java.util.List" %>  <%--这是导包在jSP 当中，并翻译为import导入对于的报--%>
<%@ page import="java.lang.Integer" %>
<%@page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>部门列表页面</title>
</head>
<script type="text/javascript">
    function del(dno) {
        // 弹出确认框,用户点击确定，返回true,点击取消返回false
        var ok = window.confirm("亲,删了不可恢复哦!");
        if (ok) {
            // 发送请求进行删除数据的操作
            // 在js代码当中如何发送请求给服务器
            //
            // document.location.href="请求路径"
            // document.location = "请求路径"
            // window.location.href = "请求路径"
            // window.location = "请求路径"

            // 注意是根据所传的部门编号删除数据的
            document.location.href = "<%=request.getContextPath()%>/dept/delete?deptno=" + dno;

        }
    }
</script>

<body>
<h1 align="center">部门列表</h1>
<%--显示登录的用户名信息--%>
<%--<%

    ServletContext servletContext = session.getServletContext();
    Object onlinecount = servletContext.getAttribute("onlinecount");

%>--%>
<%--显示用户在线的个数，其信息存储在了 ServletContext()应用域当中了--%>
<h3>在线人数: <%=request.getServletContext().getAttribute("onlinecount")%></h3>
<%--注意这里使用的是 jsp 内置的 session 对象所以不可以，不要把 session 禁用了。--%>

<a href="<%=request.getContextPath()%>/user/exit">安全退出系统(手动清除 session 会话信息)</a>

<table border="1px" align="center" width="50%">
    <tr>
        <th>序号</th>
        <th>部门编号</th>
        <th>部门名称</th>
    </tr>

    <%
        // 获取到 request 域当中的数据，并取出来，我们知道是什么类型的数据，直接强制转换
        List<Dept> depList = (List<Dept>) request.getAttribute("depList");

        int i = 0;
        // 循环遍历List，取出数据
        for (Dept dept : depList) {
            // 在后台输出测试
            //System.out.println(dept.getDname());
            // 在浏览器显示 测试
            //out.write(dept.getDname() + "<br>");


    %>
    <%--    浏览器显示测试--%>
    <%--    <%=dept.getDname()%>--%>


    <tr>
        <td><%=++i%>
        </td>
        <td><%=dept.getDeptno()%>
        </td>
        <td><%=dept.getLoc()%>
        </td>
        <td>
            <!-- javascript:void(0)保持<a>超链接的格式，同时不会发生跳转
                我只是希望用户点击该超链接的时候执行一段js代码，不进行页面的跳转 -->
            <a href="javascript:void(0)" onclick="del(<%=dept.getDeptno()%>)">删除</a>
<%--            <a href="<%=request.getContextPath()%>/dept/detail?f=m&dno=<%=dept.getDeptno()%>">修改</a>--%>
<%--            <a href="<%=request.getContextPath()%>/dept/detail?f=d&dno=<%=dept.getDeptno()%>">详情</a>--%>
            <%-- 这里的修改，和详情都是需要 部门编号，我们可以进行一个统一处理，通过所传的不同的 f= 值作为
 标记，用于服务器端的 Servlet 判断转发处理到的--%>
            <%--            或者是--%>
            <a href="<%=request.getContextPath()%>/dept/detail?f=edit&dno=<%=dept.getDeptno()%>">修改</a>
            <a href="<%=request.getContextPath()%>/dept/detail?f=detail&dno=<%=dept.getDeptno()%>">详情</a>
        </td>
    </tr>

    <%
        }
    %>
</table>
<hr>
<a href="<%=request.getContextPath()%>/add.jsp">新增部门</a>
</body>

</html>