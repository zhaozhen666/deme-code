package com.zhao.leetcode;

public class SearchInsert {
    public int searchInsert(int[] nums, int target) {
        int low =0;
        int high =nums.length-1;
        while (low<=high){
            int mid = low+(high-low)/2;
            if (nums[mid]>=target){
                if (mid==0||nums[mid-1]<target){
                    return mid;
                }else {
                    high=mid-1;
                }
            }else {
                low=mid+1;
            }
        }
        return nums.length;
    }
}
