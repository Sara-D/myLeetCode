package com.example.util;
/**
 * @author dw_dingdan1
 * @date 2020/6/12
 */
public class TreeUtil {

    public static TreeNode buildTree(){
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(1);
        return root;
    }

    public static void printTree(TreeNode root){
        if(root == null){
            return;
        }
        printTree(root.left);
        printTree(root.right);
        System.out.print(root.val + " ");
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x) { val = x; }
    }
}
