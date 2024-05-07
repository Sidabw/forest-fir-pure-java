package com.brew.home.in_progress.timewheel.i;

import java.util.concurrent.TimeUnit;

/**
 * @author shaogz
 * @since 2024/1/25 17:17
 */
public interface Timer {

    void createTimerTask(TimerTask task, long delay, TimeUnit unit);


}
