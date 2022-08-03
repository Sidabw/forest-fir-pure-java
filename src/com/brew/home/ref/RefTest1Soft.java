package com.brew.home.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class RefTest1Soft {

    public static void main(String[] args) throws InterruptedException {
        //测试软引用-Xms50M -Xmx50M
        Object obj = new Object();
        ReferenceQueue<Object> q = new ReferenceQueue<>();
        SoftReference<Object> sf = new SoftReference<>(obj, q);
        obj = null; //被引用对象状态软可达（soft readable）

        System.out.println(q.poll());  //只有对象被回收了，其SoftReference才会放到ReferenceQueue中，此时是null
        System.out.println(sf.get()); // 能正常拿到
        System.gc();
        System.out.println(sf.get()); //依然能拿到

        Thread tmpThread = new Thread(() -> {
            //触发OOM
            List<Object> tmp = new ArrayList<>();
            while (true) {
                tmp.add(new byte[1024 * 1024]);
            }
        });
        tmpThread.start();
        //插队，插到主线程前面
        tmpThread.join();

        System.out.println(sf.get()); //此时发生过gc了，所以软引用的对象被清除了。

        //发生gc了，能poll到了
        SoftReference polled = (SoftReference)q.poll();
        System.out.println(polled);
        //还是null
        System.out.println(polled.get());
    }
}
