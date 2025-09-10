package com.it.zhengZeBiaoDaShi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo5 {
    public static void main(String[] args) {

        /*
            只写+和*表示贪婪匹配
            +？表示非贪婪匹配
            *？表示非贪婪匹配


            贪婪爬取：在爬取的时候尽可能的多获取数据
            非贪婪爬取：在爬取的时候尽可能的少获取数据

        */

        String str = "abbbbbbbbbbbbbbaaaab";

        String regex = "ab+?";
        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(str);

        while (m.find()){
            System.out.println(m.group());
        }
    }
}
