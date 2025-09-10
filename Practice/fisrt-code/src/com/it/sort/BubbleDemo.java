package com.it.sort;

public class BubbleDemo {
    public static void main(String[] args) {
        int[] arr = {0,3,54,2,6};

        bublleSort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void bublleSort(int[] arr){
        for (int i = 0;i < arr.length-1;i++){
            for (int j = 0;j < arr.length-i-1;j++){
                if (arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
