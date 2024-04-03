package com.brew.home.thread;

import java.util.concurrent.*;

public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        executorService.

        CompletableFuture.runAsync(()->{}).get(1, TimeUnit.SECONDS);
    }

    public static void test1() throws ExecutionException, InterruptedException {
        //虽然1.5 Future类提供了拿到线程执行结果的方式，但是太不优雅。没有回调、异步编程的味道。CompletableFuture做到了..

        //参考博客：https://colobu.com/2016/02/29/Java-CompletableFuture/

        //不练了..

        CompletableFuture<Object> c1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("come in");
            throw new RuntimeException("xxx");
        });
        //ExecutionException – if this future completed exceptionally
        System.out.println(c1.get());
        TimeUnit.SECONDS.sleep(10);
    }

}
