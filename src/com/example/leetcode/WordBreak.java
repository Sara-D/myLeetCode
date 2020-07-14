package com.example.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author dw_dingdan1
 * @date 2020/7/13
 *
 * 题号 139
 *
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 *
 *示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 *
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class WordBreak {

    public static void main(String[] args){
        String s = "catsandog";
        String[] wordDict = {"cats", "dog", "sand", "and", "cat"};
        System.out.println(new WordBreak().wordBreak(s, Arrays.asList(wordDict)));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        //return trieWordBreak(s, wordDict);
        return hashWordBreak(s, wordDict);
    }

    private boolean trieWordBreak(String s, List<String> wordDict){
        //构建字典树
        Trie trie = new Trie();
        for(String word : wordDict){
            trie.insert(word, true);
        }
        char[] chars = s.toCharArray();
        boolean[] dp = new boolean[chars.length];
        for(int i=0;i<chars.length;i++){
            Trie.TrieNode cur = trie.root;
            for(int j=i;j>=0;j--){
                Trie.TrieNode next = cur.getTrieNode(chars[j]);
                if(next == null){
                    break;
                }
                if(next.end){
                    if(j==0) {
                        dp[i] = true;
                        break;
                    } else if(dp[j-1]){
                        dp[i] = true;
                        break;
                    }
                }
                cur = next;
            }
        }
        return dp[chars.length-1];
    }

    private boolean hashWordBreak(String s, List<String> wordDict){
        Set<String> words = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for(int i=1;i<=s.length();i++){
            for(int j=0;j<i;j++){
                if(dp[j] && words.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
