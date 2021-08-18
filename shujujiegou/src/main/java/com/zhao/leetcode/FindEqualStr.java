package com.zhao.leetcode;
//10.05
public class FindEqualStr {
    public int findString(String[] words, String s) {
        int low =0;
        int high =words.length-1;
        while (low<=high){
            int mid = (high+low)/2;
            if (words[mid].equals(s))
            {
                return mid;
            }
            else if (words[mid].equals("")){
               if (words[low].equals(s)){
                   return low;
               }else {
                   low++;
               }

            }else if (words[mid].compareTo(s)<0){
                low=mid+1;

            }else {
                high=mid-1;

            }
        }
        return -1;
    }
}
