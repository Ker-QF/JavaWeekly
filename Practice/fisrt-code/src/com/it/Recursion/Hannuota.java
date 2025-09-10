package com.it.Recursion;

public class Hannuota {
    public static void main(String[] args) {
        hanoi('A','B','C',3);
    }

    private static void hanoi(char a, char b, char c, int n) {
        if (n == 1){
            System.out.println(a + "--->" + c);
        }else{
            hanoi(a,c,b,n-1);
            System.out.println(a + "--->" + c);
            hanoi(b,a,c,n-1);
        }
    }
}
