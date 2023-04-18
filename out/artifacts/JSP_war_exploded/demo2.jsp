<%@page contentType="text/html; UTF-8" %>

<%
    System.out.println("hello jsp");
%>

<!--注意这个是HTML 当中的注释，这个注释不专业，仍然会被翻译大宋Java源代码当中，
在JSP中不要使用这中注释 相当于是: out.write("<--   -->
<%--这个才是正宗的注释，不会翻译到Java源代码当中--%>

<%--<%
注意 <% %> 是出现在 Serviet()方法体当中的，方法体当中是不可以定义 private 成员变量的，更不能定义方法。
报错：原因：看源码：在serviet()方法体当中，定义的变量不能使用 private 等访问权限修饰符修饰的
    private int num = i;


%>--%>

<%--<%
报错原因：在serviet()方法体当中：是不可以定义静态代码块的。
    static {
        System.out.println("hello JSP");
    }
%>--%>


<%--
<%
    public void fun() {
        System.out.println("hello JSP");
    }
%>--%>
<%--

<%
    报错原因: 方法体当中的代码每一行都是Java语句，Java语句要以分号结尾。
    System.out.println("hello jsp")
%>
--%>


<%
    int num = 10;
%>

<%
    System.out.println("num = " + num);
%>