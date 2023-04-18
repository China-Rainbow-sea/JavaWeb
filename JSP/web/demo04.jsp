<%@ page contentType="text/html; charset=UTF-8" %>

<%
    String name = "Nick";
%>

<%

    // 使用JSP当中的 service()方法当中内置的对象
    out.write("name = " + name);
%>

<%!
    // 注意了 out 是 jsp当中service()方法当中的内置的对象
    //out.write()
%>


<%
    out.write("zhangsan");
    out.write("lisi");

%>


<%--如果是想要在浏览端直接显示字符串的话：则我们可以直接写到 jsp 当中就可以了。--%>
<br>
张三
李四

<br>
<%=name%>

<br>
<%=100 + 200%>

<br>
<%="hello world"%>
<%--输出的是一个固定的，纯字符串，这样输出就没有意义了。可以使用如下方式替换--%>
hello world

<br>

<%
    int a = 99;
    int b = 1;
    int c = a + b;
%>


<%--输出变量值到浏览器的 html当中--%>
<%=c%>


<br>

<%
    String username = "Niek";
%>


<%= "登录成功，欢迎 ！" + username%>
<%-- out.print( "登录成功，欢迎 ！" + username); --%>



