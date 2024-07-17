有时-》上传失败了，可能是目录的问题 ，加上 “/”

// 有时-》上传失败了，可能是目录的问题 ，加上 “/”
// 为了防止大量的目录创建，可以更加日期时间进行创建多个目录
File fileRealPathDirectory = new File(fileRealPath+ WebUtils.getYearMonthDay());
String fileFullPath =  fileRealPathDirectory +"/"+ WebUtils.getYearMonthDay();

    public static String  getYearMonthDay() {
        // 如何得到当前的日期-》Java基础 日期，三代类
        LocalDateTime localDateTime = LocalDateTime.now();
        int year = localDateTime.getYear();
        int monthValue = localDateTime.getMonthValue();
        int dayOfMonth = localDateTime.getDayOfMonth();
        String yearMonthDay = year + "-" + monthValue + "-" + dayOfMonth;

        return yearMonthDay;
    }


// 有时-》上传失败了，可能是目录的问题 ，加上 “/”
// 文本被替换覆盖的问题，我们也一个工具类，让文件名不重复
// UUID.randomUUID().toString() 哈希不重复值
// System.currentTimeMillis() 获取当当前系统时间毫秒级别的
// 对上传的文件名进行处理，前面增加一个前缀，保证是唯一即可
// 同时使用特定的 "_" 符号进行分割，用于后续可能需要拿到文件名，最方便使用
name = UUID.randomUUID().toString() + "_" + System.currentTimeMillis() + "_" + name;
String fileFullPath = fileRealPathDirectory + "/" + name;


文件上传功能，在项目中建议有限制的使用，一般用在头像，证明，合同，产品展示等，如果不加限制，会造成服务
器空间被大量占用{比如：B站评论，就不能传图片，微信发1次朋友圈最多9张图等...}

文件上传，创建 web/upload 的文件夹，在tomcat 启动时，没有在 out 目录下 创建 对应的upload文件
原因是 tomcat 对应空目录是不会在 out 工作目录下创建对应目录的，所以，只需在 upload目录下，放一个文件即可，
这个Idea + tomcat 的问题，实际开发不会存在。


# 文件下载
响应头
> 1. Content-Disposition: 表示下载的数据的展示方式，比如是内联形式（网页形式或者网页一部分）
> 或者是文件下载方式 attachment
> 2. Content-Type: 指定返回数据的类型 MIME  ————》http 协议的内容

响应体:
1. 在网络传输时是图片的原生数据（按照浏览器下载的编码）
2. 这个图片时下载后查看到的，也就是浏览器本身做了解析


如果你没有看到你创建的 download在工作目录 out下 rebuild project -> restart proj

文件下载，比较麻烦的就是不同浏览器文件名中文处理，因此，在代码中，需要针对不同的浏览器做处理

// 3. 对于网站的文件，很多文件使用另存为即可下载，对于大文件（文档，视频）会使用专业的下载工具（迅雷，百度网盘等）

对于不同的浏览器，在把文件下载完毕后，处理的方式不一样，有些是直接打开文件，有些是
将文件下载到本地/下载目录