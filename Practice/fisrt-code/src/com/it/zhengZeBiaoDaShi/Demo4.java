package com.it.zhengZeBiaoDaShi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo4 {
    public static void main(String[] args) {

        String str = "java是一个优秀的编程语言，Java8和java11被广泛使用，或许将来java17也会广流传";

        //?理解为前面的数据Java
        //=表示Java后面要跟随的数据
        //但是要获取的时候只获取前半部分
        //(?i)忽略大小写
        String regex = "((?i)java)(?=8|11|17)";

        //需求2
        String regex2 = "((?i)java)(8|11|17)";
        String regex3 = "((?i)java)(?:8|11|17)";

        //需求3
        String regex4 = "((?i)java)(?!8|11|17)";

        Pattern p = Pattern.compile(regex4);

        Matcher m = p.matcher(str);

        while (m.find()){
            System.out.println(m.group());
        }
    }
}
