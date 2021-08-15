package com.zhao.leetcode;

public class Sanbu {
    private int mod = 1000000007;
    private int [] memo = new int[1000001];
    public int wayToStep(int n){
        if (n==1) return 1;
        if (n==2) return 2;
        if (n==3) return 4;
        if (memo[n]!=0) return memo[n];
        memo[n] = ((wayToStep(n-1)+wayToStep(n-2))%mod+wayToStep(n-3))%mod;
        return memo[n];
    }

    public  int waysToStep(int n){
        if (n==1) return 1;
        if (n==2) return 2;
        if (n==3) return 4;
        int a =1;
        int b =2;
        int c =4;
        int d =0;
        for (int i=4;i<n;i++){
            d=((c+b)%mod+a)%mod;
            a=b;
            b=c;
            c=d;

        }
        return d;
    }
}
