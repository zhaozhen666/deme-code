package com.zhao.leetcode;

public class Pow {
    public double myPow(double x, int n) {
        if (n>=0){
            return rPow(x,n);
        }else {
            return 1/(rPow(x,-1*(n+1))*x);
        }
    }
    public double rPow(double x, int n){
        if (n==0) return 1;
        double halfPow = rPow(x,n/2);
        if (n%2==1){
            return halfPow*halfPow*x;
        }else {
            return halfPow*halfPow;
        }
    }
}
