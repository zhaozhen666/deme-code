package com.zhao.leetcode;

import java.util.ArrayList;
import java.util.List;

// 78 子集
public class SubSet {

    private  List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        backTrack(nums,0,new ArrayList<>());
        return result;
    }

    public void backTrack(int [] nums,int k,List<Integer> path){
        if (k==nums.length){
            result.add(new ArrayList<>(path));
            return;
        }

        backTrack(nums,k+1,path);
        path.add(nums[k]);
        backTrack(nums, k+1, path);
        path.remove(path.size()-1);
    }
}
