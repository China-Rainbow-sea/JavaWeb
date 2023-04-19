
会话机制 session

用户打开浏览器，进行一系列操作，然后最终将浏览器关闭，这个整个过程叫做：一次会话，会话在服务器
端也有一个对应的Java对象，这个Java对象叫做 session
回顾：什么是一次请求？， 用户在浏览器上点击一下，然后到页面停下来，可以粗略的认为是一次请求，
请求对应的服务器端的Java对象是 : request;

重点：
一个会话当中包含多次请求（一次会话对应N次请求）
在Java规范当中，session对应的类名为：HttpSession D:\dev\apache-tomcat-10.0.12-fulldocs\tomcat-10.0-doc\servletapi\jakarta\servlet\http
session 机制属于B/S结构的一部分，如果使用php语言开发的web项目，同样也是session这种机制的，session
机制实际上是一个规范，然后不同的语言对这种会话机制（session） 都有实现。

> session 对象主要的作用是：保存会话的状态的，（用户登录成功了，这是一种登录成功的状态，你怎么把登录成功的这种状态）
一直保存起来，使用 session 会话机制对象可以保留会话的状态。
 > 为什么需要session 对象来保存会话的状态

 因为：HTTP协议是一种无状态的协议。
 什么是无状态的：请求的时候，B（浏览器）和 S(服务器端) 是连接的，但是请求结束之后，连接就断了，
 为什么要这么做？ HTTP 协议为什么要设计成这个样子？> 因为这样的无状态协议，可以降低服务器的压力，
 请求的瞬间是连接的，请求结束之后，连接断开，这样服务器压力小。
 > 只要 B 浏览器端和 S (服务器端)断开了，那么关闭了浏览器这个动作，服务器知道吗？
   不知道的，服务器是不知道浏览器器的是关闭的。因为HTTP协议是无状态协议的。

 举例：
  张三打开一个浏览器 A ，李四打开一个浏览器B，访问服务器之后，在服务器端会生成：
   > 一个属于张三专属的session 对象
   > 一个属于李四专属的 session 对象，

  为什么不使用 request 对象保存会话状态呢？为什么不使用ServletContest 对象会保存会话状态。
  > request.setAttribute() 存，request.getAttribute()取，ServletContext()也有这个方法，
  request 请求域，ServletContext 应用域（一个weabpps 就只有一个xml 文件），
  request 是一次请求一个对象。
  ServletContext()对象是服务器启动的时候创建，服务器关闭的时候销毁，这个ServletContext 对象只有一个。
  同样的注意一点：就是服务器有的时候，并不是我们可以控制的，ServletContext() 是所有的共用的。
  ServletContext()对象的域作用域，太大的，所有的Servlet 共用的。
  request请求域（HttpServletRequest） ，session 会话域(HttpSession),application(ServletContext)
  作用的范围大小： request < session < SelectContext

  // 向会话域当中绑定数据:
  // session.setAttribute();
  // 从会话域当中取数据:


  HttpSession session = request.getSession(); 这行代码很神奇就是张三访问的时候，获取到的就是
  张三是 session 对象，李四访问的时候，获取到的就是李四的session 对象它的原理是什么？

  Session 的原理是：
  JSESSIONID=91FB105CF98AB2A0DC5B3CFDB16CE9FE  这个是以 Cookie的形式保存在浏览器的内存当中的
  浏览器只要关闭，这个 Cookie 就没有了，或者手动清楚 浏览器当中的 Cookie
  session 列表是一个 Map,map 的 key 是 sessionid，而 Map 当中的 value 是 session 对象
  用户第一次请求，服务器生成session 对象，同时生成 session 对应的 key 的 id ,将其 id
  发送给服务器
  用户第二次发请求的时候，自动将浏览器内存当中的存储到服务器响应给客户端是保存的 sessionid ,
  ,服务器根据id查找对应的 session 对象，
   关闭浏览器，内存消失，cookie 消失，或者是 手动清除 cookie 内容，则保存到对应 sessionid 就消失的，
   自然其中的客户端也就无法将 sessionid 发送给服务器了，上次同一个会话也就无法找到了，上次建立的
   会话从某种意义上说也就是结束销毁了（因为session 会话超时销毁机制的存在）
   session 超时销毁机制默认在 web.xml 配置的当为 30 分钟
   D:\dev\apache-tomcat-10.0.12\conf
   <!-- ==================== Default Session Configuration ================= -->
     <!-- You can set the default session timeout (in minutes) for all newly   -->
     <!-- created sessions by modifying the value below.                       -->

       <session-config>
           <session-timeout>30</session-timeout>
       </session-config>

   Cookie 禁用了，session 还能找到吗？
   cookie 禁用是什么意思，服务器正常发送cookie给服务器，但是浏览器不要，拒收了，并不是服务器不发了。
   找不到了，每一次请求都会获取到新的 session对象。

   Cookie 被禁用了，session 机制还能实现吗？
    > 可以，需要使用URL 重写机制。
    http://127.0.0.1:8080/servlet13/test/session;jsessionid=42BB984DECE0BEAF58C05C3922FAB92C
    注意是小写的jsessionid，同时注意是使用 ";" 分号分隔开来的。
    URL重写机制会提高开发者的成本。开发人员在编写任何请求路径的时候，后面都要添加一个sessionid(也就是 jsessionid)
    给开发带来了很大的难度，很大的成本，所以大部分的网站都是这样设计的，你要是禁用Cookie ,那你就不要用了。


    总结一下目前位置我们所了解的域对象:
     request 请求域(HttpServletRequest) 请求级别的
     session 会话域(HttpSerssion)  用户级别的
     application 应用域(对应的类名: ServletContext ) 应用级别的

     这三个域对象的大小关系：
     request < session < application
     他们三个域对象都有以下三个公共的的方法:
     setAttribute(向域当中绑定数据)
     getAttribute(从域当中获取到数据)
     removeAttribute(删除域当中的数据)

     使用原则: 尽量使用小的域。















