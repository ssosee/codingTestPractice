package toss_assistant_algorithm_study.season3.week3;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class 괄호_회전하기 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //System.out.println(solution.solution("[](){}"));
        System.out.println(solution.solution("()("));
    }

    static class Solution {
        /**
         * 아이디어
         * - 큐를 이용하여 문자열을 회전시킨다.
         * - 스택을 이용하여 문자열이 유효한지 아닌지 확인
         *
         * 자료구조
         * - 큐, 스택
         *
         * 시간복잡도
         * - O(N^2)
         */
        public int solution(String s) {
            int answer = 0;

            Queue<Character> queue = new ArrayDeque<>();
            for(int i = 0; i < s.length(); i++) {
                queue.add(s.charAt(i));
            }

            for(int i = 0; i < s.length(); i++) {
                String str = rotation(queue, i);
                answer += getValidationCount(str);
            }

            return answer;
        }

        private String rotation(Queue<Character> queue, int count) {
            StringBuilder sb = new StringBuilder();
            Queue<Character> tempQueue = new ArrayDeque<>(queue);

            // count 만큼 문자열을 회전시킨다.
            for(int i = 0; i < count; i++) {
                Character poll = tempQueue.poll();
                tempQueue.offer(poll);
            }

            while (!tempQueue.isEmpty()) {
                sb.append(tempQueue.poll());
            }

            return sb.toString();
        }

        private int getValidationCount(String str) {
            Stack<Character> stack = new Stack<>();

            for(int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                // 여는 괄호이면
                if(c == '{' || c == '[' || c == '(') {
                    stack.push(c);
                }
                // 닫는 괄호이면
                else {
                    // 닫는 괄호인데 스택이 비어있으면
                    if(stack.isEmpty()) {
                        return 0;
                    }

                    Character pop = stack.pop();
                    // 현재 닫는 괄호인데 pop이 여는 괄호가 아닐 경우
                    if(c == ')' && pop != '(' || c == ']' && pop != '[' || c == '}' && pop != '{') {
                        return 0;
                    }
                }
            }

            if(!stack.isEmpty()) return 0;

            return 1;
        }
    }
}
