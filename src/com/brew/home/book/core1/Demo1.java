/**
 * Copyright (C), 2018-2020, zenki.ai
 * FileName: Demo1
 * Author:   feiyi
 * Date:     2020/3/19 10:49 AM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.brew.home.book.core1;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 〈一句话功能简述〉:
 * 〈
 * 1。ascii
 * 0 48
 * A 65
 * a 97
 * 〉
 *
 * @author feiyi
 * @date 2020/3/19
 * @since 1.0.0
 */
public class Demo1 {

    public static void main(String[] args) {
        //
        int[] a1Arr = new int[]{34, 2, 15, 9, 2, 8, 20};
        Arrays.sort(a1Arr);//使用优化了的快速排序。推荐哦//。。
        System.out.println(Arrays.toString(a1Arr));

        //代码变成可执行文件叫编译，先编译这个还是编译那个，是编译的安排，叫做构建(build). make命令主要用于c的构建
        //可以理解Java内置make(Javac A.java 其中A中引用了C，那么java会自动先去编译C)

        //final指的是对象的引用不可变。内容还是可以变化的。所以final应当尽量去修饰不可变类，像是String这样的，final修饰类;
        //final修饰类，所有方法默认也是final，但域不会。
        // 创建为完成后没有add/set方法改变当前对象状态。
        //例外的是一些native本地方法，因为他可以绕过Java语言的存取控制机制，像是System.out是final的，但还有一个native 的 setOut方法。

        //4.1类之间的常见关系
        //is-a 继承
        //has-a 聚合 （A对象里面又一个B成员变量，A has B）
        //uses-a 依赖（A对象用到了B对象的方法，A依赖B）
        //4.5 Java语言总是采用按值调用。
        //传递一个基本数据类型时，毫无疑问，是按值传递。
        //传递一个引用数据类型时，方法内部会创建一个局部引用指向真正的数据，所以同样是按值传递。否则，那我在方法内部s = new Obj()是不是可以改变方法外部的s对象的引用？当然不可以
        //父类的属性只能通过super.getXX()获取，而能super.xx
        //如果子类的构造器没有显式的调用超类的构造器，那么默认调用父类的无参构造，如果此时父类没有无参构造，那么JVM编译器将报错。
        //多态：一个变量可以指示多数数据类型
        //静态绑定：对private、static、final修饰的几种方法或构造器的调用，属于静态绑定。
        //动态绑定：在运行时动态的决定调用哪个方法.如果有方法重载，那么则会发生重载解析（从重载的方法中挑选正确的那个）。
        //过程：JVM调取引用的实际类型的方法表，再调取父类的方法表，并最终决定调用哪一个方法。
        //抽象类是不可实例化的

        //所有的数组类型同样扩展与Object
        int[] ints = new int[10];
        ints[0] = 1;
        int[] clone = ints.clone();
        //要习惯使用Objects.eq方法。

        //equal特性
        //1.自反性。x.eq(x) return true.
        //2.对称性。a.eq(b) 的结果和 b.eq(a) 相同
        //3.传递性。a.eq(b) : true, b.eq(c) : true, 那么a.eq(c) 应当同样返回true
        //4.一致性。如果x和y引用的对象没有发生变化，那么反复调用a.eq(b)得到的结果应当相同。
        //对于任意非空引用x，x.equals(null)应返回false
    }


    //TODO fix this, 泛型相关的case

//    private static Map<String, ThreadPoolExecutor> threadPoolExecutorMap = new HashMap<>();
//    ThreadPoolExecutor blockingThreadPool = getThreadPool(threadPoolExecutorMap, "threadPool_" + proceedId);

//    //从指定map中根据指定key取ThreadPoolExecutor，取不到就创建一个新的并放到Map里面去
//    private ThreadPoolExecutor getThreadPool(Map<String, ThreadPoolExecutor> dataMap, String key) {
//        if (dataMap.get(key) instanceof ThreadPoolExecutor)
//            return (ThreadPoolExecutor)dataMap.get(key);
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
//                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1));
//        dataMap.put(key, threadPoolExecutor);
//        return threadPoolExecutor;
//    }
}
