package com.brew.home.thread.jstack;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Logger;

/**
 *
 * 测试使用jstack时，打印出来的线程状态，对应的实际代码是什么。
 * <p> 1. 启动main方法
 * <p> 2. 快速jps -l 拿到pid
 * <p> 3. jstack pid
 * @author shaogz
 * @since 2024/4/19 18:43
 */
public class JstackThreadStateTest {

    private static final Logger logger = Logger.getLogger("JstackThreadStateTest");
    public static void main(String[] args) {
        Thread.currentThread().setName("thread-main");

        /*
         * part-1
         * WAITING (on object monitor). jstack info:
         * "thread-1" #20 prio=5 os_prio=31 tid=0x00007f834e008800 nid=0x6403 in Object.wait() [0x0000700003836000]
         *  java.lang.Thread.State: WAITING (on object monitor)
         */
        Thread thread1 = new Thread(() -> {
            try {
                Object obj1 = new Object();
                synchronized (obj1) {
                    obj1.wait();
                }
            } catch (Exception e) {
                logger.info("ERROR:" + Thread.currentThread().getName() + e.getMessage());
            }
        }, "thread-1");
        thread1.start();

        /*
         * part-2
         * WAITING (parking)
         * "thread-2" #21 prio=5 os_prio=31 tid=0x00007f834e094800 nid=0x6503 waiting on condition [0x0000700003939000]
         *  java.lang.Thread.State: WAITING (parking)
         */
        Thread thread2 = new Thread(() -> {
            LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
            try {
                blockingQueue.take();
            } catch (Exception e) {
                logger.info("ERROR:" + Thread.currentThread().getName() + e.getMessage());
            }
        }, "thread-2");
        thread2.start();

        /*
         * part-3
         * TIMED_WAITING (on object monitor)
         * "thread-3" #22 prio=5 os_prio=31 tid=0x00007f834e095800 nid=0x6603 in Object.wait() [0x0000700003a3c000]
         *  java.lang.Thread.State: TIMED_WAITING (on object monitor)
         */
        Thread thread3 = new Thread(() -> {
            try {
                Object obj2 = new Object();
                synchronized (obj2) {
                    obj2.wait(20*1000);
                }
            } catch (Exception e) {
                logger.info("ERROR:" + Thread.currentThread().getName() + e.getMessage());
            }
        }, "thread-3");
        thread3.start();

        /*
         * part-4
         * TIMED_WAITING (parking)
         * "thread-4" #23 prio=5 os_prio=31 tid=0x00007f8358013800 nid=0x6703 waiting on condition [0x0000700003b3f000]
         *  java.lang.Thread.State: TIMED_WAITING (parking)
         */
        new Thread(()->{
            LockSupport.parkNanos(new Object(), TimeUnit.SECONDS.toNanos(20));
        }, "thread-4").start();

        /**
         * part-5
         * BLOCKED (on object monitor)
         * "thread-5" #24 prio=5 os_prio=31 tid=0x00007f78fd89b000 nid=0x9503 waiting for monitor entry [0x0000700005779000]
         *  java.lang.Thread.State: BLOCKED (on object monitor)
         */
        Object obj1 = new Object();
        new Thread(()->{
            //先睡一会，保证主线程能拿到锁
            LockSupport.parkNanos(new Object(), TimeUnit.SECONDS.toNanos(5));
            synchronized (obj1) {
                logger.info("got it");
            }
        }, "thread-5").start();

        /**
         * part-6
         * TIMED_WAITING (sleeping)
         * "thread-main" #1 prio=5 os_prio=31 tid=0x00007f834f80d000 nid=0xe03 waiting on condition [0x00007000013c7000]
         *  java.lang.Thread.State: TIMED_WAITING (sleeping)
         */
        try {
            synchronized (obj1) {
                logger.info("got it");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            }
        } catch (Exception e) {
            logger.info("ERROR:" + Thread.currentThread().getName() + e.getMessage());
        }
    }
}
