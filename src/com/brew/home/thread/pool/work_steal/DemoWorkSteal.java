package com.brew.home.thread.pool.work_steal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author shaogz
 * @since 2024/1/9 00:05
 */
public class DemoWorkSteal {


    public static void main(String[] args) throws Exception {

        //以下测试证明：使用work steal pool时，提交任务不会阻塞
        //因为其本身也有一个WorkQueue[]存放等待执行的任务。
        //所以不建议使用CompletableFuture.runAsync的原因就只剩下：场景和模糊的执行机制
        //100的原因是本机是20核心，所以得大一点...
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0 ; i < 100; i++) {
            System.out.println(simpleDateFormat.format(new Date()) + " 提交任务 " + i);
            int finalI = i;
            CompletableFuture.runAsync(() -> {
                System.out.println(simpleDateFormat.format(new Date()) + " 任务开始执行 " + finalI);
                try {
                    TimeUnit.SECONDS.sleep(120);
                }catch (Exception e) {
                }
                System.out.println(simpleDateFormat.format(new Date()) + " 任务执行完毕 " + finalI);
            });
        }

        TimeUnit.SECONDS.sleep(1200);
        System.out.println("ALL DONE!");

    }
}
