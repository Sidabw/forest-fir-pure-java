package com.brew.home.stream.learn.optional;

import com.brew.home.stream.learn.pojo.User;

import java.util.HashMap;
import java.util.Optional;

public class OptionalDemo {

    /**
     * @Description
     * note from Effective Java
     * 1.Optional是一个不可变集合，最多只有一个元素
     * 2.创建Optional的3种工厂方式.of .ofNullable .empty
     * 3.使用Optional的3种最简洁方式
     *      .orElse(T t) 提供默认值
     *      .orElseGet(Supplier<? extends T> other) 提供默认处理
     *      .ofElseThrow(Supplier<? extends X> exceptionSupplier) 提供特定异常处理
     *
     * 4.尽量不要将Optional用到除返回值之外的任何地方
     * @since: 2.0.5
     * @Author: feiyi
     * @Date: 2020/2/17 11:16 PM
     **/

    /**
     * 1.存在即返回, 无则提供默认值
     * return user.orElse(null);
     * return user.orElse(UNKNOWN_USER);
     * 2.存在即返回, 无则由函数来产生
     * return user.orElseGet(() -> fetchAUserFromDatabase());
     * 3.存在才对它做点什么
     * http://www.importnew.com/22060.html
     * 使用map
     * <p>
     * --------
     * 尽可能不要使用isPresent和get，否则你会发现与之前判空没有什么区别
     * --------
     * <p>
     * 类似空指针这样的异常，从某些层面来说正确的处理应该是告诉页面没有数据，而不是抛异常或者try catch。
     * <p>
     * Optional是1个可以为null的容器，如果值存在则方法isPresent返回true，调用get方法返回该对象。
     * 我们通常使用Optional来解决空指针的问题。
     * Optional提供了很多有用的方法，不需要再进行显式的空指针判断。
     * 1.使用of构建时，如果构建值为null，则直接报空指针异常。
     * 2.使用ofNullable构建时，可以使用isPresent判断是否为null。
     */
    public static void main(String[] args) {
        new OptionalDemo().test1();
        new OptionalDemo().test2();
        new OptionalDemo().test3();
        new OptionalDemo().test4();
    }

    /***
     * 正常情况不会这样用
     */
    public void test1() {
        //这里传null的话会报nullPointException
        Optional<String> brew = Optional.of("Brew");
        //这里可以传null
        Optional<Object> empty = Optional.ofNullable(null);

        System.out.println("of   " + brew.isPresent() + "--------" + "ofNullable   " + empty.isPresent());

        if (brew.isPresent()) {
            System.out.println(brew.get());    //Brew
        }
        if (empty.isPresent()) {    //false
            System.out.println(empty.get());
        }
    }

    public void test2() {
        //ofElse:如果有值则返回该值，如果没有值则返回指定值
        Optional<Object> ofNullable = Optional.ofNullable(null);
        System.out.println(ofNullable.orElse("指定值"));
        int a = 2;

        //ofElseGet:相比ofElse，ofElseGet可以传入1个lambda表达式接受默认值
        System.out.println(ofNullable.orElseGet(() -> ""));

        //ofElseThrow	如果没有值的话就抛出指定异常
        try {
            ofNullable.orElseThrow(Exception::new);
        } catch (Exception e) {
            // handle exception
            System.out.println(e.getMessage());
        }
    }

    /**
     * lambda   map:
     */
    public void test3() {
        Optional<String> ofNullable = Optional.ofNullable("brew");
        //生成1个新的map
        Optional<String> optionalMap = ofNullable.map(e -> e.toUpperCase());
        System.out.println(optionalMap.orElse("DEFAULT"));
    }

    /**
     * lambda	filter	max
     */
    public void test4() {
        HashMap<Double, User> hashMap = new HashMap<Double, User>();
        hashMap.put(1D, new User(12, "nv", "sida"));
        hashMap.put(2D, new User(19, "nan", "brew1"));
        hashMap.put(5D, new User(15, "nan", "brew2"));
        hashMap.put(2D, new User(13, "nan", "brew3"));
        hashMap.put(3D, new User(12, "nan", "brew4"));
        hashMap.put(2D, new User(16, "nan", "brew5"));
        Optional<Double> max = hashMap.keySet().stream().filter(e -> e > 3D).max(Double::compareTo);
        Optional<Double> findFirst = hashMap.keySet().stream().filter(e -> e > 33D).findFirst();
        if (findFirst.isPresent()) {
            System.out.println("可以这样判空！");
        } else {
            System.out.println("It's empty after filter operation in stream");
        }
        System.out.println(max.orElse(1D));

        //List<User> user = hashMap.get(2D);

    }
}
