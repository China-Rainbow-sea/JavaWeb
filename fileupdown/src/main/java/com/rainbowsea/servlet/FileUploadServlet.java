package com.rainbowsea.servlet;


import com.rainbowsea.utils.WebUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import java.nio.file.attribute.FileTime;
import java.util.List;
import java.util.UUID;

public class FileUploadServlet extends HttpServlet {

    /*
    1. 判断是不是一个文件传单
    2. 判断表单提交的各个表单项是什么类型
    3. 如果是一个普通的表单项，就按照文本的方式来处理
    4. 如果是一个文件表单项(二进制数据)，使用 IO技术进行处理
    5. 把表单提交的文件数据，保存到你指定的服务端的某个目录
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        //System.out.println("被调用了");


        // 1. 判断是不是 文件表单(enctype="multipart/form-data")
        if (ServletFileUpload.isMultipartContent(request)) {
            //System.out.println("OK");
            // 2. 创建 DiskFileItemFactory 对象，用于构建一个解析上传数据的工具对象
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            // 3. 创建一个解析上传数据的工具对象
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

            // 4. 关键的地方 servletFileUpload 对象可以把表单提交的数据 text/ 文件
            // 将其封装到 FileItem 文件项中
            // 韩老师的编程心得体会：如果我们不知道一个对象是什么结构
            // 可以：1.输出该对象，2 debug 测试
            try {
                List<FileItem> list = servletFileUpload.parseRequest(request);
                //System.out.println("List ==>" + list);
                // 遍历，并分别处理=> 自然思路
                for (FileItem fileItem : list) {
                    // java.lang.ClassCastException: org.apache.commons.fileupload.disk.DiskFileItem cannot be cast to java.nio.file.attribute.FileTime
                    //System.out.println("fileItem == >" + fileItem);
                    if (fileItem.isFormField()) { // 如果是 true 就是文本 input text
                        String name = fileItem.getString("utf-8");
                        System.out.println("图片名称: " + name);

                    } else { // 是一个文件
                        // 获取上传的文件的名字:
                        String name = fileItem.getName();
                        System.out.println("上传的文件名: " + name);

                        // 把这个上传到服务器的 temp 下的文件保存到你指定的目录
                        // 1. 指定一个目录，就是我们网站工作目录下
                        String filePath = "/upload/";

                        // 2. 获取到完整目录[io/servlet基础]
                        String fileRealPath = request.getServletContext().getRealPath(filePath);

                        System.out.println("fileRealpath = " + fileRealPath);

                        // 3. 创建这个上传的目录=> 创建目录 => Java对象
                        // 为了防止大量的目录创建，可以更加日期时间进行创建多个目录
                        File fileRealPathDirectory = new File(fileRealPath + WebUtils.getYearMonthDay());
                        if (!fileRealPathDirectory.exists()) {  // 不存在创建
                            fileRealPathDirectory.mkdirs(); // 创建

                        }

                        // 解决接收到文件名是中文乱码问题
                        servletFileUpload.setHeaderEncoding("utf-8");
                        // 4. 将文件拷贝到 fileRealPathDirectory 目录
                        // 构建一个上传文件的完整路径：目录 + 文件名
                        // 有时-》上传失败了，可能是目录的问题 ，加上 “/”
                        // 文本被替换覆盖的问题，我们也一个工具类，让文件名不重复
                        // 对上传的文件名进行处理，前面增加一个前缀，保证是唯一即可
                        name = UUID.randomUUID().toString() + "_" + System.currentTimeMillis() + "_" + name;
                        String fileFullPath = fileRealPathDirectory + "/" + name;
                        fileItem.write(new File(fileFullPath));

                        // 提示信息
                        response.setContentType("text/html;charset=utf-8");
                        response.getWriter().write("上传成功");

                    }
                }
            } catch (FileUploadException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("不是文件表单...");
        }
    }

}
