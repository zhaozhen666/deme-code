package com.zhao.sort;

public class QuickSort {

    public static void quickSort(int [] a,int n){
        quickSort_r(a,0,n-1);
    }

    private static void quickSort_r(int[] a, int p, int r) {
        if (p>=r){
            return;
        }
        int q = partition(a,p,r);
        quickSort_r(a,p,q-1);
        quickSort_r(a,q+1,r);
    }

    private static int partition(int[] a, int p, int r) {
        int i=p-1;
        for (int j=p;j<r;++j){
            if (a[j]<a[r]){
                swap(a,i+1,j);
                i++;
            }
        }
        swap(a,i+1,r);
        return i+1;
    }
    private static void swap(int[] nums,int i,int j){
        int tmp = nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }
}
