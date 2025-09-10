package com.it.zhengZeBiaoDaShi;

public class Demo1 {
    public static void main(String[] args) {
        String str = "123";
        String regex1 = "1[1-9]\\d{1}";
        String regex2 = "1[1-9]\\d{9}";
        String regex3 = "1[1-9][1-9]";

        System.out.println(str.matches(regex1));
        System.out.println(str.matches(regex2));
        System.out.println(str.matches(regex3));

    }
}
