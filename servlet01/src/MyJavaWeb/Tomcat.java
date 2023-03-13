package MyJavaWeb;


import java.io.FileReader;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * 充当Tomcat服务器的开发者
 */
public class Tomcat {
    public static void main(String[] args) throws Exception {
        System.out.println("Tomcat 服务器启动成功，开始接收用户的访问...");

        // 简单的使用Scanner来模拟一下用户的请求
        // 用户访问服务器是通过浏览器上的“请求路径”
        // 也就是说用户请求路径不同，后台执行的Servlet 不同
        /*
            /userList   UserListServlet
            /login      UserLoginServlet
            /bank       BankServlet
         */

        System.out.print("请输入您访问的路径：");
        Scanner scanner = new Scanner(System.in);

        // 用户的请求路径：
        String key = scanner.nextLine();  // Tomcat 服务器已经获取到了用户的请求路径了

        // Tomcat 服务器应该通过用户的请求路径找到对应的 XXXServlet
        // 请求路径和XXXServlet 之间的关系应该由谁指定呢？ wedapps 的开发者
        // 对应Tomcat 服务器来说需要解析配置文件
        //ResourceBundle bundle = ResourceBundle.getBundle("web.properties");

        FileReader reader = new FileReader("servlet01/src/MyJavaWeb/web.properties");
        Properties properties = new Properties();
        properties.load(reader);
        reader.close();

        // 通过 key 获取value
        String className = properties.getProperty(key);

        // 通过反射机制创建对象，注意：需要无参构造器
        Class clazz = Class.forName(className);
        Object o = clazz.newInstance();

        //因为这里使用的是多态：所有的 XXXServlet 都实现了其Servlet 接口
        // 但是Tomcat 服务器的开发者知道: 你写的XXXServlet 一定实现了 Servlet 接口
        Servlet servlet = (Servlet) o;

        servlet.service();

    }
}
