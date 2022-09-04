package inflearn.stackqueue;

import java.util.*;

public class test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(new Solution().getAnswer(s));
    }

    static class Solution {

        /**
         * 강의 풀이
         */
        public String getAnswer2(String s) {
            String answer = "";
            Stack<Character> stack = new Stack<>();
            for(char c : s.toCharArray()) {
                if(c == ')') {
                    while(stack.pop() != '(');
                } else {
                    stack.push(c);
                }
            }
            for(int i = 0; i < stack.size(); i++) answer += stack.get(i);
            return answer;
        }

        public String getAnswer(String s) {
            String answer = "";

            Stack<Character> stack = new Stack<>();
            for(char x : s.toCharArray()) {
                if(x == '(') {
                    stack.push(x);
                }
                else if(x == ')'){
                    stack.pop();
                }
                else if(stack.isEmpty()) {
                    answer += String.valueOf(x);
                }
            }

            return answer;
        }
    }
}
