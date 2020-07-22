package com.example.leetcode;

/**
 * @author dw_dingdan1
 * @date 2020/7/22
 *
 * 题号：154
 *
 * 题目：寻找旋转排序数组中的最小值 II
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 请找出其中最小的元素。
 *
 * 注意数组中可能存在重复的元素。
 *
 * 示例 1：
 *
 * 输入: [1,3,5]
 * 输出: 1
 * 示例 2：
 *
 * 输入: [2,2,2,0,1,2]
 * 输出: 0
 * 说明：
 *
 * 这道题是 寻找旋转排序数组中的最小值 的延伸题目。
 * 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？ 当所有元素全相等的时候 时间复杂度为O(n)
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class RotateArrayFindMinV2 {

    public static void main(String[] args){
        int[] nums = new int[]{2,2,2,2,2};
        System.out.println(new RotateArrayFindMinV2().findMin(nums));
    }

    public int findMin(int[] nums) {
        return findMinInner(0, nums.length-1, nums);
    }

    public int findMinInner(int from, int to, int[] nums){
        if(from >= to)
            return nums[from];
        int middle = from + (to - from) / 2;
        if(nums[middle] < nums[to]) {
            return findMinInner(from, middle, nums);
        }else if(nums[middle] > nums[to]){
            return findMinInner(middle + 1, to, nums);
        }else {
            return Math.min(findMinInner(from, middle, nums), findMinInner(middle + 1, to, nums));
        }
    }
}
