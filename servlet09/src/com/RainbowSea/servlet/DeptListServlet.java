package com.RainbowSea.servlet;

import com.RainbowSea.DBUtil.DBUtil;
import jakarta.servlet.ServletException;
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
 * 部门列表
 */
public class DeptListServlet extends HttpServlet {

    /*
    说明：这里使用了doGet,和 goPost 的原因是，我们前端的 DeptSaveServlet 的新增部门，
    的请求是doPost,从 doPost 请求  "转发"出来的同样是 doPost请求的，而 重定向就是doGet请求了，无论是doPost,doGet请求都是
    所以这里为了，处理接受到 DeptSaveServlet 的新增部门的 "转发"请求，写了一个doPost 请求处理
     */


    // 优化，将转发，替换成了 重定向的机制，（重定向的机制）是自发到浏览器前端的地址栏上的，后自发的执行
    // 地址栏上是 doGet 请求的，就不需要 doPost 请求了。
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        doGet(request, response); // 调用本身这里的doGet()请求

    }

    /*
        因为我们前端使用的是 <a>超链接，是goGet请求所以，
        前后端的请求保持一致。
         */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
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
                writer.print("					<a href='"+contextPath+"/dept/edit?deptno="+det+"'>修改</a>");
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
}
