package com.RainbowSea.templateMethod;

public class Test {
    public static void main(String[] args) {
        Person p1 = new Student(); // 多态
        p1.day();

        System.out.println("******************************");
        Person p2 = new Teacher();
        p2.day();


    }


    public static void main1(String[] args) {
        Student student = new Student();
        student.day();
        System.out.println("*******************************");

        Teacher teacher = new Teacher();
        teacher.day();

    }



}
