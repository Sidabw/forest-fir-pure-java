package com.brew.home.queue.delay;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author shaogz
 */
public class DelayTest {

    public static void main(String[] args) throws InterruptedException {

        DelayQueue<ReplayDelayItem> replayDelayItems = new DelayQueue<>();
        replayDelayItems.put(new ReplayDelayItem(1, 1, 10, TimeUnit.SECONDS));
        replayDelayItems.put(new ReplayDelayItem(1, 2, 10, TimeUnit.SECONDS));
        replayDelayItems.put(new ReplayDelayItem(1, 11, 11, TimeUnit.SECONDS));

        while (true) {
            System.out.println("try take from delay queue");
            Thread.sleep(30000);
            ReplayDelayItem take = replayDelayItems.take();
            System.out.println("msg: " + take);
        }
    }
}
