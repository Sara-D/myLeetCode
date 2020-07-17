package com.example.leetcode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dw_dingdan1
 * @date 2020/7/17
 *
 * 题号：210
 *
 * 题目：课程表II
 *
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 *
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 *
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 *
 * 示例 1:
 *
 * 输入: 2, [[1,0]]
 * 输出: [0,1]
 * 解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 *
 * 示例 2:
 *
 * 输入: 4, [[1,0],[2,0],[3,1],[3,2]]
 * 输出: [0,1,2,3] or [0,2,1,3]
 * 解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 *      因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 *
 *说明:
 *
 * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法（https://blog.csdn.net/woaidapaopao/article/details/51732947）(https://www.cnblogs.com/liushang0419/archive/2011/05/06/2039386.html)。
 * 你可以假定输入的先决条件中没有重复的边。
 * 提示:
 *
 * 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
 * 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟）（https://www.coursera.org/specializations/algorithms），介绍拓扑排序的基本概念。
 * 拓扑排序也可以通过 BFS 完成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CourseScheduleV2 {

    public static void main(String[] args){
        int numCourses = 4;
        int[][] prerequisites = new int[][]{{3,0}, {0,1}};
        System.out.println(Arrays.toString(new CourseScheduleV2().findOrder(numCourses, prerequisites)));
    }

    private boolean valid = true;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(numCourses == 0)
            return new int[]{};
        //将先决条件变成邻接表
        List<Integer>[] adjacency = new List[numCourses];
        for(int i=0;i<prerequisites.length;i++){
            int[] prerequisite = prerequisites[i];
            if(adjacency[prerequisite[0]] == null){
                List<Integer> list = new ArrayList<>();
                list.add(prerequisite[1]);
                adjacency[prerequisite[0]] = list;
            }else {
                adjacency[prerequisite[0]].add(prerequisite[1]);
            }
        }
        int curIndex = 0;
        int[] courses = new int[numCourses];
        //存储当前节点状态 0-初始化状态 1-因等待先决条件被挂起 2-课程已安排
        int[] status = new int[numCourses];
        for(int i=0;i<numCourses && valid && curIndex <= numCourses-1; i++){
            if(status[i] != 2) {
                curIndex = dfs(curIndex, i, courses, adjacency, status);
            }
        }
        if(valid)
            return courses;
        return new int[]{};
    }

    public int dfs(int index, int courseId, int[] courses, List<Integer>[] adjacency, int[] status){
        status[courseId] = 1;
        List<Integer> prerequisites = adjacency[courseId];
        if(prerequisites == null) {
            courses[index] = courseId;
            status[courseId] = 2;
            return index + 1;
        }else{
            int curIndex = index;
            for(int i=0;i<prerequisites.size() && valid; i++){
                if(status[prerequisites.get(i)] == 1){
                    //出现循环
                    valid = false;
                    return -1;
                }else if(status[prerequisites.get(i)] == 0){
                    curIndex = dfs(curIndex, prerequisites.get(i), courses, adjacency, status);
                    if(!valid)
                        return -1;
                }
            }
            courses[curIndex] = courseId;
            status[courseId] = 2;
            return curIndex + 1;
        }
    }

}
