package com.brew.home.jvm.ref;

import java.lang.ref.WeakReference;

public class RefTest2Weak {

    public static void main(String[] args) {
        Object o = new Object();
        WeakReference<Object> weakReference = new WeakReference<Object>(o);
        o = null;

        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get()); //gc会就拿不到了
    }
}
