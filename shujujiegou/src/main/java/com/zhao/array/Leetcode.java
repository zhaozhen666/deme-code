package com.zhao.array;

public class Leetcode {
    public int[] twoSum(int[] nums, int target) {
        int [] obj =new int[2];
        for (int i =0;i<nums.length;i++){
            for (int j=0;j<nums.length;j++){
                if (nums[i]+nums[j]==target){
                    obj[0]=i;
                    obj[1]=j;
                }
            }
        }
        return obj;
    }
}
