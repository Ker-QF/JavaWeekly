package com.it.dataStruct.Set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Demo1 {
    public static void main(String[] args) {
        Set<String> s = new HashSet<>();

        s.add("烈焰马");
        s.add("洛奇亚");

        Iterator<String> it = s.iterator();

        /*while(it.hasNext()){
            String str = it.next();
            System.out.println(str);
        }*/

        //增强for
        /*for (String str : s) {
            System.out.println(str);
        }*/

        //lambda表达式
        s.forEach(str-> System.out.println(str));
    }
}
