package com.example.leetcode;
/**
 * @author dw_dingdan1
 * @date 2020/6/8
 */
public class Trie {

    TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if(word == null || word.length()==0){
            return;
        }
        TrieNode tmpRoot = root;
        char[] chars = word.toCharArray();
        for(char c : chars){
            TrieNode target;
            if(tmpRoot.nodes == null){
                tmpRoot.nodes = new TrieNode[26];
            }
            if((target = tmpRoot.nodes[c-'a']) == null){
                target = new TrieNode();
                tmpRoot.nodes[c-'a'] = target;
            }
            tmpRoot = target;
        }
        tmpRoot.end = true;
    }

    public void insert(String word, int id) {
        if(word == null || word.length()==0){
            return;
        }
        TrieNode tmpRoot = root;
        char[] chars = word.toCharArray();
        for(char c : chars){
            TrieNode target;
            if(tmpRoot.nodes == null){
                tmpRoot.nodes = new TrieNode[26];
            }
            if((target = tmpRoot.nodes[c-'a']) == null){
                target = new TrieNode();
                tmpRoot.nodes[c-'a'] = target;
            }
            tmpRoot = target;
        }
        tmpRoot.end = true;
        tmpRoot.id = id;
    }

    /** Returns if the word is in the trie. find from root node*/
    public boolean search(String word) {
        if(word==null || word.length()==0){
            return false;
        }
        TrieNode tmpRoot = root;
        char[] chars = word.toCharArray();
        for(char c : chars){
            if(tmpRoot.nodes == null){
                return false;
            }
            TrieNode target;
            if((target = tmpRoot.nodes[c-'a'])==null)
                return false;
            tmpRoot = target;
        }
        return tmpRoot.end;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. find from root node*/
    public boolean startsWith(String prefix) {
        return startsWith(root, prefix);
    }

    /** find from any node */
    public boolean startsWith(TrieNode node, String prefix) {
        if(prefix==null || prefix.length()==0){
            return false;
        }
        char[] chars = prefix.toCharArray();
        for(char c : chars){
            if(node.nodes == null){
                return false;
            }
            TrieNode target;
            if((target = node.nodes[c-'a'])==null)
                return false;
            node = target;
        }
        return true;
    }

    public boolean startsWith(TrieNode node, char c){
        if(node.nodes == null){
            return false;
        }
        return node.nodes[c-'a'] != null;
    }

    static class TrieNode{
        //if a node is end of a string
        /**
         * 记录敏感词的ID 敏感词过滤中有用到
         */
        int id = -1;
        boolean end;
        TrieNode[] nodes;
        //根据char获取子节点
        public TrieNode getTrieNode(char c){
            if(nodes == null)
                return null;
            return nodes[c-'a'];
        }
    }

    public static void main(String[] args){
        Trie trie = new Trie();
        System.out.println(trie.search("a"));
        System.out.println(trie.startsWith("a"));
        trie.insert("abc");
        trie.insert("bac");
        trie.insert("dfg");
        trie.insert("apple");
        trie.insert("app");
        System.out.println(trie.search("abc"));
        System.out.println(trie.search("ab"));
        System.out.println(trie.startsWith("ab"));
        System.out.println(trie.startsWith("a"));
        System.out.println(trie.startsWith("c"));
        System.out.println(trie.search("app"));
        System.out.println(trie.search("app"));
    }
}
