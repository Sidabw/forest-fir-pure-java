/**
 * Copyright (C), 2018-2019, zenki.ai
 * FileName: Singleton
 * Author:   feiyi
 * Date:     2019/1/2 下午2:26
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.brew.home.in_progress.design.mode.singleton;

/**
 * 〈一句话功能简述〉:
 * 〈饿汉式单例〉
 *
 * @author feiyi
 * @date 2019/1/2
 * @since 1.0.0
 */
public class Singleton {

    private static final Singleton singleton = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return singleton;
    }
}
