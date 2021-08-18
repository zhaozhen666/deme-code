package com.zhao.leetcode;
//367
public class PerfectSquare {
    public boolean isPerfectSquare(int num) {
        int n = num;
        int low =0;
        int high =n;
        while (low<=high){
            int mid =low+(high-low)/2;
            long multi = (long)mid*mid;
            if (multi==num){
                return true;
            }else if (multi>num){
                high =mid-1;
            }else {
                low =mid+1;
            }
        }
        return false;
    }
}
