package com.example.leetcode;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dw_dingdan1
 * @date 2020/7/16
 *
 * 题号：785
 *
 * 题目：判断二分图
 *
 * 给定一个无向图graph，当这个图为二分图时返回true。
 *
 * 如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。
 *
 * graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。每个节点都是一个在0到graph.length-1之间的整数。这图中没有自环和平行边： graph[i] 中不存在i，并且graph[i]中没有重复的值。
 *
 *
 * 示例 1:
 * 输入: [[1,3], [0,2], [1,3], [0,2]]
 * 输出: true
 * 解释:
 * 无向图如下:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * 我们可以将节点分成两组: {0, 2} 和 {1, 3}。
 *
 *
 *
 * 示例 2:
 * 输入: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * 输出: false
 * 解释:
 * 无向图如下:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * 我们不能将节点分割成两个独立的子集。
 *
 *
 *注意:
 *
 * graph 的长度范围为 [1, 100]。
 * graph[i] 中的元素的范围为 [0, graph.length - 1]。
 * graph[i] 不会包含 i 或者有重复的值。
 * 图是无向的: 如果j 在 graph[i]里边, 那么 i 也会在 graph[j]里边。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/is-graph-bipartite
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsBipartite {

    public static void main(String[] args){
        int[][] graph = new int[][]{{1},{0}};
        System.out.println(new IsBipartite().isBipartite(graph));
    }

    //用来提前终止某些某些不必要的染色
    private boolean valid = true;

    public boolean isBipartite(int[][] graph) {
        //AtomicInteger counter = new AtomicInteger(0);
        //int n = graph.length;
        //int[] colors = new int[n];
        //for(int i=0;i<n && valid;i++){
        //    if(colors[i] == 0) {
        //        dfs(i, 1, colors, graph, counter);
        //    }
        //}
        //System.out.println("dfs次数:" + counter.get());
        //return valid;


        //广度优先搜索
        int n = graph.length;
        int[] colors = new int[n];
        for(int i=0; i<n; i++){
            if(colors[i] == 0) {
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                colors[i] = 1;
                while (!queue.isEmpty()){
                    Integer cur = queue.poll();
                    int colorNei = colors[cur] == 1 ? 2 : 1;
                    for(int j = 0; j < graph[cur].length; j++){
                        if(colors[graph[cur][j]] == 0){
                            //先染色 然后加入队列
                            colors[graph[cur][j]] = colorNei;
                            queue.offer(graph[cur][j]);
                        }else if(colors[graph[cur][j]] != colorNei){
                            return false;
                        }else {
                            //死循环
                            //queue.offer(graph[i][j]);
                        }
                    }
                }
            }
        }
        return true;
    }

    //深度优先搜索
    private void dfs(int index, int color, int[] colors, int[][] graph, AtomicInteger counter){
        System.out.println("当前染色的节点:" + index);
        counter.incrementAndGet();
        colors[index] = color;
        int cNei = color == 1 ? 2: 1;
        for(int i=0;i<graph[index].length;i++){
            int neighbor = graph[index][i];
            if(colors[neighbor] == 0){
                dfs(neighbor, cNei, colors, graph, counter);
                if (!valid) {
                    return;
                }
            }else if(colors[neighbor] == color){
                valid = false;
                return;
            }
        }
    }
}
