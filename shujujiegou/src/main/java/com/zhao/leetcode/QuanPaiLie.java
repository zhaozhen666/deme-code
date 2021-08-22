package com.zhao.leetcode;

import java.util.ArrayList;
import java.util.List;

//46 全排列
public class QuanPaiLie {

    private List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> path = new ArrayList<>();
        backtrack(nums,0,path);
        return result;
    }

    private  void  backtrack(int [] nums,int k,List<Integer> path){
        if (k==nums.length){
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i=0;i<nums.length;++i){
            if (path.contains(nums[i])){
                continue;
            }
            //做选择
            path.add(nums[i]);
            //递归
            backtrack(nums,k+1,path);
            path.remove(path.size()-1);
        }
    }
}
