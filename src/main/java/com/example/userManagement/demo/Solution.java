package com.example.userManagement.demo;

import java.util.*;

class Solution {
    Map<String,Boolean> map = new HashMap<>();

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "leetcodehashave";
        List<String>  wordDict = Arrays.asList("leet","code","has","have");
        sol.wordBreak(s,wordDict);
    }

    private  boolean wordBreak(String s, List<String> wordDict) {

        if(s.isEmpty()){
            return true;
        }

        if(map.containsKey(s)){
            return map.get(s);
        }

        for(String word : wordDict){
            if(s.startsWith(word)){
                if(wordBreak(s.substring(word.length()),wordDict)){
                    map.put(s,true);
                    return true;
                }
            }
        }
        map.put(s,false);
        return false;
    }
}