package com.it.Recursion;

public class Demo1 {
    public static void main(String[] args) {

    }

    public static int getSum(int number){
        if (number == 1){
            return 1;
        }

        return number + getSum(number-1);
    }
}
