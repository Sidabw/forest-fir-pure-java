package com.brew.home.jvm.jmx.l2.bean;


import javax.management.MXBean;

@MXBean
public interface MyUser {

    //向下面这样定义一个getA setA，站在MxBean的角度，这叫定义了一个"属性 attributes"
    void setName(String b);

    String getName();


    //这叫定义了一个 "操作 operations"
    void dododo();

}
