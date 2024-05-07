package com.brew.home.in_progress.timewheel;


import com.brew.home.in_progress.timewheel.i.TimerTask;

import java.util.concurrent.TimeUnit;

/**
 * @author shaogz
 * @since 2024/1/25 17:27
 */
public class TestIt {
    public static void main(String[] args) {

        final TimeWheel wheel = new TimeWheel(1, TimeUnit.SECONDS);
        wheel.createTimerTask(new TimerTask() {
            @Override
            public void run() {
                System.out.println(1111);
                wheel.createTimerTask(this, 4, TimeUnit.SECONDS);
            }
        }, 3, TimeUnit.SECONDS);
    }

}
