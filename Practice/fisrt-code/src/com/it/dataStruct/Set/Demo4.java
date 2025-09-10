package com.it.dataStruct.Set;

import java.util.Comparator;
import java.util.TreeSet;

public class Demo4 {
    public static void main(String[] args) {

        //函数式接口才可改lambda表达式
        //o1表示当前要添加的元素
        //o2表示已经在红黑树存在的元素
        TreeSet<String> ts = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //按照长度排序
                int i = o1.length() - o2.length();

                i = i == 0 ? o1.compareTo(o2):i;
                return i;
            }
        });

        //lambda表达式
        /*
            TreeSet<String> ts = new TreeSet<>((o1, o2)->{
                //按照长度排序
                int i = o1.length() - o2.length();

                i = i == 0 ? o1.compareTo(o2):i;
                return i;
        });
        */

        ts.add("c");
        ts.add("ab");
        ts.add("df");
        ts.add("qwer");
    }
}
