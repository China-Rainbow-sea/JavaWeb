<%@ page import="com.RainbowSea.bean.Dept" %>
<%@page contentType="text/html; charset=UTF-8" %>


<%
  // 获取到请求域当中的数据，
  Dept dept = (Dept) request.getAttribute("dept");
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>部门详情</title>
</head>
<body>
<h1>部门详情</h1>
部门编号: <%=dept.getDeptno()%> <br>
部门名称: <%=dept.getDname()%><br>
部门位置: <%=dept.getLoc()%><br>

<input type="button" value="后退" onclick="window.history.back()"  />
</body>
</html>