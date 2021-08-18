package com.zhao.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ThreeSum {

    public static void main(String[] args) {
        int[] nums ={-1,0,1,2,-1,-4};
       threeSum(nums);
    }
    public  static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n =nums.length;
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i=0;i<n;++i){
            hashMap.put(nums[i],i);
        }
        for (int i=0;i<n;++i){
            if (i!=0&&nums[i]==nums[i-1])
                continue;
            for (int j=i+1;j<n;++j){
                if (j!=i+1&&nums[j]==nums[j-1])
                    continue;
                int c =-1*(nums[i]+nums[j]);
                if (!hashMap.containsKey(c)){
                    continue;
                }
                int k = hashMap.get(c);
                if (k>j){
                    List<Integer> items = new ArrayList<>();
                    items.add(nums[i]);
                    items.add(nums[j]);
                    items.add(nums[k]);
                    result.add(items);
                }
            }
        }
        return result;
    }
}
