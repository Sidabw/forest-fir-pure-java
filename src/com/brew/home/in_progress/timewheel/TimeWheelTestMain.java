package com.brew.home.in_progress.timewheel;


import java.util.concurrent.TimeUnit;

/**
 * @author shaogz
 * @since 2024/1/25 17:27
 */
public class TimeWheelTestMain {
    public static void main(String[] args) {

        TimeWheel wheel = new TimeWheel(1, TimeUnit.SECONDS);
        wheel.createTimerTask(() -> {
            System.out.println(1111);
//                wheel.createTimerTask(this, 4, TimeUnit.SECONDS);
        }, 3, TimeUnit.SECONDS);

    }

}
