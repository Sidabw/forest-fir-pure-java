/**
 * Copyright (C), 2018-2019, zenki.ai
 * FileName: Demo
 * Author:   feiyi
 * Date:     2019/6/19 11:58 AM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.brew.home.jvm;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 〈一句话功能简述〉:
 * 〈〉
 *
 * @author feiyi
 * @date 2019/6/19
 * @since 1.0.0
 */
public class PrintSysProp {

    public static void main(String[] args) {
        new PrintSysProp().printSysProperties();
    }

    public void printSysProperties() {
        Properties properties = System.getProperties();
        Set<Map.Entry<Object, Object>> entries = properties.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + "---" + value);
            if (key.toString().contains("io")) System.out.println("0000000000000000000000000000000000");
            if (value.toString().contains("private")) System.out.println("1111111111111111111111111111111111");
            // java.io.tmpdir---/var/folders/8g/wd8tdvcd1m5_1krwg4s261qr0000gn/T/
        }
    }

}
