package com.zhao.leetcode;

public class Multi {
    public int multiply(int A, int B) {
        if (A==1) return B;
        int halfValue = multiply(A/2,B);
        if (A%2==1){
            return halfValue+halfValue+B;
        }else {
            return halfValue+halfValue;
        }
    }
}
