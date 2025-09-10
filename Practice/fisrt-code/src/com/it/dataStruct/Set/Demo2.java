package com.it.dataStruct.Set;

import java.util.HashSet;

public class Demo2 {
    public static void main(String[] args) {
        HashSet<Student> hs = new HashSet<>();

        Student s1 = new Student("ikun",25);
        Student s2 = new Student("GGbond",19);
        Student s3 = new Student("ikun",25);

        System.out.println(hs.add(s1));
        System.out.println(hs.add(s2));
        System.out.println(hs.add(s3));

        System.out.println(hs);
    }
}
