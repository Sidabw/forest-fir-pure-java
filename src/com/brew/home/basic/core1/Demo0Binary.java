package com.brew.home.basic.core1;

public class Demo0Binary {

    public static void main(String[] args) {

        //原文来自Java Core
        //位运算部分参考了：https://zhuanlan.zhihu.com/p/106893096

        int a = 0b110;//加上0b的前缀表示二进制
        int a1 = 0xf;   //加上0x的前缀表示十六进制
        System.out.println(a);
        System.out.println(a1);
        int a2 = 1_000_000;//只是为了更加易读：表示一百万，编译后会省略 _
        //浮点类型后面不跟F默认是D
        //char类型是用于表示Unicode编码的字符单元的字符类型
        //char类型用UTF-16描述一个代码单元。一个字符占两个字节，被称作一个代码单元。
        //UTF-16编码采用不同长度的编码表示所有的Unicode
        char a3 = 'a';
        int a4 = a3 + 1;
        System.out.println(a4); //因为 'a'对应ascii码表的十进制值是97
        int i1 = 98;
        System.out.println("i1:::" + (char) i1);//输出b


        //与或非，是逻辑运算符。& | !
        //按位与运算、按位或运算、按位取反运算、按位异或运算、按位左移、按位右移，都是位运算

        //位运算。
        //位运算都是在补码上进行的。最后的结果也是补码。
        //与，或，非，异或
        //与&：1 1 得 1，否则为0
        //或|：有一个为1 则为1， 否则为0
        //非~：1->0, 0->1
        //异或^：相同为0，不同为1
        int a5 = 0b0000;
        int a6 = 0b0001;
        System.out.println("a5 " + a5 + " a6 " + a6);
        System.out.println("a5 & a6 得：" + (a5 & a6));
        System.out.println("a5 | a6 得：" + (a5 | a6));
        //符号位：最高位只表示符号位。-3用一个字节的二进制表示1000 0011
        //原反补：计算机没有减法，只有加法。9-3 是拿9的补码和-3的补码相加。
        //俩补码相加，结果超位数了，那对应就把高位超了的丢掉。
        //正数的原码反码补码一样。负数的反码是最高位符号位不变，其他位1变0，0变1。负数的补码是反码+1。

        //9-3，即9+(-3)
        //9原反补：00001001，-3原码：1000 0011，-3的反码（符号位不变）：1111 1100，-3的补码：1111 1101。
        // 相加：
        //  00001001
        //  11111101
        //1 00000110
        //即0000 0110，即6


        //~a5 得多少，即~0b00得多少？
        //0000 0000 【补码】
        //取反后
        //1111 1111 【补码】
        //再往前推其原码。【补码】1111 1111    --> 【反码】1111 1110   ---> 【原码,符号位不变】1000 0001，即-1

        //这里实际上是~0b00 -> 0b11也就是-1。
        //补充-1，如果用1个字节表示的话，就是1000 0001
        //2-1的话就不赘述了，原反补相加

        System.out.println("a5 " + a5 + " ~a5  "+ ~a5);
        System.out.println(~0b0001);//同上44


        //位移运算
        //左移，高位舍弃，低位补0
        //右移，高位补0，低位舍弃.这里高位是符号位，所以负数，高位则补1。
        //如果位移超过32位，则会 x%32先取余。1111>>33 和 1111>>1是一个意思
        //对于负数的右移：因为负数在内存中是以补码形式存在的，所有首先根据负数的原码求出负数的补码(符号位不变，其余位按照原码取反加1)，
        //然后保证符号位不变，其余位向右移动到X位，在移动的过程中，高位补1.等移位完成以后，然后保持符号位不变，其余按位取反加1，得到移位后所对应数的原码。即为所求。
        //所以负数位移运算：1原码2饭吗3补码4位移5继续取反+1得出结果
        System.out.println(25 << 1);//得50，刚好2倍
        System.out.println(25 >> 1);//得12，刚好除2取整
        System.out.println(-2 >> 1);
        System.out.println(-2 >>> 1);
    }
}
