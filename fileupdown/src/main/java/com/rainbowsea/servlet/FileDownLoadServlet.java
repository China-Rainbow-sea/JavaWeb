package com.rainbowsea.servlet;

import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

public class FileDownLoadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("被调用");

        // 1. 先准备要下载的文件（假定这些文件时公共的资源）
        // 重要: 保证当我们的 tomcat 启动后，在工作目录下 有 out 有 download 文件夹，并且
        // 有可供下载的文件！！
        // 再次说明：如果你没有看到你创建的 download在工作目录 out下 rebuild project -> restart proj

        // 2. 获取到要下载的文件的名字
        request.setCharacterEncoding("utf-8");
        String downLoadFileName = request.getParameter("name");
        System.out.println("downLoadFileName = " + downLoadFileName);

        // 3. 给 http 响应，设置响应头 Content-Type,就是文件的MIME
        // 通过 servletContext 来获取
        ServletContext servletContext = request.getServletContext();
        String downLoadPath = "/download/";  // 服务器资源图片，存放路径
        String downLoadFileFullPath = downLoadPath + downLoadFileName;
        String mimeType = servletContext.getMimeType(downLoadFileFullPath);
        System.out.println("mimeType = " + mimeType);
        response.setContentType(mimeType);

        // 4. 给http响应，设置响应头Content-Dispostion
        // 这里考虑的细节比较多，比如不同的浏览器写法不一样，考虑编码
        // ff 是文件名中文需要 base64, 而 ie/chrome 是 URL编码
        // 这里我们不需要同学门机制，只需知道原理
        if(request.getHeader("User-Agent").contains("Firefox")) {
            // 火狐浏览器的设置 为 Base64编码
            response.setHeader("Content-Disposition","attachment; filename==?UTF-8?B?" +
                    new BASE64Encoder().encode(downLoadFileName.getBytes("UTF-8")));
        } else {
            // 其他(主流ie/chrome) 使用 URL编码操作
            response.setHeader("Content-Disposition","attachment; filename=" +
                    URLEncoder.encode(downLoadFileName,"UTF-8"));
        }

        // 5. 读取下面的文件数据，返回给客户端
        // (1)创建一个和要下载的文件，关联的输入流
        InputStream resourceAsStream = servletContext.getResourceAsStream(downLoadFileFullPath);

        // (2) 得到返回数据的输出流{因为返回文件大多数是二进制(字节)，IO Java基础}
        ServletOutputStream outputStream = response.getOutputStream();

        // (3) 使用工具类，将输入流关联的文件，对拷到输出流，并返回给客户端/浏览器
        // 注意是: import org.apache.commons.io.IOUtils; 包下的
        IOUtils.copy(resourceAsStream,outputStream);

    }
}
