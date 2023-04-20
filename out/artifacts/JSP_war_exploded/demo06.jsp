<%@page errorPage="/error.jsp" %>
<%--eeorPage JSP九大内置对象，表示如果该页面出现了错误，就跳转到 error.jsp 页面，

注意：一点就是如果没有在对应跳转到的 error.jsp 页面当中配置<%@page isErrorPage="true" %> 的话
是没有提示信息的，这个对客户端很友好，但是对应程序员来说就是不友好的，因为程序员不知道错误信息，提示
就无法调试Bug了--%>
<%
    String name = null;
    // 空指针异常。
    name.toString();
%>
