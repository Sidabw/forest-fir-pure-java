package com.brew.home.basic;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.brew.home.basic.date.R1CalendarTest.FORMATTER_THREAD_LOCAL;

/**
 * @author shaogz
 * @since 2025/7/15 20:12
 */
public class TmpTest250715 {

    private static final String FORMAT_ZH_CN = "INSERT INTO SYS_MULTI_LANG_MGT (ID, LANG_KEY, LANG_TEXT, APP_ID, LANG, CREATE_TIME, CREATOR, LAST_TIME, LAST_EDITOR, `TYPE`) VALUES(%s, '%s', '%s', NULL, 'zh-CN', NULL, NULL, NULL, NULL, 'system');";
    private static final String FORMAT_EN_US = "INSERT INTO SYS_MULTI_LANG_MGT (ID, LANG_KEY, LANG_TEXT, APP_ID, LANG, CREATE_TIME, CREATOR, LAST_TIME, LAST_EDITOR, `TYPE`) VALUES(%s, '%s', '%s', NULL, 'en-US', NULL, NULL, NULL, NULL, 'system');";
    public static void main(String[] args) throws Exception {
//        test6();
//        test5();
    }

    public static void test6() throws Exception{
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2025-07-15 20:12:05");
        System.out.println(date1.getTime());
        Date date2 = new Date((date1.getTime()/1000) * 1000 + 999);
        System.out.println(date2.getTime());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(date2));
    }
    public static void test5() throws Exception {
        LinkedList<String> res = new LinkedList<>();
        //读取该文件的所有行
        String path = "/Users/feiyi/Downloads/a1.log";
        long id = 1947900480231477249L;
        List<String> allLines = Files.readAllLines(Paths.get(path)).stream().filter(e->e!=null&&!e.isEmpty()).collect(Collectors.toList());
        for (int i = 0; i < allLines.size(); i++) {
            String key = allLines.get(i);
            String zhCn = key;
            String enUs = allLines.get(++i);
            res.add(String.format(FORMAT_ZH_CN, ++id, key, zhCn));
            res.add(String.format(FORMAT_EN_US, ++id, key, enUs));
        }
        res.forEach(System.out::println);
    }

    public static void test4() throws Exception {
        LinkedList<String> res = new LinkedList<>();
        //读取该文件的所有行
        String path = "/Users/feiyi/Downloads/a2.log";
        long id = 1945664357912293377L;
        List<String> allLines = Files.readAllLines(Paths.get(path)).stream().filter(e->e!=null&&!e.isEmpty()&&e.length()>2).collect(Collectors.toList());
        for (int i = 0; i < allLines.size(); i++) {
            String line = allLines.get(i);
            String[] split = line.split(" - ");
            String key = split[0];
            String zhCn = split[0];
            String enUs = split[1];
            res.add(String.format(FORMAT_ZH_CN, ++id, key, zhCn));
            res.add(String.format(FORMAT_EN_US, ++id, key, enUs));
        }
        res.forEach(System.out::println);
    }

    public static void test3() throws IOException {
        Set<String> res = new HashSet<>();
        //读取该文件的所有行
        String path = "/Users/feiyi/Downloads/a1.log";
        List<String> allLines = Files.readAllLines(Paths.get(path)).stream().filter(e->e!=null&&!e.isEmpty()&&e.length()>2).collect(Collectors.toList());
        for (int i = 0; i < allLines.size(); i++) {
            String line = allLines.get(i);
            int i1 = line.indexOf("@AuditLog");
            line = line.substring(i1);
            int i2 = line.indexOf("moduleName");
            line = line.substring(i2 + 10);
            int i3 = line.indexOf("\"");
            line = line.substring(i3 + 1);
            int i4 = line.indexOf("\"");
            line = line.substring(0, i4);
            res.add(line);
//            System.out.println(line);
        }
        res.forEach(System.out::println);
    }

    public static void test1() throws IOException {
        List<String> res = new LinkedList<>();
        //读取该文件的所有行
        String path = "/Users/feiyi/Downloads/tmp1.log";
        List<String> allLines = Files.readAllLines(Paths.get(path)).stream().filter(e->e!=null&&!e.isEmpty()&&e.length()>2).collect(Collectors.toList());
        for (int i = 0; i < allLines.size(); i++) {
            String line = allLines.get(i);
            if (containsChinese(line)) {
                System.out.println("coming in:: " + line);

                String zhCn = line;
                String enUs =allLines.get(i+1);
                String key = allLines.get(i+2);
                res.add(String.format(FORMAT_ZH_CN, key, zhCn));
                res.add(String.format(FORMAT_EN_US, key, enUs));

                i+=3;
            }
        }
        for (String line : res) {
            System.out.println(line);
        }
    }

    public static void test2() {
        //    public static void main(String[] args) throws Exception{
        ////        for (int i = 0; i < 100; i++) {
        ////            long id = IdWorker.getId(new DingTalkChannel());
        ////            System.out.println(id);
        ////        }
        //        List<String> res = new LinkedList<>();
        //        String path = "/Users/feiyi/Downloads/tmp2.log";
        //        List<String> allLines = Files.readAllLines(Paths.get(path)).stream().filter(e->e!=null&&!e.isEmpty()&&e.length()>2).collect(Collectors.toList());
        //        for (int i = 0; i < allLines.size(); i++) {
        //            long id = IdWorker.getId(new DingTalkChannel());
        //            String curLine = allLines.get(i);
        //            String formatted = String.format(curLine, id);
        //            res.add(formatted);
        //            System.out.println(formatted);
        //        }
        //    }
    }

    public static boolean containsChinese(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        // 匹配中文字符的正则表达式（涵盖常用汉字区间）
        String regex = "[\\u4e00-\\u9fa5\\u3400-\\u4db5\\u9fa6-\\u9fef]";
        return str.matches(".*" + regex + ".*");
    }
}
