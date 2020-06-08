package com.example.leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * @author dw_dingdan1
 * @date 2020/5/22
 */
public class JobSequenceProblem {

    static class Job implements Comparable<Job>{
        int id;
        int deadline;
        int profit;

        @Override
        public String toString() {
            return "id:" + id + " deadline:" + deadline + " profit:" + profit;
        }

        @Override
        public int compareTo(Job o) {
            return o.profit - this.profit;
        }
    }

    public static void main(String[] str){
        //randomly assign jobs
        Random random = new Random();
        Job[] jobs = new Job[10];
        int maxDeadline = 0;
        for(int i=1;i<=jobs.length;i++){
            Job job = new Job();
            job.id = i;
            job.deadline = 1 + random.nextInt(5);
            job.profit = 1000 - random.nextInt(500);
            jobs[i-1] = job;
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }
        Arrays.sort(jobs);
        for(Job job : jobs){
            System.out.println(job);
        }
        //int maxProfit = jobs[0].profit;
        int[] parent = new int[maxDeadline + 1];
        for(int i=0;i<parent.length;i++){
            parent[i] = i;
        }
        //存储的是当前任务的id
        int[] result = new int[maxDeadline + 1];
        for(int i=0;i<jobs.length;i++){
            Job job = jobs[i];
            int parentPoint = find(parent, job.deadline);
            if(parentPoint==0){
                continue;
            }
            result[parentPoint] = job.profit;
            parent[parentPoint] = find(parent, parentPoint-1);
        }
        long max = 0 ;
        System.out.println();
        for(int j=1;j<result.length;j++){
            if(result[j] >0){
                max += result[j];
            }
        }
        System.out.println();
        System.out.println("最大利润:" + max);
        /*int[] array = new int[11];
        array[10] = 1;
        array[1] = 5;
        array[5] = 4;
        array[4] = 3;
        array[3] = 3;
        System.out.println(find(array, 5));
        System.out.println(array[10] + " " + array[1] + " " + array[5] + " " + array[4] + " " + array[3]);*/
    }

    //找到X的根节点，并将所有途中的父节点都更新为最后的节点
    public static int find(int[] parent, int x){
        //时间复杂度
        if(x == parent[x]){
            return x;
        }
        return parent[x] = find(parent, parent[x]);
    }

}
