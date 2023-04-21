<%@ page import="com.RainbowSea.bean.Dept" %>
<%@page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">


<%
  // 获取到存储到请求域当的 部门信息
  Dept dept = (Dept) request.getAttribute("dept");
%>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>修改部门</title>
</head>

<body>
<h1>修改部门</h1>
<form action="<%=request.getContextPath()%>/dept/modify" method="post">
  <!-- readonly 表示只读，不可修改的作用 -->
  部门编号: <input type="text" name="deptno" value="<%=dept.getDeptno()%>" readonly /><br>
  部门名称: <input type="text" name="dname" value="<%=dept.getDname()%>" /><br>
  部门位置: <input type="text" name="loc" value="<%=dept.getLoc()%>" /><br>
  <input type="submit" value="修改" />
</form>

</body>

</html>