
package com.brew.home.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarTest {

    /**
     * <p> 1. SimpleDateFormat本身是线程不安全的。
     * <p> 2. 所以可以在format方法上加锁
     * <p> 3. 但也可以使用ThreadLocal.这样每个线程都有一份自己的SimpleDateFormat，自然就不存在线程安全问题了。
     * <p> 4. 如果不使用ThreadLocal.那总不能每次访问util.format方法就new SimpleDateFormat吧..
     * <p> 5. 下面的测试，线程id相同的情况下，不会再走ThreadLocal的withInitial方法。
     */
    private static final ThreadLocal<SimpleDateFormat> FORMATTER_THREAD_LOCAL = ThreadLocal.withInitial(() -> {
        System.out.println("thread local init");
        return new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
    });

    public static void main(String[] args) throws ParseException {
        Calendar cal = Calendar.getInstance();


        //week时，这里0表示明天，-1表示今天
        cal.add(Calendar.DAY_OF_MONTH, -1);
        String d00 = FORMATTER_THREAD_LOCAL.get().format(cal.getTime());
        System.out.println("昨天：" + d00);

        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 0);
        String d0 = FORMATTER_THREAD_LOCAL.get().format(cal.getTime());
        System.out.println("今天：" + d0);

        cal.add(Calendar.DAY_OF_MONTH, 22);
        String d1 = FORMATTER_THREAD_LOCAL.get().format(cal.getTime());
        System.out.println("22天后：" + d1);
        System.out.println("22天后，周：" + cal.get(Calendar.DAY_OF_WEEK));

        cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        String lastYear = FORMATTER_THREAD_LOCAL.get().format(cal.getTime());
        System.out.println("去年：" + lastYear);


        cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, -1);
        String lastHour = FORMATTER_THREAD_LOCAL.get().format(cal.getTime());
        System.out.println("一小时前：" + lastHour);

        cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, -10);
        String xMinAgo = FORMATTER_THREAD_LOCAL.get().format(cal.getTime());
        System.out.println("10分钟前：" + xMinAgo);


        /*
         * 关于System.currentTimeMillis() 和 System.nanoTime()的补充说明
         * 1。 1秒=1000毫秒，1毫秒=1000微秒，1微秒=1000纳秒
         * 2。 System.currentTimeMillis()表示自1970年1月1日0时起的毫秒数
         * 3。 System.nanoTime()只是以纳秒作为单位，但一不能精确到真正的纳秒，更重要的是不能表示时间。
         * 4。 System.nanoTime()往往用来衡量一段代码的执行时间差、网络链接时间差这种相对值。
         */
        long currentTimeMillis = System.currentTimeMillis();
        long nanoTime = System.nanoTime();
        System.out.println("currentTimeMillis: " + currentTimeMillis);
        System.out.println("nanoTime: " + nanoTime);

    }
}
