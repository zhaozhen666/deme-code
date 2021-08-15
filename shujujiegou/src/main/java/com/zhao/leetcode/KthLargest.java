package com.zhao.leetcode;


public class KthLargest {
    public static void main(String[] args) {
        int [] nums ={3,2,1,6,5,4};
        System.out.println(findKthLargest(nums, 2));
    }

    public static int findKthLargest(int [] nums,int k){
        if (nums.length<k){
            return -1;
        }
        return quickSort(nums,0,nums.length-1,k);
    }
    private static int quickSort(int [] nums,int p,int r,int k){
        if (p>r){
            return -1;
        }
        int q = partition(nums,p,r);
        if (q-p+1==k){
            return nums[q];
        }else if (q-p+1<k){
            return quickSort(nums,q+1,r,k-(q-p+1));
        }else {
            return quickSort(nums,p,q-1,k);
        }
    }
    private  static int partition(int [] nums,int p,int r){
        int i=p;
        int j=r-1;
        while (i<j){
            while (i<j&&nums[i]>nums[r]){
                i++;
            }
            while (i<j&&nums[j]<=nums[r]){
                j--;
            }
            if (i<j){
                swap(nums,i,j);
                i++;
                j--;

            }
        }
        if (j>=p&&nums[j]<nums[r]){
            swap(nums,j,r);
            return j;
        }else {
            swap(nums,j+1,r);
            return j+1;
        }
    }

    private static void swap(int[] nums,int i,int j){
        int tmp = nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }
}
