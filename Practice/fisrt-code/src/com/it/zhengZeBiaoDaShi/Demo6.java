package com.it.zhengZeBiaoDaShi;

public class Demo6 {
    public static void main(String[] args) {
        /*
            字符串（String）基础
                代码中定义了一个字符串变量str，包含了中文、英文字母、数字等多种字符
                String 是 Java 中的不可变类，所有修改字符串的方法都会返回新的字符串对象
            String 类的 replaceAll () 方法
                方法作用：使用给定的替换字符串替换匹配正则表达式的所有子字符串
                语法：public String replaceAll(String regex, String replacement)
                注意：该方法会返回一个新的字符串，原字符串不会被修改
            正则表达式（Regular Expression）
                代码中使用的正则表达式是"[\\w&&[^_]]+"
                解析：
                \\w：表示匹配任何单词字符，等价于[a-zA-Z0-9_]（字母、数字、下划线）
                &&[^_]：这是正则表达式中的交集操作，表示排除下划线_
                +：表示匹配前面的子表达式一次或多次
                整体含义：匹配由字母或数字组成的一个或多个连续字符（不含下划线）
                替换逻辑
                代码将字符串中所有符合正则表达式"[\\w&&[^_]]+"的部分替换为 "vs"
                对于示例字符串"达克莱伊adwef123快龙afwwfe456哲尔尼亚斯"：
                "adwef123" 会被替换为 "vs"
                "afwwfe456" 会被替换为 "vs"
                最终输出结果为："达克莱伊vs快龙vs哲尔尼亚斯"

        */

        String str = "达克莱伊adwef123快龙afwwfe456哲尔尼亚斯";

        String result = str.replaceAll("[\\w&&[^_]]+","vs");

        //System.out.println(result);

        //生成左边Ctrl+Alt+V
        String[] arr = str.split("[\\w&&[^_]]+");

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
