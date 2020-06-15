package com.example.leetcode;

import com.example.util.TreeUtil;

/**
 * @author dw_dingdan1
 * @date 2020/6/15
 */
public class TrimBST {

    public static TreeUtil.TreeNode trimBST(TreeUtil.TreeNode root, int L, int R){
        if(root == null){
            return null;
        }
        if(root.val < L){
            return trimBST(root.right, L, R);
        }
        if(root.val > R){
            return trimBST(root.left, L, R);
        }
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }
}
