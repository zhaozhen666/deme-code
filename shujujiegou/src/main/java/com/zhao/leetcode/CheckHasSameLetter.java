package com.zhao.leetcode;

import java.util.HashMap;
//01.02
public class CheckHasSameLetter {

    public boolean CheckPermutation(String s1, String s2) {
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0;i<s1.length();++i){
            char c= s1.charAt(i);
            int count =1;
            if (map.containsKey(c)){
                count+=map.get(c);
            }
            map.put(c,count);
        }

        for(int i=0;i<s2.length();++i){
            char c= s2.charAt(i);
            if (!map.containsKey(c)){
                return false;
            }
            int count =map.get(c);
            if (count==0) return false;
            map.put(c,count-1);
        }
        for (int i=0;i<s1.length();++i){
            char c= s1.charAt(i);
            if (map.get(c)!=0){
                return false;
            }
        }
        return true;
    }
}
