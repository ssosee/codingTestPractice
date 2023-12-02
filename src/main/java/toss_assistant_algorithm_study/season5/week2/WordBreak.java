package toss_assistant_algorithm_study.season5.week2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class WordBreak {

    /**
     * 다이나믹 프로그래밍(기억하며 풀기)?
     * 완전탐색, DFS, BFS와 같이 수 많은 경우를 모두 따지기에는 너무 많은 경우수가 존재하여,
     * 속도가 느려지는 문제를 개선하고자(수행시간을 단축하고자) 만들어진 알고리즘 이다.
     *
     * 완전탐색으로 해결할 수 있는 것은 대충 500만개의 경우의 수
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        //System.out.println(solution.wordBreak("catsandog", List.of("cats","dog","sand","and","cat")));
        //System.out.println(solution.wordBreak("applepenapple", List.of("apple","pen")));
        System.out.println(solution.wordBreak("cars", List.of("car","ca", "rs")));
    }

    static class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            int n = s.length();
            boolean[] dy = new boolean[n + 1];
            dy[n] = true;

            for(int i = n - 1; i >= 0; i--) {
                for(String word : wordDict) {
                    String substring = s.substring(i, i + word.length());
                    if(i + word.length() <= n && word.equals(substring)) {
                        dy[i] = dy[i + word.length()];
                    }

                    if(dy[i]) break;
                }
            }

            return dy[0];
        }
    }
}
