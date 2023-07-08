package toss;

import java.util.*;

public class 멋쟁이팬디지털 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //int r = solution.solution("313253123", 3);
        int r = solution.solution("131211123", 3);
        System.out.println(r);
    }

    static class Solution {
        public int solution(String s, int N) {
            int answer = 0;

            int max = Integer.MIN_VALUE;
            StringBuilder sb = new StringBuilder();
            Set<Character> sets = new HashSet<>();
            for(int i = 0; i < s.length() - N + 1; i++) {
                String substring = s.substring(i, N+i);
                for(int j = 0; j < substring.length(); j++) {
                    char c = substring.charAt(j);
                    if(c - '0' <= N) {
                        sb.append(c);
                        sets.add(c);
                    }
                }
                if(sets.size() == N) {
                    max = Math.max(Integer.parseInt(sb.toString()), max);
                }
                sb.setLength(0);
                sets.clear();
            }

            if(max == Integer.MIN_VALUE) return -1;

            return max;
        }
    }
}
