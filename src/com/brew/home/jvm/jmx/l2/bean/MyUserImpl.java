package com.brew.home.jvm.jmx.l2.bean;


public class MyUserImpl implements MyUser{

    private String name = "nulla";

    @Override
    public void setName(String b) {
        name = b ;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void dododo() {
        System.out.println("adaaaa dododododododododo");
    }
}
