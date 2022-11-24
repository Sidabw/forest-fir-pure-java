/**
 * Copyright (C), 2018-2019, zenki.ai
 * FileName: Observer
 * Author:   feiyi
 * Date:     2019/12/27 3:22 PM
 * Description: 观察者
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.brew.home.design.mode.observe;

import java.util.Observable;
import java.util.Observer;

/**
 * 〈一句话功能简述〉:
 * 〈观察者〉
 *
 * @author feiyi
 * @date 2019/12/27
 * @since 1.0.0
 */
public class MyObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("MyObserver :: update:: args::" + arg + "");
    }
}
