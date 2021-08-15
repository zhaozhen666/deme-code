package com.zhao.leetcode;
//75
public class Color {
    public void sortColors(int[] nums) {
        int p=0;
        int q=nums.length-1;
        while (p<q){
            if (nums[p]!=2){
                p++;
                continue;
            }
            if (nums[q]==2){
                q--;
                continue;
            }
            swap(nums,p,q);
            p++;
            q--;
        }
        int i = 0;
        int j= p;
        if (nums[j]==2) j--;
        while (i<j){
            if (nums[i]==0){
                i++;
                continue;
            }
            if (nums[j]==1){
                j--;
                continue;
            }
            swap(nums,i,j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums,int i,int j){
        int tmp = nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }
}
