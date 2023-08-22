package toss_assistant_algorithm_study.season1.week1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 큰_수_만들기 {
    public static void main(String[] args) {
        Solution s = new Solution();
        String solution = s.solution("1231234", 3);
        System.out.println(solution);
    }

    static class Solution {
        public String solution(String number, int k) {
            String answer = "";

            /** 0  1  2  3  4  5  6
             * [1, 2, 3, 1, 2, 3, 4]
             * targetLen = 4, k = 3
             * 1. 1231|234  -> 3
             * 2. 123 12|34 -> 2
             * 3. 12312 3|4 -> 3
             * 4. 123123 4  -> 4
             */

            int numLen = number.length();
            int targetLen = numLen - k;
            char[] chs = number.toCharArray();
            StringBuilder sb = new StringBuilder();

            int start = 0;
            int end = k;
            while (sb.length() != targetLen) {
                int max = Integer.MIN_VALUE;
                for(int i = start; i <= end; i++) {
                    if(max < chs[i] - '0') {
                        max = chs[i] - '0';
                        start = i + 1;
                    }
                }
                end++;
                sb.append(max);
            }

            return sb.toString();
        }
    }
}
