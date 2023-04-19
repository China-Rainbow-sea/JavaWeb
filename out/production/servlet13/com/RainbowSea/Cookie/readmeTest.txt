Cookie 机制：
session 的实现原理中，每一个session 对象都会关联一个sessionld,例如：
Cookie: JSESSIONID=CAED745C8D69B0AC7F903BFBC30934F0
以上的这个键值对数据其实就是Cookie 对象
对于session 关联的cookie 来说，这个Cookie 是被保存在浏览器的“运行内存”当中。
只要浏览器不关闭，用户再次发送请求的时候，会自动将运行内存中的 Cookie发送给服务器
例如：这个Cookie: JSESSIONID=CAED745C8D69B0AC7F903BFBC30934F0 就会再次发送给服务器。
服务器就是根据CAED745C8D69B0AC7F903BFBC30934F0这个值来找到对应的 session 对象的。

Cookie 怎么生成的，cookie 保存在什么地方。cookie 有啥用？，浏览器什么时候会发送Cookie,
发送哪些 cookie 给服务器 ？？？？？？

Cookie 最终是保存在浏览器客户端上的。
> 可以保存在运行内存中，(浏览器只要关闭Cookie就消失了)
> 也可以保存在硬盘文件中 (永久保存)

Cookie 有啥用?
> cookie 和 session 机制其实都是为了保存会话的状态。(http 协议是无状态协议)
> cookie 是将会话的状态保存在浏览器客户端上的。(cookie 数据存储在浏览器客户端上的)
> session 是将会话的状态保存在服务器端上。 (session对象是存储在服务器上的)
> 注意这两者的区别。

什么要有 Cookie 和 session 机制呢？因为HTTP协议是无状态，无连接协议。

Cookie 经典案例:
京东案例，在未登录的情况下，向购物车中放几件商品，然后关闭商城，再次打开浏览器，访问
京东商城的时候，购物车中的商品还在，这是怎么做到的。我没有登录，为什么购物车还有商品呢？
> 将购物车中的商品编号放到 Cookie 当中，Cookie 保存在硬盘文件当中，这样即便关闭
浏览器，硬盘上的Cookie 还在，下一次再次打开京东商城的时候，查看购物车的时候，会自动读取
本地硬盘中存储的Cookie 拿到商品编号，动态展示购物车中的商品。
> 京东存储购物车中商品的 Cookie 可能是这样的: productlds=xxxx;yyy,zzz,kkkk

126 邮箱中有一个功能：十天内免登录:
这个功能也是需要Cookie来实现的。
怎么实现呢？
 > 用户输入正确的用户名和密码，并且同时选择十天内免登录，登录成功后，浏览器客户端会保存一个Cookie,
 这个Cookie中保存了用户名和密码等信息，这个Cookie是保存在硬盘文件当中的。十天有效，在十天内用户
 再次访问126的时候，浏览器自动提交 126的关联的Cookie 给服务器，服务器接收到Cookie 之后，
 获取用户名和密码，验证，通过之后，自动登录成功。

> 怎么实现的呢？
  用户输入正确的用户名和密码，并且同时选择十天内免登录。登录成功后。浏览器客户端会保存一个
  Cookie,这个Cookie 中保存了用户名和密码等信息，这个Cookie 是保存在硬盘文件当中，十条有效，
  在十天内用户再次访问126的时候，浏览器自动提交 126关联的Cookie 给服务器，服务器接收到Cookie之后，
  获取用户名和密码，验证，通过之后，自动登录成功。

  怎么让Cookie 失效？
    > 十天过后自动失效。
    > 或者改密码
    > 或者在客户端浏览器上清除Cookie

  Cookie 机制和Session 机制其实都不属于java中的机制，实际上Cookie 机制和session 机制都是HTTP协议的
  一部分，PHP并发中也有Cookie 和session 机制，只要是你做web开发，不管是什么编程语言，cookie 和session机制都是需要的。
 HTTP协议中规定了：任何一个Cookie都是由name和value组成的，name和 value 都是字符串类型的
 file:///D:/dev/apache-tomcat-10.0.12-fulldocs/tomcat-10.0-doc/servletapi/jakarta/servlet/http/Cookie.html
 file:///D:/dev/apache-tomcat-10.0.12-fulldocs/tomcat-10.0-doc/servletapi/jakarta/servlet/http/HttpServletResponse.html#addCookie(jakarta.servlet.http.Cookie)
 在Java的Servlet 中，对Cookie 提供了哪些支持呢？
 提供了一个Cookie类来专门表示Cookie数据。jakarta.servlet.http.Cookie;
 Java程序怎么把Cookie 数据发送给浏览器呢？response.addCookie(cookie); cookie 信息最初是从服务器产生的，服务器响应给客户端，客户端存储起来。
 在HTTP协议中是这样规定的：当浏览器发送请求的时候，会自动携带改path下的cookie数据给服务器。URL


 关于Cookie 的有效时间:
  cookie.setMaxAge(60*60) 设置cookie 在一小时之后失效。
 没有设置有效时间:默认保存在浏览器的运行内存中，浏览器关闭则cookie 消失。
 只要设置cookie 的有效时间 > 0 这个cookie 则一定会存储到硬盘文件当中。
 设置cookie 的有效时间 = 0 表示：

 设置cookie 的有效时间 < 0 表示


 关于cookie的path,cookie 关联的路径:
 假设现在发送的请求路径是:"http://localhost:8080/servelt13/cooke/fasd" 生成的 cookie 信息，如果cookie
 没有设置path,默认的path是什么？
 > 默认的path是: http://localhost:8080/servelt13/cooke以及它的子路径的子路径(就算该路径不存在资源也会)
 将其cookie信息发送给服务器，只要在该cooke的范围内就可以，如果不是在cooke的范围呢，而是servlet13/dfasfa一个不存在的资源且没有设置
 cookie 则不会发送cookie信息给服务器，注意：需要是在cookie 生成的路径下的以及其子路径下。


修改我们上次的oa项目：实现是10 天免登录的效果。
先实现登录功能
  > 登录成功 跳转到部门列表页面
  登录失败：
  > 跳转到登录失败页面

 修改前端页面:
 > 在登录页面给一个复选框，复选框后面给上一句话：十天内免登录
 > 用户选择复选框，表示要支持十天免登录。
 > 用户没有选择复选框，表示用户不想使用十天内免登录的功能。
 > 修改 Servlet 中的 login 方法
  > 如果用户登录成功了，并且用户登录时选择了 10 天内免登录功能，
  这个时候应该在Servlet的login方法中创建cookie，用来存储用户和密码，并且设置cookie 关联路径，
  并设置cookie的响应时间（存储到客户端的硬盘当中去）给浏览器（浏览器将其自动保存在硬盘文件当中10 天）

 > 用户再次访问该网站的时候，访问这个网站的首页的时候，有两个走向，
   > 要么跳转到部门列表页面.
   > 要么跳转到登录以页面
   以上分别有两个走向，这显然时需要编写Java程序进行控制的。




