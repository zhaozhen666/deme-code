package com.zhao.leetcode;

import java.lang.annotation.Target;

public class Trie {
    public class TrieNode{
        public char data;
        public TrieNode children[] =new TrieNode[26];
        public boolean isEndingChar =false;
        public TrieNode(char data){
            this.data =data;
        }
    }
    private TrieNode root =new TrieNode('/');
    public void  insert(char [] text){
        TrieNode p = root;
        for (int i=0;i<text.length;++i){
            int index = text[i]-'a';
            if (p.children[index]==null){
                TrieNode newNode =new TrieNode(text[i]);
                p.children[i] = newNode;

            }
            p=p.children[index];

        }
        p.isEndingChar=true;
    }

    public boolean find(char [] text){
        TrieNode p =root;
        for (int i=0;i<text.length;++i){
            int index = text[i]-'a';
            if (p.children[index]==null){
                return false;
            }
            p=p.children[index];
        }
        if (p.isEndingChar==false) return false;
        return true;
    }
}
