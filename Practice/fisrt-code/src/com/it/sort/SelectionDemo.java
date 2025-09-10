package com.it.sort;

public class SelectionDemo {
    public static void main(String[] args) {
        int[] arr = {0,3,54,2,6};

        selectionSort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void selectionSort(int[] arr){
        for (int i = 0;i < arr.length-1;i++){
            for (int j = i+1;j < arr.length;j++){
                if (arr[j] < arr[i]){
                    int t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;
                }
            }
        }
    }
}
