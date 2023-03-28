package com.RainbowSea.templateMethod;


/**
 * Teacher 和 Student 都是Person
 * 1.Person 就是模板方法设计模式当中的模板类
 * 2. day()方法就是模板方法设计模式当中的模板方法。
 * 模板类通常是一个抽象类，模板类当中的模板方法定义核心算法，这个方法通常是final 的（但也可以不是final的）
 * 模板类当中的抽象方法就是不确定实现的方法，这个不确定怎么实现的事情就交给子类去做了。
 *
 *
 */
public abstract class Person {  // 模板类通常是抽象类
        /**
         * 这个方法描述学生的一天
         */
        // 添加了final 之后，这个方法就无法被覆盖，这样核心算法也可以得到保护。
        // 模板方法：
        // 模板方法定义核心的算法骨架：具体的实现步骤可以延迟到子类当中去实现。
        // 核心算法一方面是得到了保护，不能被改变，另外一方面就是算法得到了重复使用。
        // 另外代码也得到了复用，因为算法中某些步骤的代码是固定的，这种固定的代码不会随着子类
        // 的变化而变换，这一部分代码可以写到模板当中，
        public final void day() {
            getUp();
            brushTeeth();
            eatFast();
            doSome();
        }

        // 其中的某些步骤：不会随着子类的变化而变化，这些代码可以写到父类中，得到代码的复用。
        public void getUp() {
            System.out.println("起床");
        }

        public void brushTeeth() {
            System.out.println("刷牙");
        }

        public void eatFast() {
            System.out.println("吃早饭");
        }


        // 这一步是要做，但是具体这一步怎么做，子类说的算。
        public abstract void doSome();  // 定义为抽象方法
}
