package com.it.dataStruct.Set;

import java.util.Objects;

public class Student implements Comparable<Student>{
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public int compareTo(Student o) {
        //this表示当前要添加的元素
        //o表示红黑树已存在的元素
        System.out.println("------------");
        System.out.println("this:" + this.getAge());
        System.out.println("o:" + o.getAge());
        System.out.println("------------");

        //返回值
        //负数，小的，存左边；正数，大的存右边
        //0，舍弃
        //只看年龄升序
        return this.getAge() - o.getAge();
    }
}
