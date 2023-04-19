package com.RainbowSea.servlet;

import com.RainbowSea.DBUtil.DBUtil;
import com.RainbowSea.bean.Dept;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 可以使用模糊查询 @WebServlet("/dept/*")
@WebServlet({"/dept/list", "/dept/detail", "/dept/delete","/dept/save","/dept/modify"})
public class DeptServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String servletPath = request.getServletPath();  // 获取到浏览器当中的uri

        // 获取session 这个 session 是不不需要新建的
        // 只是获取当前session ,获取不到这返回null,
        HttpSession session = request.getSession(false);  // 获取到服务器当中的session ，没有不会创建的


        /**
         * 说明这里我们通过 session 会话机制，判断用户是否登录过，如果用户没有登录就想要访问
         * 到其信息，不可以，因为我们这里判断了一次是否登录过，只有登录入过了，才会将中登录到
         * 用户名为 “username” 的信息存储到 session 会话当中，如果没有的话是查询不到的，返回的是 null
         * 需要注意的一点就是，我们的jsp 当中的内置对象，是会自动创建一个 session 会话对象的，但是
         * 因为这里我们进行了一个 双重的判断机制。注意：需要先将对应的 xx_jsp.java 生成才行。同时
         * 使用 <%@page session = false %> 指令的话，需要所有会被访问，生成的 Jsp 文件都需要设置。
         *
         *   jakarta.servlet.http.HttpSession session = null;
         *   session = pageContext.getSession();
         */
        if(session != null && session.getAttribute("username") != null) {
            // 双重的判断，一个是 session 会话域要存在，其次是 会话域当中存储了名为 "username" 的信息
            if ("/dept/list".equals(servletPath)) {
                doList(request, response);
            } else if ("/dept/detail".equals(servletPath)) {
                doDetail(request, response);
            } else if ("/dept/delete".equals(servletPath)) {
                doElete(request,response);
            } else if("/dept/save".equals(servletPath)) {
                doSave(request,response);
            } else if("/dept/modify".equals(servletPath)) {
                doModify(request,response);
            }
        } else {
            response.sendRedirect(request.getContextPath());  // 访问的web 站点的根即可，自动找到的是名为 index.jsp 的欢迎页面
        }



    }


    /**
     * 修改部门信息
     * @param request
     * @param response
     */
    private void doModify(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
            response.sendRedirect(request.getContextPath() + "/dept/list");

        }
    }


    /**
     * 保存部门信息
     * @param request
     * @param response
     */
    private void doSave(HttpServletRequest request, HttpServletResponse response) throws IOException {

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
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }
    }


    /**
     * 通过部门删除部门
     * @param request
     * @param response
     */
    private void doElete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");  // 设置获取的的信息的编码集
        // 获取到发送数据
        String deptno = request.getParameter("deptno");

         /*
        根据部门编号删除信息，
        删除成功，跳转回原来的部门列表页面
        删除失败，跳转删除失败的页面
         */

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        // 记录删除数据库的行数
        int count = 0;


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
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }
    }


    /**
     * 通过部门编号，查询部门的详情
     * @param request
     * @param response
     */
    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");  // 设置获取的的信息的编码集

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        // 获取到部门编号
        String dno = request.getParameter("dno");
        Dept dept = new Dept();

        // 获取到部门编号，获取部门信息，将部门信息收集好，然后跳转到JSP做页面展示


        try {
            // 2. 连接数据库，根据部门编号查询数据库
            // 1.注册驱动，连接数据库
            connection = DBUtil.getConnection();

            // 2. 预编译SQL语句,sql要测试
            String sql = "select dname,loc from dept where depton = ?";  // ? 占位符
            preparedStatement = connection.prepareStatement(sql);

            // 3. 填充占位符,真正执行sql语句
            preparedStatement.setString(1, dno);
            resultSet = preparedStatement.executeQuery();

            // 4. 处理查询结果集
            while (resultSet.next()) {
                String dname = resultSet.getString("dname");
                String loc = resultSet.getString("loc");

                // 封装对象(建议使用咖啡豆，因为只有一个对象)
                dept.setDeptno(dno);
                dept.setDname(dname);
                dept.setLoc(loc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 5. 释放资源
            DBUtil.close(connection, preparedStatement, resultSet);
        }

        // 这个咖啡豆只有一个，所以不需要袋子，只需要将这个咖啡豆放到request请求域当中，
        // 用于对应的 jsp显示
        request.setAttribute("dept", dept);
        //String sign = request.getParameter("f");

        /*if("m".equals(sign)) {

            // 转发：多个请求为一个请求(地址栏不会发生改变)
            // 注意: 该路径默认是从 web 开始找的 / 表示 web
            // 转发到修改页面
            request.getRequestDispatcher("/edit.jsp").forward(request,response);
        } else if("d".equals(sign)) {
            // 跳转到详情页面
            request.getRequestDispatcher("/detail.jsp").forward(request,response);
        }*/

        // 或者优化
        // 注意 无论是转发还是重定向都是从 “/” 开始的
        // request.getParameter()拿到的是 f=edit，还是f=detail 就是跳转到的哪个页面
        //<a href="<%=request.getContextPath()%>/dept/detail?f=edit&dno=<%=dept.getDeptno()%>">修改</a>
        //<a href="<%=request.getContextPath()%>/dept/detail?f=detail&dno=<%=dept.getDeptno()%>">详情</a>
        String forward = "/" + request.getParameter("f") + ".jsp";
        request.getRequestDispatcher(forward).forward(request,response);
    }


    /**
     * 连接数据库，查询所有的部门信息，将部门信息收集好，然后跳转到JSP页面展示
     *
     * @param request
     * @param response
     */
    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");  // 设置获取的的信息的编码集
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // 创建一个集合List 存储查询到的信息
        List<Dept> depts = new ArrayList<Dept>();


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

                Dept dept = new Dept(det, dname, loc);

                // 将部门对象放到List集合当中
                depts.add(dept);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            // 5. 关闭资源
            DBUtil.close(connection, preparedStatement, resultSet);
        }


        // 查询到数据，将数据提交给 list.jsp 显示数据
        // 将集合存储的数据放到请求域当中，用于其他Servlet 使用 jsp 也是Servelt
        request.setAttribute("depList", depts);

        // 转发(注意不要重定向)，重定向无法共用 request 请求域当中的数据
        // 转发路径，/ 默认是从 web 目录开始找的
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }
}
