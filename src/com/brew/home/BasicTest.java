package com.brew.home;


import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/***
 *  Created by shao.guangze on 2018/7/26
 */
public class BasicTest {

    public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
        new BasicTest().t1();
        new BasicTest().t2();
        new BasicTest().t3();
        new BasicTest().t4();
        new BasicTest().t5();
        new BasicTest().t6();
    }

    public void t1() {
        String str2 = "aa\\aba\"";
        //1个反斜线换成2个反斜线
        str2 = str2.replaceAll("\\\\", "\\\\\\\\");
        //1个单引号变成1个反斜杠+1个单引号
        str2 = str2.replaceAll("\"", "\\\\\"");
        //同样，1个反斜杠需要用4个反斜杠来表示
        str2 = str2.replaceAll("b", "\\\\");
        System.out.println(str2);

        System.out.println("----------------------------------");
        System.out.println(null instanceof String);
        System.out.println(new Double(Double.valueOf("0.1") * 1000).intValue());
        String aaa = new String("aaa");
        String aaa1 = new String("aaa");
        System.out.println(aaa.hashCode() == aaa1.hashCode());
        System.out.println(aaa.equals(aaa1));
        BasicTest demo = new BasicTest();
        demo.equals(new BasicTest());
        System.out.println("-----------------------------------");
        Object o = new BasicTest();
        BasicTest d = (BasicTest) o;
        //所以说强转之后还是原来的对象
        System.out.println(d == o);
        System.out.println("----------------------------------");
        System.out.println(String.format("task.%s", 1));
        System.out.println("---");


        //断言之所以本地测试会被启动是因为启动参数上加上了-ea ，即-enableassert

        //伪随机数；密码学安全随机数；真随机数
        //1。伪随机数：看着是随机的，但实际上有固定的生成规则
        //2。密码学安全随机数，也不是真正意义上的随机数，但是没有办法根据已有样本推测出下一个元素
        //3。真随机数：往往都是一些实际现象，如抛硬币等。对核裂变等要求的真正随机数，往往是非常复杂的。
        int i = new Random().nextInt(10);
        System.out.println(i);


        //string 和 StringBuffer 在使用上的一点区别
        // String a = null;
        // System.out.println(new StringBuffer(a).append("aaaa").toString()); //会抛异常
    }

    public void t2() {
        HashMap<String, Object> map = new HashMap<>();
        System.out.println(map.getOrDefault("aa", "asdfsdf"));
        Runtime runtime = Runtime.getRuntime();
        System.out.println(runtime.availableProcessors());
        map.put("a", "b");
        map.put("b", "c");
        System.out.println(map.get("a"));
        Map<String, String> map2 = Collections.singletonMap("a", "v");
        map.put("c", map2);
        System.out.println("----------");
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.YEAR, 0);
        System.out.println(new SimpleDateFormat("yyyy").format(instance.getTime()));
    }

    public void t3() {
        int i = 4;
        int j = 4;
        System.out.println(i++);
        System.out.println(++j);


        //i-- i++测试
        int[] arr2 = {1, 2, 3, 4, 5};
        while (arr2[i] > 2) {
            i--;
        }
        while (arr2[j--] > 2) {
        }
        System.out.println(i);  //输出：1
        System.out.println(j);  //输出：0
    }

    /**
     * @param
     * @Description 元素迭代的时候remove必须使用迭代器. jdk1.8之后新增了removeIf方法
     * 注意：下面只有方式二好使，因为方式一返回的是一个假的ArrayList(是Arrays的静态内部类)
     * @return: void
     * @since: 2.0.5
     * @Author: feiyi
     * @Date: 2019/12/23 4:00 PM
     **/
    public void t4() {
        //方式一
        //        List<String> strs = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

        //方式二
        List<String> strs = new ArrayList<>();
        strs.add("a");
        strs.add("b");
        strs.add("c");
        strs.add("d");
        strs.add("e");
        strs.add("f");
        strs.add("g");

        //
        Iterator<String> iterator = strs.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
            if (next.equals("b")) {
                iterator.remove();
            }
        }

        System.out.println("------------------------------------------------------");
        //
        strs.removeIf(e -> {
            System.out.println(e);
            return "d".equals(e);
        });

    }

    public void t5() throws UnsupportedEncodingException {
        String str = "21\u4e16\u7eaa\u7ecf\u6d4e\u62a5\u9053";
        System.out.println(new String(str.getBytes(), "UTF-8")); //21世纪经济报道

        String str2 = "\u83b7\u53d6\u62bd\u53d6\u6570\u636e\u5931\u8d25";
        System.out.println(new String(str2.getBytes(), "UTF-8")); //获取抽取数据失败
    }

    public void t6() {
        String str = "aa$.bb";
        String str2 = "aa$bb.cc";
        //index of 不接受regex...
        System.out.println(str.indexOf("\\."));

        String str3 = " $.param.meta_info";
        String substring = str3.substring(2);
        System.out.println(substring);
    }
}
