package com.it.Recursion;

public class Demo4 {
    public static void main(String[] args) {
        int[] step = new int[21];

        step[1] = 1;
        step[2] = 2;

        for (int i = 3;i <= 20;i++){
            step[i] = step[i-1] + step[i-2];
        }

        System.out.println(step[20]);
    }
}
