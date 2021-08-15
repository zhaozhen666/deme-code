package com.zhao.sort;

public class MergeSort {

    public void mergeSort(int[] a,int n){
        mergeSort_r(a,0,n-1);
    }

    private void mergeSort_r(int[] a, int p, int r) {
        if (p>=r){
            return;
        }
        int q = p+(r-p)/2;
        mergeSort_r(a,p,q);
        mergeSort_r(a,q+1,r);

        merge(a,p,q,r);
    }

    private void merge(int[] a, int p, int q, int r) {

        int i=p;
        int j =q+1;
        int k=0;
        int [] tmp = new int[r-p+1];
        while (i<=q&&j<=r){
            if (a[i]<=a[j]){
                tmp[k++] = a[i++];
            }else {
                tmp[k++] = a[j++];
            }
        }
        while (i<=q){
            tmp[k++] = a[i++];
        }
        while (j<=r){
            tmp[k++] = a[j++];
        }
        for (i=0;i<=r-p;++i){
            a[p+i] =tmp[i];
        }
    }
}
