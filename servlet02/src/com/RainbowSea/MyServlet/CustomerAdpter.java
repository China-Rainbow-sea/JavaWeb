package com.RainbowSea.MyServlet;

// CustomerService 专用的适配器：
/*
需要多次使用的方法，被充当为适配器，该适配器类将需要多次使用的方法定义为 abstract 抽象放
这样继承该适配器的子类，就只需要重写该适配器的 abstract 定义为的抽象方法了。
 */
public abstract class CustomerAdpter implements MyInterface {
    @Override
    public void m1() {

    }

    @Override
    public abstract void m2();

    @Override
    public void m3() {

    }

    @Override
    public void m4() {

    }

    @Override
    public void m5() {

    }

    @Override
    public void cour() {

    }
}
