package com.zhao.leetcode;

import java.util.HashMap;

public class WordsFrequency {
    HashMap<String,Integer> map =new HashMap<>();
    public WordsFrequency(String[] book) {
        for (String word :book){
           Integer  count =map.get(word);
            if (count==null||count==0){
                count=1;
                map.put(word,1);
            }else {
                map.put(word,count+1);
            }
        }
    }

    public int get(String word) {
        if (!map.containsKey(word)){
            return 0;
        }
        return map.get(word);
    }
}
