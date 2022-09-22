package com.brew.home.jvm.jmx;

import java.lang.management.*;
import java.util.List;

public class PrintJvmInfoByMxBean {

    public static void main(String[] args) {
        new PrintJvmInfoByMxBean().test1();
    }

    //对内存监控
    public void test1(){
        System.out.println(Runtime.getRuntime().maxMemory());
        System.out.println(Runtime.getRuntime().totalMemory());
        System.out.println(Runtime.getRuntime().freeMemory());

        MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        System.out.println(heapMemoryUsage.getInit());
        System.out.println(heapMemoryUsage.getUsed());
        System.out.println(heapMemoryUsage.getCommitted());
        System.out.println(heapMemoryUsage.getMax());

        MemoryUsage nonHeapMemoryUsage = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();
        System.out.println(nonHeapMemoryUsage.getInit());
        System.out.println(nonHeapMemoryUsage.getUsed());
        System.out.println(nonHeapMemoryUsage.getCommitted());
        System.out.println(nonHeapMemoryUsage.getMax());

        //即将被回收的对象个数
        int objectPendingFinalizationCount = ManagementFactory.getMemoryMXBean().getObjectPendingFinalizationCount();
        System.out.println(objectPendingFinalizationCount);

        //线程信息
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        int threadCount = threadMXBean.getThreadCount();
        ThreadInfo threadInfo = threadMXBean.getThreadInfo(threadMXBean.getAllThreadIds()[0]);
        System.out.println(threadCount);
        //这里打印的信息不全，实际里面东西比较多
        System.out.println(threadInfo);

        //gc信息
        List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean garbageCollectorMXBean : garbageCollectorMXBeans) {
            System.out.println(garbageCollectorMXBean.getName());
            System.out.println(garbageCollectorMXBean.getCollectionCount());
            System.out.println(garbageCollectorMXBean.getCollectionTime());
        }

    }
}
