package com.it.jinzhi;

public class Demo1 {
    public static void main(String[] args) {
        System.out.println(toBinaryString(10));

        System.out.println(Integer.toBinaryString(10));
    }

    public static String toBinaryString(int number){
        StringBuilder sb = new StringBuilder();

        while (true){
            if (number == 0){
                break;
            }

            int remaindar = number % 2;

            sb.insert(0,remaindar);

            number /= 2;
        }

        return sb.toString();
    }
}


