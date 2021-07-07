package com.example.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author dw_dingdan1
 * @date 2020/8/6
 *
 * 题号：207
 *
 * 题目：课程表
 *
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 *
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 *
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 *  
 *
 * 提示：
 *
 * 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 1 <= numCourses <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CourseSchedule {

    private boolean valid = true;

    public static void main(String[] args){
        int numCourses = 4;
        int[][] prerequisites = new int[][]{{1,0}, {2,0}, {3,1}, {3,2}};
        System.out.println(new CourseSchedule().canFinishV2(numCourses, prerequisites));
    }

    public boolean canFinish(int numCourses, int[][] prerequisites){
        List<Integer>[] prerequisitesList = new ArrayList[numCourses];
        for(int[] prerequisite : prerequisites){
            List<Integer> lists = prerequisitesList[prerequisite[0]];
            if(lists == null){
                lists = new ArrayList<>();
                lists.add(prerequisite[1]);
                prerequisitesList[prerequisite[0]] = lists;
            }else {
                lists.add(prerequisite[1]);
            }
        }
        //记录课程状态 0-初始状态 1-挂起 2-已完成
        int[] status = new int[numCourses];
        for(int i=0; i<numCourses && valid; i++){
            if(status[i] == 0){
                dfs(i, prerequisitesList, status);
            }
        }
        return valid;
    }

    public void dfs(int courseIndex, List<Integer>[] prerequisitesList, int[] status){
        List<Integer> prerequisites = prerequisitesList[courseIndex];
        if(prerequisites == null){
            status[courseIndex] = 2;
            return;
        }
        status[courseIndex] = 1;
        for(int i=0; i<prerequisites.size(); i++){
            int prerequisiteCourse = prerequisites.get(i);
            if(status[prerequisiteCourse] == 1){
                valid = false;
                return;
            }else if(status[prerequisiteCourse] == 0){
                dfs(prerequisiteCourse, prerequisitesList, status);
                if(!valid){
                    return;
                }
            }
        }
        status[courseIndex] = 2;
    }

    public boolean canFinishV2(int numCourses, int[][] prerequisites){
        //邻接表 下标：课程ID 值：依赖该课程的课程列表
        List<Integer>[] prerequisitesList = new ArrayList[numCourses];
        //依赖数量
        int[] prerequisitesSize = new int[numCourses];
        for(int[] prerequisite : prerequisites){
            prerequisitesSize[prerequisite[0]]++;
            List<Integer> lists = prerequisitesList[prerequisite[1]];
            if(lists == null){
                lists = new ArrayList<>();
                lists.add(prerequisite[0]);
                prerequisitesList[prerequisite[1]] = lists;
            }else {
                lists.add(prerequisite[0]);
            }
        }
        int count = 0;
        //只存入不受约束的课程
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<numCourses; i++){
            if(prerequisitesSize[i] == 0)
                queue.offer(i);
        }
        while (!queue.isEmpty()){
            Integer curIndex = queue.poll();
            List<Integer> courses;
            if((courses = prerequisitesList[curIndex]) != null) {
                for(Integer course : courses){
                    if(--prerequisitesSize[course] == 0)
                        queue.offer(course);
                }
            }
            count++;
        }
        if(count == numCourses){
            return true;
        }
        return false;
    }

}
