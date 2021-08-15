package com.zhao.leetcode;

public class SpecialSort {
    public int [] exchange(int [] nums){
        int i=0;
        int j=nums.length-1;
        while (i<j){
            if (nums[i]%2==1){
                i++;
                continue;
            }
            if (nums[j]%2==0){
                j--;
                continue;
            }
            int tmp = nums[i];
            nums[i]=nums[j];
            nums[j]=tmp;
            i++;
            j--;
        }
        return nums;
    }
}
