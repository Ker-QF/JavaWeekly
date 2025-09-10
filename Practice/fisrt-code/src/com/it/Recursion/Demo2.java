package com.it.Recursion;

public class Demo2 {
    public static void main(String[] args) {
        System.out.println(getJC(5));
    }

    public static int getJC(int n){
        if (n == 1){
            return 1;
        }

        return n*getJC(n-1);
    }
}
