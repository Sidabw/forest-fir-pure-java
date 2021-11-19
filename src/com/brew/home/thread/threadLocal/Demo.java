/**
 * Copyright (C), 2018-2020, zenki.ai
 * FileName: Demo
 * Author:   feiyi
 * Date:     2020/3/4 7:32 AM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.brew.home.thread.threadLocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 〈一句话功能简述〉:
 * 〈〉
 *
 * @author feiyi
 * @create 2020/3/4
 * @since 1.0.0
 */
public class Demo {


    ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> {
        System.out.println("thread local init");
        return new SimpleDateFormat("");
    });

    ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws InterruptedException {
        Demo demo = new Demo();
        for (int i = 0; i < 20; i++) {
            demo.executorService.execute(() -> {
                demo.format(new Date());
                try {
                    System.out.println(Thread.currentThread().getId());
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            if (i == 10) {
                Thread.sleep(5000L);
                demo.threadLocal = ThreadLocal.withInitial(() -> {
                    System.out.println("thread local init ---- new ThreadLocal");
                    return new SimpleDateFormat("");
                });
            } else
                Thread.sleep(1500L);
        }
    }


    public String format(Date date) {
//        return threadLocal.get().format(date);
        SimpleDateFormat simpleDateFormat = threadLocal.get();
        return "hahah";
    }

}
