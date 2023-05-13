package com.RainbowSea.bank.exampleThreadlocal;


import java.util.HashMap;
import java.util.Map;

public class MyThreadLocal<T> {

    /**
     * 所有需要和当前线程绑定的数据要放到整个容器当中
     */
    private Map<Thread,T> map = new HashMap<Thread,T>();


    /**
     * 向ThreadLocal 中绑定数据
     */
    public void set(T t) {
        map.put(Thread.currentThread(),t);

    }


    /**
     * 向ThreadLocal 当中获取数据
     * 注意：获取到的是当前线程的Connection绑定的数据，
     */
    public T get() {
        return map.get(Thread.currentThread());

    }

    /**
     * 移除ThreadLocal当中数据
     */
    public void remove() {
        map.remove(Thread.currentThread());
    }
}
