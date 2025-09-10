package com.it.Recursion;

public class Demo3 {
    public static void main(String[] args) {
        System.out.println(getSum(0));
    }

    public static int getSum(int day){
        if (day == 10){
            return 1;
        }

        return (getSum(day+1)+1)*2;
    }
}
