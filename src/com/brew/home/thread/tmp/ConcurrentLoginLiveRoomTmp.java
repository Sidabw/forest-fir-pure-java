package com.brew.home.thread.tmp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author shaogz
 * @since 2024/3/26 09:55
 */
public class ConcurrentLoginLiveRoomTmp {

    public static void main(String[] args) throws InterruptedException {
        int workerCount = 5;
        CountDownLatch countDownLatch = new CountDownLatch(workerCount);
        for (int i = 0; i < workerCount; i++) {
            new Thread(new MyRunnable(countDownLatch)).start();
            countDownLatch.countDown();
        }

        TimeUnit.SECONDS.sleep(20);
    }

    public static class MyRunnable implements Runnable {

        private final CountDownLatch countDownLatch;

        public MyRunnable(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();

                //业务代码--start
                System.out.println("线程ID: " + Thread.currentThread().getId() + ".   时间:" +
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
                //业务代码--end

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
