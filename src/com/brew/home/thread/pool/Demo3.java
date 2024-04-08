package com.brew.home.thread.pool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shaogz
 * @since 2024/3/1 07:56
 */
public class Demo3 {

    public static void main(String[] args) throws InterruptedException {
        //证明：3s执行一次的任务，但是任务耗时是5s、4s...0s的情况下，任务会在队列里等着，上一个执行完了接着执行
        //慢慢的，就恢复到3s执行一次了！
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(6);
        AtomicInteger ai = new AtomicInteger(5);
        AtomicInteger ai2 = new AtomicInteger(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        scheduledExecutor.scheduleAtFixedRate(()->{
            int a = ai2.incrementAndGet();
            if (a==10){
                throw new RuntimeException("xxx");
            }
            System.out.println(simpleDateFormat.format(new Date()) + "第多少次执行？ "+a);

            try {
                int b = ai.getAndDecrement();
                System.out.println("这次睡多久？" + b);
                TimeUnit.SECONDS.sleep(b);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }, 0, 3, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(1000);
    }
}
