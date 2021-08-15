package com.zhao.sort;

public class InsertSort {
    void insertSort(int [] a,int n){
        if (n<=1){
            return;
        }
        for (int i=1;i<n;++i){
            int target = a[i];
            int j=i-1;
            for (;j>=0;j--){
                if (a[j]>target){
                    a[j+1] =a[j];
                }else {
                    break;
                }
            }
            a[j+1] =target;
        }

    }
}
