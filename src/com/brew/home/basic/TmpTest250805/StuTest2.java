package com.brew.home.basic.TmpTest250805;

import java.util.concurrent.TimeUnit;

/**
 * @author shaogz
 * @since 2025/8/5 22:52
 */
public class StuTest2 {

    public static void main(String[] args) throws InterruptedException {
        StuTest stuTest = new StuTest();
        new Thread(() -> {
            stuTest.a();
        }).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("main thread sleep 1s finished.");
        new Thread(() -> {
            stuTest.b();
        }).start();
        TimeUnit.SECONDS.sleep(100);
    }
}

class StuTest {

    public synchronized void a() {
        System.out.println("a1....");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("a2....");
    }

    public void b() {
        System.out.println("b....");
    }
}
