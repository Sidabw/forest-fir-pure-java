package com.brew.home.stream.learn.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author shaogz
 */
public class LambdaDemo1 {

    public static void main(String[] args) {
        //Lambda入门
		/*List<String> list = new ArrayList<String>();
		Collections.sort(list, (a,b) -> a.compareTo(b));*/

        //常见的几种流的创建方式
		/*// 1. Individual values
		Stream stream = Stream.of("a", "b", "c");
		// 2. Arrays
		String [] strArray = new String[] {"a", "b", "c"};
		stream = Stream.of(strArray);
		stream = Arrays.stream(strArray);
		// 3. Collections
		List<String> list = Arrays.asList(strArray);
		stream = list.stream();*/

        //IntSteam使用
		/*IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
		IntStream.range(1, 3).forEach(System.out::println);*/

        //Stream常见用法
        //1.转换大写
		/*List<String> list = Arrays.asList("And","jkljKAjj","jlABNmmBBa");
		List<String> list2 =list.stream().
				map(String::toUpperCase).
				collect(Collectors.toList());*/
        //2.遍历List
        /*list2.stream().forEach(System.out::println);*/

        //3.平方数		map 1:1
 		/*List<Integer> nums = Arrays.asList(1,2,3,4);
		List<Integer> squareNums = nums.stream().map(n -> n*n).collect(Collectors.toList());
		squareNums.stream().forEach(System.out::println);*/

        //4.flatMap 合并list
        Stream<List<Integer>> inputStream = Stream.of(
            Arrays.asList(1),
            Arrays.asList(2, 3),
            Arrays.asList(4, 5, 6)
        );
        List<Integer> collect = inputStream.
            flatMap((childList) -> childList.stream()).collect(Collectors.toList());

        //5.filter	filter对Stream进行某项测试，通过测试的元素被留下来生成一个新的Stream
        //留下偶数
        //Integer[] sixNums = {1, 2 , 3, 4, 5, 6};
    /*Integer[] events=
            Stream.of(sixNums).filter(n -> n%2 == 0).toArray(Integer[]::new);
    Stream.of(events).forEach(System.out::println);*/
        //把单词挑出来	使用flatMap
    /*List<String> output = reader.lines().
             flatMap(line -> Stream.of(line.split(REGEXP))).
             filter(word -> word.length() > 0).
             collect(Collectors.toList());*/
    }
}
