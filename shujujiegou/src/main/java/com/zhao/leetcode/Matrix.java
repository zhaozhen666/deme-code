package com.zhao.leetcode;
// 74
public class Matrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int h =matrix.length;
        int w = matrix[0].length;
        int low =0;
        int high = h*w-1;
        while (low<=high){
            int mid =(low+high)/2;
            int midVal = matrix[mid/w][mid%w];
            if (target==midVal){
                return true;
            }else if(target<midVal){
                high =mid-1;
            }else{
                low =mid+1;
            }
        }
        return false;
    }
}
