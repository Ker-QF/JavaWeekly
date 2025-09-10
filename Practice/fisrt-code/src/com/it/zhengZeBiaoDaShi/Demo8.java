package com.it.zhengZeBiaoDaShi;

public class Demo8 {
    public static void main(String[] args) {

        String s1 = "a123a";

        String regex1 = "(.).+\\1";
        System.out.println(s1.matches(regex1));

    }
}
