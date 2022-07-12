package com.brew.home.jvm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OomPracticeBrew {

    public static void main(String[] args) throws InterruptedException {
        //目标：在oom的堆dump.bin文件分析中，得到大对象被谁一直拿着
        //-Xmx200M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/feiyi/Documents/git-project-sidabw/forest-fir-pure-java/out
        //结果参考印象笔记-Java
        new Thread(() -> {
            while(true) {
                try {
                    System.out.println(new SimpleDateFormat("HH:mm:ss:SSS").format(new Date()) + " still run ...");
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (Exception e) {
                    System.out.println("exit......");
                    System.exit(1);
                }
            }
        }).start();

        new OomPracticeBrew().doOom();

    }




    private List<String> bigObjBrew = new ArrayList<>();
    private void doOom() {
        StringBuilder builder = new StringBuilder();
        for (int i=0; i< 100_000; i++) {
            builder.append("sida");
        }
        String bigStrBrew = builder.toString();
        while (true) {
            bigObjBrew.add(bigStrBrew);
        }
    }
}
