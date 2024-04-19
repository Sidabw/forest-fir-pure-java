package com.brew.home.thread.jstack;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

/**
 *
 * 测试使用jstack时，打印出来的线程状态，对应的实际代码是什么。
 * @author shaogz
 * @since 2024/4/19 18:43
 */
public class JstackThreadStateTest {

    private static final Logger logger = Logger.getLogger("JstackThreadStateTest");
    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            try {
                new Object().wait();
            } catch (Exception e) {
                logger.info("ERROR:" + Thread.currentThread().getName() + e.getMessage());
            }
        }, "thread-1");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
            try {
                blockingQueue.take();
            } catch (Exception e) {
                logger.info("ERROR:" + Thread.currentThread().getName() + e.getMessage());
            }
        }, "thread-2");
        thread2.start();

        Thread thread3 = new Thread(() -> {
            try {
                new Object().wait(20*1000);
            } catch (Exception e) {
                logger.info("ERROR:" + Thread.currentThread().getName() + e.getMessage());
            }
        }, "thread-1");
        thread3.start();
    }
}
