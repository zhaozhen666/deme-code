package com.zhao.leetcode;
//98
public class ValidBST {
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
    private boolean isValid =true;
    public boolean isValidBST(TreeNode root) {

        if (root==null) return true;
        dfs(root);
        return isValid;
    }

    private  int[] dfs(TreeNode root){
        if (isValid==false) return null;
        int min =root.val;
        int max = root.val;
        if (root.left!=null){
            int [] LeftMinMax = dfs(root.left);
            if (isValid ==false) return null;
            if (LeftMinMax[1]>=root.val){
                isValid =false;
                return null;
            }
            min =LeftMinMax[0];
        }

        if (root.right!=null){
            int [] rightMinMax = dfs(root.right);
            if (isValid ==false) return null;
            if (rightMinMax[0]<=root.val){
                isValid =false;
                return null;
            }
            max =rightMinMax[1];
        }
        return new  int[]{min,max};
    }
}
