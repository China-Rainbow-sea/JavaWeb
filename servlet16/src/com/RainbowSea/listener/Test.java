package com.RainbowSea.listener;

public class Test {

    // 只要这个 Test 类(被加加载到了内存，这个事件)当中，该静态代码块就会被执行，并且只会仅仅只会执行一次。
    static {
        System.out.println("静态代码块被执行了");
    }

    public static void main(String[] args) {
        Test test = new Test();  // 实例化，该对象的时候，就会将该 Test 类加载到内存当中,(该Test 加载
        // 到内存当中，该事件触发，执行其中 Test 类当中定义的 静态代码块。 )
    }


}
