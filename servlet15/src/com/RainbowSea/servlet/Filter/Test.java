package com.RainbowSea.servlet.Filter;


/**
 * 这个程序的问题：
 * 在编译阶段已经完全确定了调用关系：
 * 如果你想改变它们的调用顺序，必须修改以下Java源代码。
 * Java代码修改，需要重新编译，项目需要重新测试，项目需要重新发布，这是一个繁琐的过程。
 * 显然: 这种设计违背了 OCP 原则，(开闭原则: 对应扩展开放，对修改关闭
 *  对项目扩展我没有意见，但是你扩展的过程中如果修改了我之前写好的代码，这就是你的不对了。
 *  最好要达到的效果是，可以扩展，但是最好别改我之前协写好的代码。而是动态获取修改。)
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("main begin");
        m1();
        System.out.println("main end");
    }

    private static void m1() {
        System.out.println("m1 begin");
        m2();
        System.out.println("m1 end");
    }

    private static void m2() {
        System.out.println("m2 begin");
        m3();
        System.out.println("m2 end");
    }

    private static void m3() {
        System.out.println("目标正在执行中......");
    }
}
