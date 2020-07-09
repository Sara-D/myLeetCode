package com.example.leetcode;
import java.util.*;

/**
 * @author dw_dingdan1
 * @date 2020/7/8
 * 给定一个较长字符串big和一个包含较短字符串的数组smalls，设计一个方法，根据smalls中的每一个较短字符串，对big进行搜索。输出smalls中的字符串在big里出现的所有位置positions，其中positions[i]为smalls[i]出现的所有位置。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/multi-search-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：
 * big = "mississippi"
 * smalls = ["is","ppi","hi","sis","i","ssippi"]
 * 输出： [[1,4],[8],[],[3],[1,4,7,10],[5]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/multi-search-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 因为没有记录每个敏感词的位置，所以使用Map进行存储          耗时情况    525 ms	  62.2 MB
 * 在trie树中记录记录每个敏感词的位置，直接用数组存储         耗时情况    39 ms	      59.8 MB
 */
public class MutiSearch {

    public static void main(String[] args){
        String[] smalls = {"is","ppi","hi","sis","i","ssippi"};
        int[][] value = multiSearch("mississippi", smalls);
        for(int i=0;i<value.length;i++){
            System.out.println(Arrays.toString(value[i]));
        }
    }

    /**
     * 类似敏感词操作（找出一个句子中的敏感词并用XXX代替）
     * @param big
     * @param smalls
     * @return
     */
    public static int[][] multiSearch(String big, String[] smalls) {
        if(big == null || smalls == null){
            return null;
        }
        //构造字典树
        Trie trie = new Trie();
        for(int i=0; i<smalls.length; i++){
            trie.insert(smalls[i], i);
        }
        List<Integer>[] lists = new List[smalls.length];
        for(int i=0; i<big.length(); i++){
            Trie.TrieNode cur = trie.root;
            for(int j=i;j<big.length();j++){
                char curChar = big.charAt(j);
                Trie.TrieNode next = cur.getTrieNode(curChar);
                if(next == null){
                    break;
                }
                if(next.end){
                    List<Integer> lists1 = lists[next.id];
                    if(lists1 == null){
                        lists1 = new ArrayList<>();
                        lists1.add(i);
                        lists[next.id] = lists1;
                    }else {
                        lists1.add(i);
                    }
                }
                cur = next;
            }
        }
        int[][] positions = new int[smalls.length][];
        for(int i=0;i<smalls.length;i++){
            List<Integer> list = lists[i];
            if(list == null){
                positions[i] = new int[]{};
            }else {
                positions[i] = new int[list.size()];
                for(int j = 0; j<list.size();j++){
                    positions[i][j] = list.get(j);
                }
            }
        }
        return positions;
    }
}
