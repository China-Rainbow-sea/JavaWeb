package com.rainbowSea.testThreadLocal;

import java.util.HashMap;
import java.util.Map;

public class MyThreadLocal<T> {

    /**
     * 所有需要和当前线程绑定的数据要放到整个容器当中
     */
    private Map<Thread,T> map = new HashMap<Thread,T>();


    /**
     * 向ThreadLocal 中绑定数据
     * 注意是：Thread.currentThread() 当前线程作为 key 存在
     */
    public void set(T t) {
        map.put(Thread.currentThread(),t);

    }


    /**
     * 向ThreadLocal 当中获取数据
     * 注意：获取到的是当前线程的Connection绑定的数据，
     */
    public T get() {
        // 通过 Thread.currentThread()当中线程作为key ，获取到对应的 value值
        return map.get(Thread.currentThread());

    }

    /**
     * 移除ThreadLocal当中数据
     * 注意：移除的是Thread.currentThread()当前线程作为key 存储的数据信息。
     */
    public void remove() {
        map.remove(Thread.currentThread());
    }
}
