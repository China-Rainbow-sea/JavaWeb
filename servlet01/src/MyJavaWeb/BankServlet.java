package MyJavaWeb;

/**
 * 充当的角色为：webapp开发者
 * 只要我们webapp开发者的 XXXServlet 都要实现 Servlet 接口
 */
public class BankServlet implements Servlet{
    @Override
    public void service() {
        System.out.println("BankServlet is service...");
    }
}

