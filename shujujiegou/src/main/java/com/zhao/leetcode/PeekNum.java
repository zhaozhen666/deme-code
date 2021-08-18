package com.zhao.leetcode;
//852
public class PeekNum {
    public int peakIndexInMountainArray(int[] arr) {
        int n =arr.length;
        int low =0;
        int high =n-1;
        while (low<=high){
            int mid =low+(high-low)/2;
            if (mid==0){
                low=mid+1;
            }else if (mid==n-1){
                high=mid-1;

            }else if(arr[mid]>arr[mid-1]&&arr[mid]>arr[mid+1]){
                return mid;
            }else if (arr[mid]>arr[mid-1]){
                low = mid+1;
            }else {
                high =mid-1;
            }

        }
        return -1;
    }
}
