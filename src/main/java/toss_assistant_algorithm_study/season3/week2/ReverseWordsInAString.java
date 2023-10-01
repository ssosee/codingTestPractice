package toss_assistant_algorithm_study.season3.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReverseWordsInAString {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverseWords("a good   example"));
    }

    static class Solution {
        public String reverseWords(String s) {
            // 맨앞, 맨뒤 공백 제거
            String trim = s.trim();
            String[] split = trim.split(" ");
            StringBuilder sb = new StringBuilder();
            for (int i = split.length - 1; i >= 0; i--) {
                if(!split[i].isBlank()) sb.append(split[i]).append(" ");
            }

            return sb.toString();
        }
    }
}
