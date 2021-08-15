package com.zhao.sort;

public class SelectSort {

    void selectSort(int [] a,int n){
        if (n<=1){
            return;
        }
        for (int i=0;i<n;++i){
            int minPos = i;
            for (int j=i;i<n;++j){
                if (a[j]<a[minPos]){
                    minPos=j;
                }
            }
            int tmp = a[i];
            a[i] =a[minPos];
            a[minPos]=tmp;
        }
    }
}
