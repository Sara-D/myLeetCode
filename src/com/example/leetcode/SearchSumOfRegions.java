package com.example.leetcode;

/**
 * @author dw_dingdan1
 * @date 2020/7/23
 *
 * 题号：307
 *
 * 题目：区域和检索-数组可修改
 *
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 *
 * update(i, val) 函数可以通过将下标为 i 的数值更新为 val，从而对数列进行修改。
 *
 * 示例:
 *
 * Given nums = [1, 3, 5]
 *
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * 说明:
 *
 * 数组仅可以在 update 函数下进行修改。
 * 你可以假设 update 函数与 sumRange 函数的调用次数是均匀分布的。
 *
 * 1、遍历 2、sqrt分解 3、线段树
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-query-mutable
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SearchSumOfRegions {

    public static void main(String[] args){
        int[] nums = {1, 3, 5, 7};
        SearchSumOfRegions searchSumOfRegions = new SearchSumOfRegions(nums);
        System.out.println(searchSumOfRegions.sumRange(0, 3));
    }

    private int[] tree;

    private int n;

    public SearchSumOfRegions(int[] nums) {
        n = nums.length;
        //成员变量左移一位不会改变成员变量原来的值
        tree = new int[n << 1];
        //自下而上构建完全二叉树
        for (int i = n, j = 0; i < n << 1; i++, j++) {
            tree[i] = nums[j];
        }
        for (int i = n - 1; i > 0; i--) {
            //局部变量左移一位会改变原来的值
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    public void update(int pos, int val) {
        int indexI = pos + n;
        tree[indexI] = val;
        int upIndex = indexI/2;
        while(upIndex >= 1) {
            tree[upIndex] = tree[upIndex * 2] + tree[upIndex * 2 + 1];
            upIndex /= 2;
        }
    }

    public int sumRange(int l, int r) {
        int sum = 0;
        l = l + n;
        r = r + n;
        while (l <= r){
            if(l % 2 == 1){
                sum += tree[l];
                l++;
            }
            if(r % 2 == 0){
                sum += tree[r];
                r--;
            }
            l /= 2;
            r /= 2;
        }
        return sum;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
