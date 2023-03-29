package com.RainbowSea.templateMethod;

public abstract class Person {

    public final void day() {
        getUp();
        brushTeeth();
        eatFast();
        doSome();
    }

    public void getUp() {
        System.out.println("起床");
    }

    public void brushTeeth() {
        System.out.println("刷牙");
    }

    public void eatFast() {
        System.out.println("吃早饭");
    }


    public abstract void doSome();
}
