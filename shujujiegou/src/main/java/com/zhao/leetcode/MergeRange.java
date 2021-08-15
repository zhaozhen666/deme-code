package com.zhao.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//56
public class MergeRange {

    public int [][] merge(int [][] intervals){
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        List<int []> result = new ArrayList<>();
        int curLeft = intervals[0][0];
        int curRight = intervals[0][1];
        for (int i=1;i<intervals.length;++i){
            if (intervals[i][0]<=curRight){
                if (intervals[i][1]>curRight){
                    curRight=intervals[i][1];

                }
            }else {
                result.add(new int[]{curLeft,curRight});
                curLeft= intervals[i][0];
                curRight =intervals[i][1];
            }
        }
        result.add(new int[]{curLeft,curRight});
        return result.toArray(new int[result.size()][]);
    }
}
