package com.example.leetcode;

/**
 * @author dw_dingdan1
 * @date 2020/7/9
 *
 * 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。像句子"I reset the computer. It still didn’t boot!"已经变成了"iresetthecomputeritstilldidntboot"。在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
 *
 * 注意：本题相对原题稍作改动，只需返回未识别的字符数
 *
 * 示例：
 *
 * 输入：
 * dictionary = ["looked","just","like","her","brother"]
 * sentence = "jesslookedjustliketimherbrother"
 * 输出： 7
 * 解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/re-space-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Respace {

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args){
        String[] dictionary = {"wccm","wiw","uwwiwcmiiiwmmwicuwu","mw"};
        String sentence = "iwiwwwmuiccwwwwwmumwwwmcciwwuiwcicwwuwicuiwciwmiwicwuwwmuimccwucuuim";
        System.out.println(respace(dictionary, sentence));
    }

    public static int respace(String[] dictionary, String sentence) {
        if(dictionary == null || sentence == null){
            return 0;
        }
        //构建字典树
        Trie trie = new Trie();
        for(String s : dictionary){
            trie.insert(s, true);
        }
        char[] chars = sentence.toCharArray();
        int[] dp = new int[chars.length + 1];
        for(int i=1;i<=chars.length;i++){
            dp[i] = dp[i-1] + 1;
            Trie.TrieNode cur = trie.root;
            for(int j=i; j>=1;j--){
                Trie.TrieNode next = cur.getTrieNode(chars[j-1]);
                if(next == null){
                    break;
                }
                if(next.end){
                    dp[i] = Math.min(dp[i], dp[j-1]);
                }
                cur = next;
            }
        }
        return dp[chars.length];
    }

    /**
     *
     * @param from inclusive
     * @param to exclusive
     * @param chars
     * @param trie
     * @return
     */
    /*public static void getUnrecognized(int from, int to, char[] chars, Trie trie, int now){
        if(now >= min){
            //提前终止
            return;
        }
        if(from >= to){
            min = now;
            return;
        }
        Trie.TrieNode cur = trie.root;
        for(int j=from;j<to;j++){
            Trie.TrieNode next = cur.getTrieNode(chars[j]);
            if(next == null){
                getUnrecognized(j+1, to, chars, trie, now + j - from + 1);
                break;
            }
            if(next.end){
            //分两种情况 1、找到一个单词 加入空格 继续搜索另一个单词 2、找到一个单词继续往下寻找看是否有更长的单词
                getUnrecognized(j+1, to, chars, trie, now);
            }
            cur = next;
        }
        min = Math.min(to-from, min);
    }*/
}
