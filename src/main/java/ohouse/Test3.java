package ohouse;

import java.util.Stack;

public class Test3 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("([]){}"));
    }

    static class Solution {
        public boolean solution(String s) {
            Stack<Character> stack = new Stack<>();

            for (char c : s.toCharArray()) {
                if (c == '(' || c == '{' || c == '[') {
                    stack.push(c); // 여는 괄호일 경우 스택에 push
                } else {
                    if (stack.isEmpty()) {
                        return false; // 닫는 괄호인데 스택이 비어있으면 유효하지 않음
                    }
                    char top = stack.pop();
                    if (c == ')' && top != '(' || c == '}' && top != '{' || c == ']' && top != '[') {
                        return false; // 닫는 괄호와 스택의 맨 위 괄호가 짝이 맞지 않으면 유효하지 않음
                    }
                }
            }

            return stack.isEmpty();
        }
    }
}
