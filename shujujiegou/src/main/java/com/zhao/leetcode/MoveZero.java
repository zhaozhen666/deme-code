package com.zhao.leetcode;

public class MoveZero {
    public static void moveZeros(int [] nums){
        int j =0;
        for (int i=0;i<nums.length;++i){
            if (nums[i]!=0){
                nums[j] =nums[i];
                if (i!=j){
                    nums[i]=0;
                }
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums ={1,3,0,100};
        moveZeros(nums);
    }
}
