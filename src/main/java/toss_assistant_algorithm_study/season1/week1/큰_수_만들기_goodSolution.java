package toss_assistant_algorithm_study.season1.week1;

import java.util.Stack;

public class 큰_수_만들기_goodSolution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String solution = s.solution("1231234", 3);
        System.out.println(solution);
    }

    static class Solution {
        public String solution(String number, int k) {
            char[] result = new char[number.length() - k];
            Stack<Character> stack = new Stack<>();

            for (int i=0; i<number.length(); i++) {
                char c = number.charAt(i);
                while (!stack.isEmpty() && stack.peek() < c && k-- > 0) {
                    stack.pop();
                }
                stack.push(c);
            }
            for (int i=0; i<result.length; i++) {
                result[i] = stack.get(i);
            }
            return new String(result);
        }
    }
}
