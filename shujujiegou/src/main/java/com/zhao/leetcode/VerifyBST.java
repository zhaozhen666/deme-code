package com.zhao.leetcode;
//剑指offer 33
public class VerifyBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    private  boolean valid =true;
    public boolean verifyPostorder(int[] postorder) {
        verify(postorder,0,postorder.length-1);
        return valid;
    }
    private  void verify(int[]postorder,int i,int j){
            if (valid==false) return;
            if (i>=j) return;
            int k=i;
            //分离左右子树
            while (k<j&&postorder[k]<postorder[j]){
                k++;
            }
            int p =k;
            while (p<j){
                if (postorder[p]<postorder[j]){
                    valid =false;
                    break;
                }
                p++;
            }
            if (valid==false) return;
            verify(postorder,i,k-1);
            verify(postorder,k,j-1);
    }
}
