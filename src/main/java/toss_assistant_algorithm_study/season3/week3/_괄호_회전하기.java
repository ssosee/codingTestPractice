package toss_assistant_algorithm_study.season3.week3;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class _괄호_회전하기 {
    public static void main(String[] args) {

    }

    static class Solution {
        /**
         * 아이디어
         * - 큐를 이용해서 문자열 회전
         * - 스택을 이용해서 올바른 괄호인지 확인
         */
        public int solution(String s) {
            Queue<Character> queue = new ArrayDeque<>();
            for(int i = 0; i < s.length(); i++) {
                queue.offer(s.charAt(i));
            }

            int answer = 0;
            for(int i = 0; i < s.length(); i++) {
                String str = rotation(queue, i);
                answer += validationCount(str);
            }

            return answer;
        }

        private int validationCount(String str) {
            Stack<Character> stack = new Stack<>();
            for(int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                // 여는 괄호
                if(ch == '(' || ch == '{' || ch == '[') {
                    stack.push(ch);
                }
                // 닫는 괄호
                else {
                    // 스택이 비어있으면
                    if(!stack.isEmpty()) return 0;

                    Character pop = stack.pop();
                    if (ch == ')' && pop != '(' || ch == '}' && pop != '{' ||ch == ']' && pop != '[') {
                        return 0;
                    }
                }
            }

            if(!stack.isEmpty()) return 0;

            return 1;
        }

        private String rotation(Queue<Character> queue, int count) {
            // 깊은복사
            Queue<Character> tempQueue = new ArrayDeque<>(queue);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < count; i++) {
                Character poll = tempQueue.poll();
                tempQueue.offer(poll);
            }

            while (!tempQueue.isEmpty()) {
                Character poll = tempQueue.poll();
                sb.append(poll);
            }

            return sb.toString();
        }
    }
}
