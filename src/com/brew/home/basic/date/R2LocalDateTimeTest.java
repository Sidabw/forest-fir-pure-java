/**
 * Copyright (C), 2018-2020, zenki.ai
 * FileName: Demo
 * Author:   feiyi
 * Date:     2020/10/16 11:29 AM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.brew.home.basic.date;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 〈一句话功能简述〉:
 * 〈〉
 *
 * @author feiyi
 * @date 2020/10/16
 * @since 1.0.0
 */
public class R2LocalDateTimeTest {

    //为什么要使用LocalDate LocalTime LocalDateTime??
    //其实把SimpleDateFormat用ThreadLocal做线程安全的处理后，这些是没必要学的
    //就当是拓宽下技术栈把
    public static void main(String[] args) {


        //Convert Date to LocalDate or LocalDateTime and Back
        //https://www.baeldung.com/java-date-to-localdate-and-localdatetime

        //1LocalDate 只获取年月日
        System.out.println("part-1 初识LocalDate");
        LocalDate localDateNow = LocalDate.now();
        LocalDate localDateSpecified = LocalDate.of(2020, 11, 12);
        System.out.println("LocalDate，何年：" + localDateNow.getYear());
        System.out.println("LocalDate，何月：" + localDateNow.getMonth().getValue());
        System.out.println("LocalDate，何日：" + localDateNow.getDayOfMonth());
        System.out.println("LocalDate，周几：" + localDateNow.getDayOfWeek().getValue());


        //2LocalTime 只获取时分秒
        System.out.println("part-2 初识LocalTime");
        LocalTime localTimeSpecified = LocalTime.of(23, 59, 59);
        LocalTime localTimeNow = LocalTime.now();
        System.out.println("LocalTime，几时：" + localTimeNow.getHour());
        System.out.println("LocalTime，几分：" + localTimeNow.getMinute());
        System.out.println("LocalTime，几秒：" + localTimeNow.getSecond());


        //This class does not store or represent a time-zone.
        // Instead, it is a description of the date, as used for birthdays, combined with the local time as seen on a wall clock.
        // It cannot represent an instant on the time-line without additional information such as an offset or time-zone
        //3LocalDateTime 获取年月日时分秒
        System.out.println("part-3 初识LocalDateTime");
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.of(2019, 11, 10, 14, 46, 56);
        LocalDateTime localDateTime2 = LocalDateTime.of(localDateNow, localTimeNow);
        System.out.println("LocalDateTime，何年：" + localDateTime2.getYear());
        System.out.println("LocalDateTime，几秒：" + localDateTime2.getSecond());

        //4格式化时间（线程安全）
        //format localTime是不能使用yyy-MM-dd这样的pattern的，否则抛出异常。format localDate也一样不能使用HH:mm:ss
        System.out.println("part-4 初识LocalDateTime format");
        System.out.println("DateTimeFormatter:LocalTime，" + DateTimeFormatter.ofPattern("HH:mm:ss").format(localTimeNow));
        System.out.println("DateTimeFormatter:LocalDate，" + DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDateNow));
        System.out.println("DateTimeFormatter:LocalDateTime，" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime));
        System.out.println("DateTimeFormatter:LocalDateTime，" + DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDateTime));

        //5格式化时间-2
        //parse的话就必须对应的类，不能用DateTimeFormatter
        //日期格式必须是对的，只有date 没有time同样也会抛出异常
        System.out.println("part-5 初识LocalDateTime parse");
        LocalDateTime localDateTime4 = LocalDateTime.parse("2020-12-12 19:26:51", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("LocalDateTime.parse:" + localDateTime4);


        //6不可变时间Instant
        System.out.println("part-5 初识Instant");
        Instant now = Instant.now();
        System.out.println("second from Instant: " + now.getEpochSecond()); //到格林威治时间的秒值，即标准unix时间戳
        System.out.println("milli from Instant: " + now.toEpochMilli()); //毫秒
        System.out.println("milli from System.currentTimeMillis: "  + System.currentTimeMillis());

    }
}
