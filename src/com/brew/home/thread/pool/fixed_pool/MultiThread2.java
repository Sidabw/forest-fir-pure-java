/**
 * Copyright (C), 2018-2020, zenki.ai
 * FileName: MultiThread2
 * Author:   feiyi
 * Date:     2020/1/2 7:40 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.brew.home.thread.pool.fixed_pool;

import java.util.concurrent.*;

/**
 * 〈一句话功能简述〉:
 * 〈〉
 *
 * @author feiyi
 * @date 2020/1/2
 * @since 1.0.0
 */
public class MultiThread2 {

    //线程池
    private static ThreadPoolExecutor blockingTestThreadPool = new ThreadPoolExecutor(
            2,
            2,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1));

    public static void main(String[] args) throws InterruptedException {
        new MultiThread2().test1();
        new MultiThread2().test2();
    }
    public void test1() throws InterruptedException {
        blockingTestThreadPool.shutdown();
        for (int i = 0; i < 6; i++) {
            int activeCount = blockingTestThreadPool.getActiveCount();
            System.out.println("activeCount : " + activeCount);
            int finalI = i;
            if (activeCount < 2)
//            if (activeCount < 3)
                blockingTestThreadPool.execute(() -> {
                    run(finalI);
                });
            else
                Thread.sleep(4000L);
        }
    }

    private void run(int i) {
        try {
            System.out.println(Thread.currentThread().getName() + " : run : " + i);
            Thread.sleep(10000L);
            System.out.println(Thread.currentThread().getName() + " : end : " + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test2() throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            try {
                int finalI = i;
                System.out.println(finalI);
                es.execute(() -> {
                    if (finalI % 2 == 0)
                        throw new RuntimeException("(finalI % 2 == 0");
                });
                Thread.sleep(1000L);
            } catch (Exception e) {
                System.out.println("sleep");
                Thread.sleep(1000L);
            }
        }
//        es.execute();
//        es.submit()
//        blockingTestThreadPool.submit()

    }
}
