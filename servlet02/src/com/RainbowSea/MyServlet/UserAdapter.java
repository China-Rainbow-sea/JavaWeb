package com.RainbowSea.MyServlet;


// UserServlet 类的适配器

/**
 * 需要适配的方法：就定义为 abstract 抽象方法，这样
 * 子类继承这个抽象类，就只需要重写其中的 abstract 抽象方法了，其他的就有这个适配器父类重写了。
 */
public abstract class UserAdapter implements MyInterface{
    @Override
    public void m1() {

    }

    @Override
    public void m2() {

    }

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
    public abstract void cour() ;
}
