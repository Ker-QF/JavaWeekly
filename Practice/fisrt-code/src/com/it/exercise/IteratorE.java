package com.it.exercise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

public class IteratorE {
    public static void main(String[] args) {

        //迭代器遍历
        Collection<String> coll = new ArrayList<>();
        coll.add("a");
        coll.add("b");
        coll.add("c");

        //通过coll里的iterator方法获取Iterator的对象（返回值）
        Iterator<String> it = coll.iterator();

        while(it.hasNext()){
            String str = it.next();
            System.out.println(str);
        }

        //增强for遍历(coll.for快捷)
        for (String s : coll){
            //s示第三方变量，修改无效
            System.out.println(s);
        }

        //lambda表达式遍历
        //s依次表示集合中的每一个数据
        coll.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {

                System.out.println(s);
            }
        });//匿名内部类

        //lambda表达式
        //数据类型可以省略，方法体留下
        coll.forEach(s-> System.out.println(s));

    }
}
