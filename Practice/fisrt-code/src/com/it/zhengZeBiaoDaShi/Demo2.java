package com.it.zhengZeBiaoDaShi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo2 {
    public static void main(String[] args) {
        String str = "java是一个优秀的编程语言，java8和java11被广泛使用，或许将来java17也会广流传";

        Pattern p = Pattern.compile("java\\d{0,2}");

        Matcher m = p.matcher(str);

        //底层记录子串起始索引和借书索引+1
        boolean b = m.find();

        String str2 = m.group();
        System.out.println(str2);

        //按上次结束继续找
        b = m.find();

        while (m.find()){
            String s = m.group();
            System.out.println(s);
        }
    }
}
