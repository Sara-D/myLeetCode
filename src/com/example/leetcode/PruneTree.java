package com.example.leetcode;

import com.example.util.TreeUtil;
/**
 * @author dw_dingdan1
 * @date 2020/6/12
 */
public class PruneTree {

    public static void main(String[] args){
        TreeUtil.TreeNode root = TreeUtil.buildTree();
        TreeUtil.printTree(root);
        System.out.println();
        TreeUtil.TreeNode newRoot = pruneTree(root);
        TreeUtil.printTree(newRoot);
    }

     public static TreeUtil.TreeNode pruneTree(TreeUtil.TreeNode root){
        if(root == null){
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if(root.left == null && root.right == null && root.val == 0){
            return null;
        }
        return root;
     }
}
