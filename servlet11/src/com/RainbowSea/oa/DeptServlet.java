package com.RainbowSea.oa;

import com.RainbowSea.DBUtil.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 采用模板方法设计模式，重新设计一个 OA 系统
 */

//@WebServlet(value = {"/dept/list","/dept/save","/dept/edit","/dept/detail","/dept/delete","/dept/modify"}

//@WebServlet({"/dept/list", "/dept/save", "/dept/edit", "/dept/detail", "/dept/delete", "/dept/modify"})
@WebServlet("/dept/*")  // 可以使用模糊查询，* 任意字符串，表示/dept/xxx的任意都可以访问该Servlet
public class DeptServlet extends HttpServlet {

    // 模板方法
    // 重写其中的 servlet 方法（并没有重写其中的doGet()方法，405错误没有了）

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        // 获取到该对应请求地址栏上的 url ,也就是一个servet对应当中的 url 映射路径
        // 这里是对应浏览器地址栏上的 url
        String servletPath = request.getServletPath();  // 返回的 url 的开头是带了 "/" 的

        if ("/dept/list".equals(servletPath)) {
            doList(request, response);
        } else if ("/dept/save".equals(servletPath)) {
            doSave(request, response);
        } else if ("/dept/edit".equals(servletPath)) {
            doEdit(request, response);
        } else if ("/dept/detail".equals(servletPath)) {
            doDetail(request, response);
        } else if ("/dept/delete".equals(servletPath)) {
            doDel(request, response);
        } else if ("/dept/modify".equals(servletPath)) {
            doModify(request, response);
        }

    }


    private void doList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 设置前端浏览器显示的格式类型，以及编码
        response.setContentType("text/html;charSet=UTF-8");
        PrintWriter writer = response.getWriter();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // 获取到该webapp的项目根路径：也就是在Tomcat 当中设置的访问的项目路径
        String contextPath = request.getContextPath();


        int i = 0;

        writer.println("     <!DOCTYPE html>");
        writer.println("<html lang='en'>");

        writer.println("<head>");
        writer.println("    <meta charset='UTF-8'>");
        writer.println("   <title>部门列表页面</title>");
        writer.println("</head>");

        writer.println("    <script type = 'text/javascript' >");
        writer.println("            function del(dno) {");
        // 弹出确认框,用户点击确定，返回true,点击取消返回false
        writer.println("        var ok = window.confirm('亲,删了不可恢复哦!');");
        writer.println("        if (ok) {");
        // 发送请求进行删除数据的操作
        // 在js代码当中如何发送请求给服务
        // document.location.href='请求路径
        // document.location = '请求路径'")
        // window.location.href = '请求路径
        // window.location = '请求路径'
        // 注意是根据所传的部门编号删除数据的
        writer.println("             document.location.href = '" + contextPath + "/dept/delete?deptno=' + dno");
        writer.println("          }");
        writer.println("       }");
        writer.println("</script >");

        writer.println("<body>");
        writer.println("    <h1 align='center'>部门列表</h1>");
        writer.println("   <table border='1px' align='center' width='50%'>");
        writer.println("      <tr>");
        writer.println("          <th>序号</th>");
        writer.println("         <th>部门编号</th>");
        writer.println("         <th>部门名称</th>");
        writer.println("     </tr>");

        try {
            // 连接数据库，查询所有部门:
            // 1. 注册驱动,获取连接
            connection = DBUtil.getConnection();
            // 2. 获取操作数据库对象，预编译sql语句
            String sql = "select depton as det,dname,loc from dept"; // 在mysql中测试一下是否正确
            preparedStatement = connection.prepareStatement(sql);

            // 3. 执行sql语句
            resultSet = preparedStatement.executeQuery();

            // 4. 处理查询结果集
            while (resultSet.next()) {
                String det = resultSet.getString("det");  // 有别名要使用别名
                String dname = resultSet.getString("dname");
                String loc = resultSet.getString("loc");

                writer.print("			<tr>");
                writer.print("				<td>" + (++i) + "</td>");
                writer.print("				<td>" + det + "</td>");
                writer.print("				<td>" + dname + "</td>");
                writer.print("				<td>");
                writer.print("					<a href='javascript:void(0)' onclick= 'del(" + det + ")'>删除</a>");
                // 将部门编号传过去，用户数据库查询修改
                writer.print("					<a href='" + contextPath + "/dept/edit?deptno=" + det + "'>修改</a>");
                //注意这里的是前端的资源，需要加项目名，但是这里的项目名我们通过 getContestPath()方法动态获取
                // 并且将部门名传过去，再从数据库当中查找出来对应的部门的详细信息：注意: ?(间隔) Http传输协议
                writer.print("					<a href='" + contextPath + "/dept/detail?deptno=" + det + "'>详情</a>");
                writer.print("				</td>");
                writer.print("			</tr>");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            // 5. 关闭资源
            DBUtil.close(connection, preparedStatement, resultSet);
        }

        writer.println("</table>");
        writer.println("<hr>");
        // 前端的资源路径访问需要加项目名
        writer.println("<a href='" + contextPath + "/add.html'>新增部门</a>");
        writer.println("</body>");
        writer.println("</html>");

    }


    private void doSave(HttpServletRequest request, HttpServletResponse response) throws IOException {
         /*
        思路:
         获取到前端的提交的数据，注意 编码设置post 请求
         连接数据库: 进行添加数据
         添加成功: 返回部门列表页面
         添加失败: 返回失败的页面
         */

        request.setCharacterEncoding("UTF-8");

        // 获取到前端的数据，建议 name 使用复制
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        // 连接数据库，添加数据
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        // 影响数据库的行数
        int count = 0;

        try {
            // 1. 注册驱动，连接数据库
            connection = DBUtil.getConnection();

            // 2. 获取操作数据库对象，预编译sql语句，Sql测试
            String sql = "insert into dept(depton,dname,loc) values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            // 3. 填充占位符, 真正执行sql语句，
            // 注意： 占位符的填充是从 1 开始的，基本上数据库相关的起始下标索引都是从 1下标开始的
            preparedStatement.setString(1, deptno);
            preparedStatement.setString(2, dname);
            preparedStatement.setString(3, loc);

            // 返回影响数据库的行数
            count = preparedStatement.executeUpdate();

            // 5.释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }

        // 保存成功，返回部门列表页面
        if (count == 1) {
            // 这里应该使用，重定向
            // 这里用的转发，是服务器内部的，不要加项目名
            //request.getRequestDispatcher("/dept/list/").forward(request, response);

            // 重定向
            response.sendRedirect(request.getContextPath() + "/dept/list/");
        } else {
            // 保存失败
            // web当中的 html资源，这里的 "/" 表示 web 目录
            //request.getRequestDispatcher("/error.html").forward(request, response);

            response.sendRedirect(request.getContextPath() + "/error.html");
        }
    }


    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charSet=UTF-8");
        PrintWriter writer = response.getWriter();

        writer.println("     <!DOCTYPE html>");
        writer.println("<html lang='en'>");

        writer.println("<head>");
        writer.println("    <meta charset='UTF-8'>");
        writer.println("   <title>部门列表页面</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("    <h1>修改部门</h1>");


        writer.println("   <form action='" + request.getContextPath() + "/dept/modify' method='post'>");



        /*
        思路:
        获取到提交的过来的 部门编号
        根据部门编号修改信息，注意：部门编号是唯一的不要被修改了
        连接数据库，查询到相关信息显示到浏览器页面当中，方便用户修改
         */

        String deptno = request.getParameter("deptno");

        // 连接数据库
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 1. 注册驱动，连接数据库
            connection = DBUtil.getConnection();

            // 2. 获取到操作数据库对象，预编译SQL语句，sql测试
            String sql = "select dname,loc from dept where depton = ?";

            preparedStatement = connection.prepareStatement(sql);

            // 3. 填充占位符，真正执行sql语句
            preparedStatement.setString(1, deptno);
            resultSet = preparedStatement.executeQuery();

            // 4. 处理查询结果集
            while (resultSet.next()) {
                String dname = resultSet.getString("dname");  // 查询使用的别名，要用别名
                String loc = resultSet.getString("loc");

                // <!-- readonly 表示只读，不可修改的作用
                writer.println("      部门编号: <input type='text' name='deptno' value='" + deptno + "' readonly /><br>");
                writer.println("     部门名称: <input type='text' name='dname' value=" + dname + " /><br>");
                writer.println("    部门位置: <input type='text' name='loc' value=" + loc + " /><br>");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 5.释放资源，最后使用的优先关闭（因为如果是关闭优先使用的话，再最后面使用的可能需要前面的资源，才能执行）
            DBUtil.close(connection, preparedStatement, resultSet);
        }

        writer.println(" <input type='submit' value='修改' />");
        writer.println(" </form>");
        writer.println("</body>");
        writer.println("</html>");
    }


    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置前端浏览器格式类型和字符集编码
        response.setContentType("text/html;charSet=UTF-8");
        PrintWriter writer = response.getWriter();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //中文思路（思路来源于：你要做什么？目标：查看部门详细信息。）
        // 第一步：获取部门编号
        // 第二步：根据部门编号查询数据库，获取该部门编号对应的部门信息。
        // 第三步：将部门信息响应到浏览器上。（显示一个详情。）


        // 1.
        // http://127.0.0.1:8080/servlet09/dept/detail?deptno=40
        String deptno = request.getParameter("deptno");  // 注意是我们前端提交的数据，建议复制name

        try {
            // 2. 连接数据库，根据部门编号查询数据库
            // 1.注册驱动，连接数据库
            connection = DBUtil.getConnection();

            // 2. 预编译SQL语句,sql要测试
            String sql = "select dname,loc from dept where depton = ?";  // ? 占位符
            preparedStatement = connection.prepareStatement(sql);

            // 3. 填充占位符,真正执行sql语句
            preparedStatement.setString(1, deptno);
            resultSet = preparedStatement.executeQuery();

            // 4. 处理查询结果集
            while (resultSet.next()) {
                String dname = resultSet.getString("dname");
                String loc = resultSet.getString("loc");


                // 注意将 “双引号转换为单引号，因为在Java当中不可以嵌套多个双引号，除非是字符串的拼接
                // 所以使用 '单引号
                writer.println("    <body>");
                writer.println("  <h1>部门详情</h1>");
                writer.println("              部门编号: " + deptno + " <br>");
                writer.println("          部门名称: " + dname + "<br>");
                writer.println("     部门位置: " + loc + "<br>");
                writer.println("  <input type='button' value='后退' onclick='window.history.back()'  />");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 5. 释放资源
            DBUtil.close(connection, preparedStatement, resultSet);
        }


        writer.println("</body>");
        writer.println("</html>");
    }


    private void doDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charSet=UTF-8");
        PrintWriter writer = response.getWriter();
        request.setCharacterEncoding("UTF-8");

        // 思路:
        /*
        根据部门编号删除信息，
        删除成功，跳转回原来的部门列表页面
        删除失败，跳转删除失败的页面
         */

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        // 记录删除数据库的行数
        int count = 0;

        // 获取到前端提交的数据
        String deptno = request.getParameter("deptno");


        // 连接数据库进行删除操作

        try {
            // 1.注册驱动，连接数据库
            connection = DBUtil.getConnection();

            // 开启事务（取消自动提交机制）,实现可回滚
            connection.setAutoCommit(false);

            // 2. 预编译sql语句,sql测试
            String sql = "delete from dept where depton = ?"; // ? 占位符
            preparedStatement = connection.prepareStatement(sql);

            // 3. 填充占位符，真正的执行sql语句
            preparedStatement.setString(1, deptno);
            // 返回影响数据库的行数
            count = preparedStatement.executeUpdate();
            connection.commit();  // 手动提交数据
        } catch (SQLException e) {
            // 遇到异常回滚
            if (connection != null) {
                try {
                    // 事务的回滚
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(e);
        } finally {
            // 4. 释放资源
            // 因为这里是删除数据，没有查询操作，所以 没有 ResultSet 可以传null
            DBUtil.close(connection, preparedStatement, null);
        }

        if (count == 1) {
            // 删除成功
            // 仍然跳转到部门列表页面
            // 部门列表页面的显示需要执行另外一个Servlet，怎么办，可以使用跳转，不过这里最后是使用重定向
            // 注意：转发是在服务器间的，所以不要加“项目名” 而是 / + web.xml 映射的路径即可
            //request.getRequestDispatcher("/dept/list/").forward(request,response);

            // 优化：使用重定向机制 注意: 重定向是自发到前端的地址栏上的，前端所以需要指明项目名
            // 注意: request.getContextPath() 返回的根路径是，包含了 "/" 的
            response.sendRedirect(request.getContextPath() + "/dept/list/");
        } else {
            // 删除失败
            // web当中的 html资源，这里的 "/" 表示 web 目录
            //request.getRequestDispatcher("/error.html/").forward(request, response);

            // 优化，使用重定向
            response.sendRedirect(request.getContextPath() + "/error.html/");
        }
    }


    private void doModify(HttpServletRequest request, HttpServletResponse response) throws IOException {
         /*
        思路:
        获取到提交的数据信息，
        根据提交的信息，连接数据库修改
        修改成功，跳转到部门列表页面，
        修改失败：跳转到失败的页面
         */

        request.setCharacterEncoding("UTF-8");  // 设置获取的的信息的编码集
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        // 影响数据库的行数
        int count = 0;


        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");


        try {
            // 1. 注册驱动，连接数据库
            connection = DBUtil.getConnection();

            // 2. 获取到操作数据库的对象，预编译sql语句，sql测试
            String sql = "update dept set dname = ?,loc = ? where depton = ?";
            preparedStatement = connection.prepareStatement(sql);

            // 3. 填充占位符，真正执行sql语句
            // 从下标 1开始
            preparedStatement.setString(1, dname);
            preparedStatement.setString(2, loc);
            preparedStatement.setString(3, deptno);

            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 4. 释放资源，最后使用的优先被释放
            DBUtil.close(connection, preparedStatement, null);
        }

        if (count == 1) {
            // 更新成功
            // 跳转到部门列表页面（部门列表表面是通过java程序动态生成的，所以还需要再次执行另一个Servlet）
            // 转发是服务器内部的操作，“/” 不要加项目名
            // request.getRequestDispatcher("/dept/list/").forward(request,response);

            // 优化使用重定向,自发前端(需要指明项目名)
            response.sendRedirect(request.getContextPath() + "/dept/list/");

        } else {
            // 更新失败
            // request.getRequestDispatcher("/error.html").forward(request,response);

            // 优化重定向
            // request.getContextPath()  获取的根路径是，带了 "/" 的
            response.sendRedirect(request.getContextPath() + "/error.html");
        }
    }
}
