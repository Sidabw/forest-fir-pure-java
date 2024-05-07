/**
 * Copyright (C), 2018-2020, zenki.ai
 * FileName: MultiThread4
 * Author:   feiyi
 * Date:     2020/3/3 7:43 AM
 * Description: 测试synchronized
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.brew.home.thread.archived_bak;

class MyThread implements Runnable {

    private Resource resource;

    public MyThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        resource.produce();
    }
}

class Resource {

    public void produce() {
        //就没有synchronized{}这个用法...
        synchronized (this) {

        }
    }
}