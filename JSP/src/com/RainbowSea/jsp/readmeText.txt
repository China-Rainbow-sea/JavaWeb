在Servlet 类 当中编写前端代码HTML/CSS/JavaScript等前端代码，存在什么问题？
java程序中编写的前端代码，编写难度大，麻烦。
java程序中编写的前端代码，显然程序的耦合度非常高。
java程序中编写的前端代码，代码非常不美观。
java程序中编写前端代码，维护成本太高了。(非常难于维护)
修改小小的一个前端代码，只要改动，就需要重新编译Java代码，生成新的 class 文件，打一个新的war包
，重新发布
思考一下，如果是你的话，你怎么准备解决这个问题。、
思路很重要，使用什么样的思路去做，去解决这个问题。
上面的哪个Servlet（Java程序）能不能不写了，让机器自动生成，我们程序员，只需要写这个Servlet 程序中的
前端那段代码。然后让机器我们写的“前端代码”自动翻译生成“Servlet” 这种java程序，然后机器自动
将java程序编译生成“class”文件，然后再使用JVM调用这个class中的方法。


我的第一个jsp程序：
在WEB-INF 目录之外创建一个index.jps 文件，然后这个文件中没有任何内容，
将上面的项目部署之后，启动服务器，打开浏览器，访问地址。
http://127.0.0.1:8080/JSP/index.jsp 展现在大家面前是一个空白。
实际上访问以上的这个：index.jsp 底层执行的是: index_jsp.class这个java程序
这个index.jsp 会被tomcat 翻译生成index_jsp.java文件，然后tomcat 服务器又会将
index_jsp.java编译生成index_jsp.class
所以访问index.jsp，实际上执行的是index_jsp.class 中的方法。

重点：
jsp实际上就是一个Servlet
index.jsp 访问的时候，会自动翻译生成一个index_jsp.java 文件，会自动编译生成一个index_jsp.class文件
那么index_jsp这个就是一个类。
index_jsp 这个类继承了 HttpJspBase 这个类，而这个 HttpJspBase又是继承了HttpServlet 这个类
，所以 index_jsp类就是一个Servlet 类。
jsp的生命周期和Servlet 的生命周期是完全相同，完全就是一个东西， 没有任何实质上的区别。
jsp和Servlet 一样是都是单例的（假单例，就是真单例的构造器是私有话的）而这里的Servlet 不是私有化的
简单的说：JSP 就是一个翻译的引擎，将我们编写的前端标签html,css,javaScript 通过Java当中的 out.write()
写入到前端浏览器当中，写的时JSP，但是想的却是 java 代码

JSP 文件第一次访问的时候是比较慢的 ，为什么？
为什么大部分的运维人员在给客户演示项目的时候，为什么提前先把所有的jsp文件先访问了一遍
> 第一次比较麻烦:
 > 要把jsp文件翻译生成.java文件，
 再把.java源文件，要编译生成.class 文件。
 然后把通过class 去创建servlet 对象
 然后调用class 去创建servlet 对象
 然后调用servlet 对象的init 方法。
 最后调用 servlet对象当中的service()方法。


> 第二次就比较快了，为什么？
 > 因为第二次直接调用servlet对象的service()方法即可。对应的.class 文件已经生成好了。

JSP 是什么？
> jsp 是java程序。
> jsp 是: JavaServer Page 的缩写，（基于Java语言实现的服务器端的页面）
  Servlet 是javaEE的13个规范之一，那么JSP也是JavaEE的13个子规范之一。
  JSP是一套规范，所有的web容器/web服务器都是遵循之套规范的，都是按照这个规范进行的“翻译”
  每一个web容器/web服务器都会内置一个JSP翻译引擎。
  对JSP进行错误的调试的时候，还是要直接打开JSP文件对应的java文件，检查java代码，
开发JSP的最高境界：
      "眼前时JSP代码，但是脑袋中所呈现的时Java代码"

重点：
JSP的基本语法：
 > 在jsp文件中直接编写文字，都会自动被翻译到哪里？
 路径：Using CATALINA_BASE:   "C:\Users\huo\AppData\Local\JetBrains\IntelliJIdea2022.1\tomcat\4b6bbfbb-d520-498b-b8f2-090a7ad68f62
 翻译到 Servlet类的service()方法的 out.write("翻译到这里")，成为字符串，被Java程序
 当做普通的字符串打印输出到浏览器当中去。
 > 在 JSP 中编写的HTML,CSS代码，这些代码对于JSP来说只是一个普通的字符串，但是JSP把这个普通
 的字符串一旦输出到浏览器，浏览器会对html，css进行解释执行，展现一个效果。


JSP的 page 指令（这个指令后面再详细说明，这里先解决一个中文乱码问题：）解决响应时
的中文乱码问题：通过page指令来设置响应的内容类型，已经字符集。
在内容类型的最后面添加: charSet=UTF-8
<%page contentType="text/html;charSet=UTF-8">,表示响应内容类型是text/html 采用的字符集UTF-8

JSP 既然本质上是一个Servlet 的，那么JSP和Servlet 到底有什么区别:
 指责不同:
   Servlet 类的职责是: 收集数据: (Servlet的强项是: 逻辑处理，业务处理，然后连接数据库，获取/收集数据)
   JSP的职责是: 展示数据: （JSP的强项是做数据的显示）


怎么在JSP中编写java程序:
 <%java语句 %>
 在这个符号当中编写的被视为java程序，被翻译到Servlet 类的service()方法内部。
 注意：方法体当中，不可以写 private 权限修饰变量，不可以定义方法，不可以写static 静态代码块，不可以没有;
 在service() 方法当中编写的代码是有顺序的，方法体当中的代码要遵循自上而下的顺序依次逐行的执行。
 在同一个jsp当中 <%%> 这个符号可以出现多个。

<%! %>
在这个符号当中编写的java程序会自动翻译到Servlet 类当中,注意不是在 service()方法当中。
这个语法很少用，为什么呢 ？ 不建议使用，因为在service()方法外，也就是在Servlet类当中。
写的静态变量和实例变量，都存在线程安全问题，JSP就是servlet ，servlet 单例的，多线程并发
的环境下，这个静态变量和实例变量一旦有修改操作，必然会存在线程安全问题（所有对象共享，被多线程修改不同步）



JSP的输出语句:
怎么向浏览器输出一个java变量:
<%=%> 这个符号翻译为 不是将一个变量作为字符串的方式打印了，而是打印该变量的值了。
out.print(name);
什么时候使用<%=%> 输出呢？
> 当输出的内容中含有java的变量？输出的内容是一个动态的内容，不是一个死的字符串的时候。使用该方式；


JSP 基本语法总结：
JSP中直接编写普通字符串:
  > 翻译到service()方法体当中的，并翻译为 out.write("这里")
<%%>
 > 将java 程序填写到到service()方法体当中，里面是一条一条的java语句
<%!%>
 > 将 java 程序翻译，填写到Servlet类当中，不是在service()方法中 。
<%=%>
 > 将Java当中的变量的值翻译到service()方法当中，
 <%@ page  %> 指令


JSP文件的扩展名必须是 xxx.jsp 吗？
> jsp 文件的扩展名是可以配置的，不是固定的。
在D:\dev\apache-tomcat-10.0.12\conf\web.xml ，这个文档中配置jsp文件的映射路径
<!-- The mappings for the JSP servlet -->
    <servlet-mapping>
        <servlet-name>jsp</servlet-name>
        <url-pattern>*.jsp</url-pattern>
        <url-pattern>*.jspx</url-pattern>
    </servlet-mapping>

xxx.jsp 文件对于小猫咪来说，只是一个普通的文件，web容器会将xxx.jsp文件最终生成java程序，
最终调用的还是java对象相关的方法，真正执行的时候，和jsp文件就没有关系了。


包名 bean 是什么意思：
 javabean （Java的logo 是一杯冒着热气的咖啡，javabean被翻译为：咖啡豆）
 java 是一杯咖啡，咖啡又是由一粒一粒的咖啡豆研磨而成。
 > 有无参数构造方法 ，无参构造器，反射机制
 > 属性私有化
 > 对外提供公开的set和get方法
 > 实现java.io.Serializable 接口，反序列化
 > 重写 toString
 > 重写hashCode() + equals()方法 集合存储


