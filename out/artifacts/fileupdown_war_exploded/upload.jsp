<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <base href="<%=request.getContextPath()+"/"%>>">
    <style type="text/css">
        input[type="submit"] {
            outline: none;
            border-radius: 5px;
            cursor: pointer;
            background-color: #31B0D5;
            border: none;
            width: 70px;
            height: 35px;
            font-size: 20px;
        }

        img {
            border-radius: 50%;
        }

        form {
            position: relative;
            width: 200px;
            height: 200px;
        }

        input[type="file"] {
            position: absolute;
            left: 0;
            top: 0;
            height: 200px;
            opacity: 0;
            cursor: pointer;
        }
    </style>
    <script type="text/javascript">
        function prev(event) {
//获取展示图片的区域
            var img = document.getElementById("prevView");
//获取文件对象
            let file = event.files[0];
//获取文件阅读器
            let reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function () {
//给 img 的 src 设置图片 url
                img.setAttribute("src", this.result);
            }
        }
    </script>
</head>
<body>
<!-- 表单的 enctype 属性要设置为 multipart/form-data -->
<form action="fileUploadServlet" method="post" enctype="multipart/form-data">
    家居图: <img src="2.jpg" alt="" width="200" height="200" id="prevView"> <input type="file" name="pic" id=""
                                                                                value="2xxx.jpg" onchange="prev(this)"/>
    家居名: <input type="text" name="name"><br/> <input type="submit" value="上传"/>
</form>
</body>
</html>