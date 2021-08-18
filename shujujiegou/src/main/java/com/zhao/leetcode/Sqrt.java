package com.zhao.leetcode;
//69
public class Sqrt {
    public int mySqrt(int x) {
        if (x==0) return 0;
        int low =1;
        int high =x/2+1;
        while (low<=high){
            int mid =low +(high-low)/2;
            long mid2 =(long)mid*mid;
            if (mid2<=x){
                long mid22 = ((long) mid+1)*(mid+1);
                if (mid22<=x){
                    low=mid+1;
                }else {
                    return mid;
                }
            }else {
                high=mid-1;
            }
        }
        return -1;
    }
}
