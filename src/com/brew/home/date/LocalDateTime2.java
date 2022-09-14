package com.brew.home.date;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class LocalDateTime2 {

    public static void main(String[] args) {
        //part-1
        //UTC 和 GMT 只是一个靠的是原子钟（UTC），一个靠的是地球自转/公转（GMT），前者误差更小。
        //其他的含义都是一样的。
        //比如时区，UTC+8 GMT+8是一个意思
        //比如时间戳，Java中通常都是指1970-01-01T00:00:00Z到现在的毫秒值

        //part-1
        //不要这样用：LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        //要这样用：LocalDateTime.now().atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli()
        //暂时的理解：Instant是一个带有时区概念的类。
        System.out.println(ZoneOffset.systemDefault());
        LocalDateTime now = LocalDateTime.now();
        long a = now.atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
        System.out.println(a);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date(a)));
        long b = now.toInstant(ZoneOffset.UTC).toEpochMilli();
        System.out.println(b);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date(b)));

        System.out.println(System.currentTimeMillis());
        System.out.println(Instant.now().toEpochMilli());

//        System.out.println();
//        System.out.println(Instant.now().toEpochMilli());
//        System.out.println(System.currentTimeMillis());
//
//        System.out.println(DateFormatTool.formatDate(new Date(Instant.now().toEpochMilli()), CRON_FIXED_TIME_PATTERN));
//
//        LocalDateTime seconds2after = now.plusSeconds(2);
//        long milli = seconds2after.toInstant(ZoneOffset.UTC).toEpochMilli();
//        System.out.println(DateFormatTool.formatDate(new Date(now.toInstant(ZoneOffset.UTC).toEpochMilli()), CRON_FIXED_TIME_PATTERN));
//        System.out.println(DateFormatTool.formatDate(new Date(milli), CRON_FIXED_TIME_PATTERN));
    }
}
