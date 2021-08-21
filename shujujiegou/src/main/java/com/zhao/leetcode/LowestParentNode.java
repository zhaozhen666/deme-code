package com.zhao.leetcode;
//236
public class LowestParentNode {
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
    private TreeNode lca =null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            dfs(root, p, q);
            return lca;
    }

    private int dfs(TreeNode root, TreeNode p, TreeNode q){
        if (root==null) return 0;
        int leftContains = dfs(root.left, p, q);
        if (lca!=null){
            return 2;
        }

        int rightContains = dfs(root.right, p, q);
        if (lca!=null){
            return 2;
        }
        int rootContains = 0;
        if (root==p||root ==q){
            rootContains=1;
        }
        if (rootContains ==0&& leftContains==1&&rightContains==1){
            lca =root;
        }
        if (rootContains ==1&& (leftContains==1||rightContains==1)){
            lca =root;
        }
        return leftContains+rightContains+rootContains;
    }
}
