package com.RainbowSea.filter;

public class Test {
    public static void main(String[] args) {
        m1();
    }

    private static void m1() {
        System.out.println("m1() begin ");
        m2();
        System.out.println("m1() end ");
    }

    private static void m2() {
        System.out.println("m2() begin ");
        m3();
        System.out.println("m2() end ");
    }

    private static void m3() {
        System.out.println("m3() begin ");

        System.out.println("m3() end ");
    }
}
