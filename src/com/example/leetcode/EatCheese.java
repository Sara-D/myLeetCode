package com.example.leetcode;

/**
 * @author dw_dingdan1
 * @date 2020/6/12
 */
public class EatCheese {

    static int n = 4;

    static double length = Integer.MAX_VALUE;

    static boolean[] accessStates = new boolean[n+1];

    static int[][] positions = new int[][]{{0,0},{1, 1},{1, -1},{-1, -1},{-1, 1}};

    static double[][] distances = new double[n+1][n+1];

    public static void main(String[] args){
        //如果不做优化 时间复杂度 n!
        dfs(0, 0, 0);
        System.out.println("最短距离:" + length);
    }

    /**
     *
     * @param past 已经走过的节点数
     * @param now  当前出发的节点
     * @param len  已经走过的距离
     */
    public static void dfs(int past, int now, double len){
        if(len >= length){
            //剪枝
            return;
        }
        if(past == n){
            //已经走过了所有的点
            length = Math.min(length, len);
            return;
        }
        for(int i=1;i<=n;i++){
            if(accessStates[i]){
                //表示该节点已经被访问过
                continue;
            }
            accessStates[i] = true;
            dfs(past+1, i, len + getDistance(now, i));
            accessStates[i] = false;
        }
    }

    public static double getDistance(int from, int to){
        if(distances[from][to] != 0)
            return distances[from][to];
        return distances[to][from] = distances[from][to] = Math.sqrt((positions[from][0]-positions[to][0])*(positions[from][0]-positions[to][0])
            +(positions[from][1]-positions[to][1])*(positions[from][1]-positions[to][1]));
    }
    //记录两点之间的最短距离
    static double[][] dp = new double[n+1][n+1];
    public static void dfsWithMemory(int past, int now, double len){

    }
}
