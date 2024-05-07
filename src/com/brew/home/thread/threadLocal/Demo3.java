package com.brew.home.thread.threadLocal;

import java.util.concurrent.TimeUnit;

/**
 * 测试InheritableThreadLocal
 * <p> Java中没有子父线程之说。子线程里拿不到父线程，父线程里也拿不到所有的子线程。
 * <p> InheritableThreadLocal只能说是一个特殊的存在：创建线程时，把当前线程的thread local的值传递给要创建的线程（子线程）。
 * <p> 传递操作是在new thread的init方法里。
 * @author shaogz
 * @since 2024/5/6 16:29
 */
public class Demo3 implements Runnable{


    private static final InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

//    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set("123");
        System.out.println("----主线程取值，第一次" + threadLocal.get());
        Thread tt = new Thread(new Demo3());
        tt.start();

        TimeUnit.SECONDS.sleep(5);
        System.out.println("----主线程取值，第二次" + threadLocal.get());
    }

    @Override
    public void run() {
        System.out.println("----子线程获取值，第一次：" + threadLocal.get());
        threadLocal.set("456");
        System.out.println("----子线程获取值，第二次：" + threadLocal.get());
    }

}
