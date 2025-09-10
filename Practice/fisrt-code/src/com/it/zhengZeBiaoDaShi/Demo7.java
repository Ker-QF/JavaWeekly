package com.it.zhengZeBiaoDaShi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo7 {
    public static void main(String[] args) {
        //邮箱
        String regex1 = "\\w+@[\\w&&[^_]]{2,6}(\\.[a-zA-Z]{2,3}){1,2}";
        //简易身份证
        String regex2 = "\\w+@[\\w&&[^_]]{2,6}(\\.[a-zA-Z]{2,3}){1,2}";
        String regex3 = "\\w+@[\\w&&[^_]]{2,6}(\\.[a-zA-Z]{2,3}){1,2}";
        String regex4 = "\\w+@[\\w&&[^_]]{2,6}(\\.[a-zA-Z]{2,3}){1,2}";

        String email1 = "test@qq.com";
        String email2 = "user_name@gmail.co.uk";

        Pattern p = Pattern.compile(regex1);

        Matcher m = p.matcher(email1);

        m.find();
        System.out.println(m.group());
    }
}
