package com.it.dataStruct.Set;

import java.util.TreeSet;

public class Demo3 {
    public static void main(String[] args) {

        /*
        * 方法一：
        *   默认排序/自然排序
        *   Student实现Compareble接口，重写里面的抽象方法，再指定比较规则
        *
        * */


        TreeSet<Student> ts = new TreeSet<>();

        Student s1 = new Student("ikun",25);
        Student s2 = new Student("GGbond",19);
        Student s3 = new Student("ikun",25);

        ts.add(s1);
        ts.add(s2);

        System.out.println(ts.add(s3));

        for (Student stu : ts){
            System.out.println(stu.getAge());
        }
    }
}
