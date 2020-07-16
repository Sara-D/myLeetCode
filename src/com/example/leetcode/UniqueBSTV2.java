package com.example.leetcode;

import com.example.models.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dw_dingdan1
 * @date 2020/7/15
 *
 * 题号：95
 *
 * 题目：不同的二叉搜索树 II
 *
 * 示例：
 *
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class UniqueBSTV2 {

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode>[][] rootNodeTrees = new List[n+1][n+1];
        return generateTreesInner(1, n, rootNodeTrees);
    }

    public List<TreeNode> generateTreesInner(int from, int to, List<TreeNode>[][] rootNodeTrees){
        if(from > to){
            return null;
        }
        if(from == to){
            return Arrays.asList(new TreeNode(from));
        }
        if(rootNodeTrees[from][to] != null){
            return rootNodeTrees[from][to];
        }
        List<TreeNode> list = new ArrayList<>();
        for(int i=from; i<=to; i++){
            List<TreeNode> lefts = generateTreesInner(from, i-1, rootNodeTrees);
            List<TreeNode> rights = generateTreesInner(i+1, to, rootNodeTrees);
            if(lefts != null && rights != null){
                for(TreeNode left : lefts){
                    for(TreeNode right : rights){
                        TreeNode root = new TreeNode(i);
                        root.left = left;
                        root.right = right;
                        list.add(root);
                    }
                }
            }else if(lefts != null){
                for(TreeNode left : lefts){
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    list.add(root);
                }
            }else {
                for(TreeNode right : rights){
                    TreeNode root = new TreeNode(i);
                    root.right = right;
                    list.add(root);
                }
            }
        }
        rootNodeTrees[from][to] = list;
        return list;
    }
}
