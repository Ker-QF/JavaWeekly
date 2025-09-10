package com.it.sort;

public class InsertDemo {
    public static void main(String[] args) {
        int[] arr = {0,3,54,2,6};

        insertSort2(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void insertSort1(int[] arr){
        for (int i = 1;i < arr.length-1;i++){
            int j;
            int t = arr[i+1];
            for (j = i+1;j > 0;j--){
                if (arr[j-1] > arr[j]){
                    arr[j] = arr[j-1];
                }else{
                    break;
                }
            }
            arr[j] = t;
        }
    }

    public static void insertSort2(int[] arr){
        int startIndex = -1;
        for (int i = 0;i < arr.length-1;i++){
            if (arr[i] > arr[i+1]){
                startIndex = i+1;
                break;
            }
        }

        for (int i = startIndex;i < arr.length;i++){
            int j = i;

            while (j > 0 && arr[j] < arr[j-1]){
                int t = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = t;
                j--;
            }
        }
    }
}
