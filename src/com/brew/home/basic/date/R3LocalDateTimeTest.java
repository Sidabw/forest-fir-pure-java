package com.brew.home.basic.date;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class R3LocalDateTimeTest {

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

        //20221020补充：
        //LocalDateTime只是一个时间的描述，他是没有时区的。
        //对于2022-10-20 10:45:39:698这个时间，如果描述的是美国，如果描述的是中国，对应的时间戳肯定是不一样的。
        //如果他是东八区的，那么他的时间戳是1666233939698
        //如果他是0区，那么他的时间戳是1666262739698

        //20221020补充：UTC & GMT？
        //GMT是老的，UTC是新的，现在（since 1972）用的都是UTC
        //GMT（Greenwich Mean Time）也叫格林威治平时，是按照太阳算的。它规定太阳每天经过位于英国伦敦郊区的皇家格林威治天文台的时间为中午12点。
        //UTC（Coodinated Universal Time），协调世界时，按原子钟算的
        //UTC+8 和 GMT+8，一个意思
        //世界标准时间 = 北京时间 - 8小时

        //20221020补充：时间戳？
        //广义的时间戳是自格林威治时间以来的秒数，也被称为 Unix 时间戳（Unix Timestamp）
        //三个表述，一个意思：格林威治时间、1970年01月01日00时00分00秒、January 1, 1970, 00:00:00 GMT
        //但是Java的Date类，Instant类toEpochMilli返回的 都是=毫秒
        //一般程序中的时间戳，都是指毫秒。但如果看到Unix Timestamp，那一定是秒...
        //参考：https://developer.aliyun.com/article/269765     https://zhuanlan.zhihu.com/p/135951778?utm_source=wechat_timeline   https://bajiu.cn/shicha/

        //20241113补充：
        //一个准确的时间，是由 时区+时间 组成的，例如：UTC+8 2022-10-20 19:46:30:733
        //不存在绝对的「这一刻」的概念，此时北京的18:00:00，就是美国的10:00:00
        //所以，字符串的时间和整形的时间戳，是一一对应的，但前提是，字符串的时间指定了时区！！！


        System.out.println("print1,当前时区：" + ZoneOffset.systemDefault());
        LocalDateTime now = LocalDateTime.now();
        long timestamp1 = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        System.out.println("print2,当前时间戳：" + timestamp1);
        System.out.println("print3,当前时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date(timestamp1)));

        //为什么得到的时间会比当前时间大8个小时
        //世界标准时间 = 北京时间 - 8小时
        //解释起来好麻烦啊，就因为用了一个错误的用法....
        //对于print2, print3，如果正确理解正确用的话，他对应的UTC+0， 应该是上面的值-8
        //即对于print3这个时间，全世界静止，他对应的UTC+0， 应该是print2的值-8

        //now.toInstant(ZoneOffset.UTC)这么用啥意思了，now，也即是print3，成了UTC+0的值了
        //就比如说：print3是2022-10-20 11:46:30:733
        //那对应的UTC+8是多少，肯定+8啊

        //延伸，所以时间戳跟时区有关系吗？
        //这么问其实很不严谨
        //时间戳是当前时间到格林威治时间的毫秒值【in java】
        //当前时间是什么时间？
        //2022-10-20 11:46:30:733 如果是在UTC+8的话 和 如果是在UTC+0的话，那表示的时间戳肯定不一样啊
        //UTC+0 2022-10-20 11:46:30:733 he UTC+8 2022-10-20 19:46:30:733 的时间戳是一样的

        long timestamp2 = now.toInstant(ZoneOffset.UTC).toEpochMilli();
        System.out.println("print4,按照UTC+0，得到的当前时间戳：" + timestamp2);
        System.out.println("print5,按照UTC+0，得到的当前时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date(timestamp2)));

        //这些跟part2的输出是一样的
        System.out.println("System.currentTimeMillis(): " + System.currentTimeMillis());
        System.out.println("Instant.now().toEpochMilli(): " + Instant.now().toEpochMilli());


        //对于1666244879773这个时间戳，他表示的UTC+8时间是：2022-10-20 13:47:59:773
        Instant instant = Instant.ofEpochMilli(1666244879773L);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());
        System.out.println(localDateTime);


        //对于字符串和LocalDateTime的互转，看R2吧
    }
}
