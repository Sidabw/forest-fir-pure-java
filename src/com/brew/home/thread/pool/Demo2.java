package com.brew.home.thread.pool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author shaogz
 * @since 2024/1/9 00:05
 */
public class Demo2 {

    public final int a;

    private int value = 0;

    public Demo2(int a) {
        this.a = a;
    }

    public Demo2() {
        this.a = -1;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static void main(String[] args) throws Exception {
        Demo2 demo2 = new Demo2();

        //未有结论...
        CompletableFuture.runAsync(() -> {
            System.out.println(1);
            try {
                TimeUnit.SECONDS.sleep(120);
            }catch (Exception e) {

            }
        });

        System.out.println(1);
        TimeUnit.SECONDS.sleep(1200);




    }
}
