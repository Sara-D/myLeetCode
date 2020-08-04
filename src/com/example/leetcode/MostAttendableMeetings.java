package com.example.leetcode;

import java.util.*;

/**
 * @author dw_dingdan1
 * @date 2020/7/28
 *
 * 题号：1353
 *
 * 题目：最多可以参加的会议数目
 *
 * 给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于 endDayi 。
 *
 * 你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。注意，一天只能参加一个会议。
 *
 * 请你返回你可以参加的 最大 会议数目。
 *
 * 示例 1：
 *
 * 输入：events = [[1,2],[2,3],[3,4]]
 * 输出：3
 * 解释：你可以参加所有的三个会议。
 * 安排会议的一种方案如上图。
 * 第 1 天参加第一个会议。
 * 第 2 天参加第二个会议。
 * 第 3 天参加第三个会议。
 *
 *
 * 示例 2：
 *
 * 输入：events= [[1,2],[2,3],[3,4],[1,2]]
 * 输出：4
 *
 *
 * 示例 3：
 *
 * 输入：events = [[1,4],[4,4],[2,2],[3,4],[1,1]]
 * 输出：4
 *
 *
 *
 *
 * 示例 4：
 *
 * 输入：events = [[1,100000]]
 * 输出：1
 *
 *
 *
 * 示例 5：
 *
 * 输入：events = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]
 * 输出：7
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-number-of-events-that-can-be-attended
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MostAttendableMeetings {

    public static void main(String[] args){
        int[][] events = {{1,4},{4,4},{2,2},{3,4},{1,1}};
        //int[][] events = {{1,100000}};
        //int[][] events = {{1,2},{2,3},{3,4},{1,2}};
        //int[][] events = {{1,1},{1,2},{1,3},{1,4},{1,5},{1,6},{1,7}};
        //int[][] events = {{1,2}, {2,3}, {3,4}};
        //int[][] events = {{1,2},{1,2},{3,3},{1,5},{1,5}};
        //int[][] events = {{1,1},{2,2},{2,2}};
        //int[][] events = {{27,27},{8,10},{9,11},{20,21},{25,29},{17,20},{12,12},{12,12},{10,14},{7,7},{6,10},{7,7},{4,8},{30,31},{23,25},{4,6},{17,17},{13,14},{6,9},{13,14}};
        //int[][] events = {{1,2}, {1,2}};
        System.out.println(new MostAttendableMeetings().maxEvents(events));
    }

    public int maxEvents(int[][] events) {
        Arrays.sort(events, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                return a[0] - b[0];
            }
        });
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int i = 0, startDay = events[0][0], endDay, n = events.length, res = 0;
        while (i < n || !queue.isEmpty()){
            while (i < n && events[i][0] == startDay){
                queue.add(events[i++][1]);
            }
            endDay = i >= n ? Integer.MAX_VALUE : events[i][0];
            while (startDay < endDay && !queue.isEmpty()){
                if(queue.peek() >= startDay){
                    startDay++;
                    res++;
                }
                queue.poll();
            }
            startDay = endDay;
        }
        return res;
    }
}
