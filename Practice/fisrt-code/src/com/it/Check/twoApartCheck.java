package com.it.Check;

public class twoApartCheck {
    public static void main(String[] args) {
        int[] arr = {0,3,54,2,6};

        long start = System.currentTimeMillis();

        quickSort(arr,0,4);

        long end = System.currentTimeMillis();

        /*for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }*/
        System.out.println(end-start);
        System.out.println(binarySearch(arr,6,0,arr.length-1));

    }

    public static int locpos(int[] arr,int low,int high){
        int pivot = arr[low];

        while (low < high){
            while (low < high && arr[high] >= pivot){
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= pivot){
                low++;
            }
            arr[high] = arr[low];
        }

        arr[high] = pivot;

        return high;
    }

    public static void quickSort(int[] arr,int l,int r){
        if (l < r){
            int pi = locpos(arr,l,r);
            quickSort(arr,l,pi-1);
            quickSort(arr,pi+1,r);
        }

    }

    public static int binarySearch(int[] arr,int number,int low,int hig){
        int mid = (low + hig)/2;

        if (low > hig){
            return -1;
        }else if (arr[mid] > number){
             return binarySearch(arr,number,low,mid-1);
        }else if (arr[mid] < number){
            return binarySearch(arr,number,mid + 1,hig);
        }else{
            return mid;
        }
    }
}
