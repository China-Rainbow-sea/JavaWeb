<%@page contentType="text/html; charset=UTF-8" %>   <%--jsp的指令，设置其响应的字符集编码，和格式类型--%>


<%
    // 注意这个是翻译到 service()方法体当中的，
    // 方法体当中可以使用该Servlet 类当中的成员变量
    // 下面我们在 Servlet 类当中定义了 name 的成员变量
    System.out.println("name = " + name);
%>


<%!
    // 在Servlet 类当中定义成员变量
    private String name = "jsp";


    // 在Servlet 类当中定义静态代码块
    static {
        System.out.println("静态代码块执行了");
    }

    // 在Servlet 类当中定义方法
    public static void m1() {
        System.out.println("m1 method execute!");
    }
%>
