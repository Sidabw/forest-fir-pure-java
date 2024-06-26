/**
 * Copyright (C), 2018-2020, zenki.ai
 * FileName: User2
 * Author:   feiyi
 * Date:     2020/3/19 3:20 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.brew.home.thread.archived_bak.test1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈一句话功能简述〉:
 * 〈〉
 *
 * @author feiyi
 * @date 2020/3/19
 * @since 1.0.0
 */
public class User2 implements Runnable {

    private static ReentrantLock lock = new ReentrantLock();

    private static final String user = "user2";

    private WebApp webApp;

    private static int i = 0;

    public User2(WebApp webApp) {
        this.webApp = webApp;
    }

    @Override
    public void run() {
        i++;
        while (true) {
            webApp.webMethod(lock, user + " -- " + i);
        }

//        webApp.m2(user);
//        new WebApp().m2(user);
    }
}
