package com.brew.home.util;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author shaogz 2023/5/31 08:22
 */
public class TimerTest {

    public static void main(String[] args) throws InterruptedException {
//        Timer timer = new Timer();
//        timer.schedule(new MyTimerTask(), 11*1000);


        TimerTest timerTest = new TimerTest();
        System.out.println("当前时间" + System.currentTimeMillis());
        synchronized (timerTest) {
            //A线程调用了小b实例的wait，那么当前线程，也就是A线程会立刻等待，直到：
            //小b实例的notify被调用了、有人调用了notifyAll、wait到点了
            timerTest.wait(5*1000);
        }

        System.out.println("当前时间" + System.currentTimeMillis());

        //本质是一个Timer实例维护了一个后台线程，所谓定时，就是调用的wait(10s)的方法，任务执行的先后顺序则是通过一个小顶堆实现的
        //Timer.java：Timer、TimerThread、TimerQueue、
        //TimerTask.java：TimerTask
    }
}

class MyTimerTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("coming in");
    }
}
