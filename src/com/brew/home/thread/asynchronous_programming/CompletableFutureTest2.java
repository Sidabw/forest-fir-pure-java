package com.brew.home.thread.asynchronous_programming;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author shaogz
 * @since 2025/8/14 19:27
 */
public class CompletableFutureTest2 {
    // 自定义线程池（建议业务中使用自定义线程池，避免默认线程池的隐患）
    private static final ExecutorService executor = Executors.newFixedThreadPool(5);

    // 模拟查询商品基本信息（如名称、描述）
    private String queryProductBaseInfo(Long productId) {
        try {
            TimeUnit.MILLISECONDS.sleep(300); // 模拟耗时操作
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "商品ID: " + productId + ", 名称: 智能手机";
    }

    // 模拟查询库存
    private Integer queryStock(Long productId) {
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 100; // 库存100件
    }

    // 模拟查询价格
    private Double queryPrice(Long productId) {
        try {
            TimeUnit.MILLISECONDS.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 3999.99; // 价格
    }

    // 聚合商品详情
    public String getProductDetail(Long productId) {
        // 1. 并行执行三个异步任务
        CompletableFuture<String> baseInfoFuture = CompletableFuture.supplyAsync(
                () -> queryProductBaseInfo(productId), executor);

        CompletableFuture<Integer> stockFuture = CompletableFuture.supplyAsync(
                () -> queryStock(productId), executor);

        CompletableFuture<Double> priceFuture = CompletableFuture.supplyAsync(
                () -> queryPrice(productId), executor);

        // 2. 等待所有任务完成后聚合结果
        return CompletableFuture.allOf(baseInfoFuture, stockFuture, priceFuture)
                .thenApply(v -> {
                    //调get也可以，但是会抛受检异常，外边就得写try catch，lambda中更推荐用join
                    String baseInfo = baseInfoFuture.join();
                    int stock = stockFuture.join();
                    double price = priceFuture.join();
                    return String.format("%s, 库存: %d件, 价格: %.2f元", baseInfo, stock, price);
                })
                .exceptionally(ex -> {
                    // 处理异常（如服务调用失败时返回默认信息）
                    return "获取商品详情失败: " + ex.getMessage();
                })
                .join(); // 阻塞获取最终结果（实际业务中可根据需求选择非阻塞方式）
    }

    public static void main(String[] args) {
        //执行耗时远小于串行执行的750ms
        CompletableFutureTest2 service = new CompletableFutureTest2();
        long start = System.currentTimeMillis();
        String detail = service.getProductDetail(1001L);
        long end = System.currentTimeMillis();
        System.out.println("商品详情: " + detail);
        System.out.println("耗时: " + (end - start) + "ms"); // 总耗时≈最长任务耗时（约300ms）
        executor.shutdown();
    }
}
