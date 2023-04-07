package programmers.level2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class 타겟_넘버 {
    static int ans = 0;
    public static void main(String[] args) {
        Solution solution = new Solution();
        //System.out.print(solution.solution(new int[]{4, 1, 2, 1}, 4));
        //System.out.print(solution.solution(new int[]{1, 1, 1, 1, 1}, 3));
        System.out.print(solution.solution(new int[]{1, 1, 3}, 3));
    }

    static class Solution {
        public int solution(int[] numbers, int target) {
            int answer = 0;
            //return dfs(numbers, target, 0, 0);
            return bfs(numbers, target);
        }

        // 깊이 탐색 -> 한 녀석을 끝까지 판다
        static int dfs(int[] numbers, int target, int depth, int result) {
            /**
             * 1. 더하기 연산 탐색
             * 2. 빼기 연산 탐색
             * 3. 타겟을 찾으면 탐색 종료
             */
            int count = 0;
            // 끝까지 탐색을 했을때
            if(numbers.length == depth) {
                // 타겟값을 찾으면
                if(target == result) {
                    count++;
                }
            } else {
                // 더하기 연산 탐색
                count += dfs(numbers, target, depth + 1, result + numbers[depth]);
                // 빼기 연산 탐색
                count += dfs(numbers, target, depth + 1, result - numbers[depth]);
            }

            return count;
        }

        // 너비 우선 탐색
        static int bfs(int[] numbers, int target) {
            Queue<Node> queue = new ArrayDeque<>();
            // 초기 탐색 값 설정
            queue.add(new Node(0, numbers[0]));
            queue.add(new Node(0, numbers[0] * -1));

            int count = 0;
            while (!queue.isEmpty()) {
                Node poll = queue.poll();
                // 너비 탐색을 마치면
                if(poll.getNode() == numbers.length - 1) {
                    // 타겟값 이면
                    if (poll.getResult() == target) {
                        // 카운트 증가!
                        count++;
                    }
                }

                // 인덱스 증가
                int newNode = poll.getNode() + 1;
                if(newNode < numbers.length) {
                    // 더하기 연산
                    queue.add(new Node(newNode, poll.getResult() + numbers[newNode]));
                    // 빼기 연산
                    queue.add(new Node(newNode, poll.getResult() - numbers[newNode]));
                }
            }

            return count;
        }

        static class Node {
            private int node;
            private int result;

            public Node(int node, int result) {
                this.node = node;
                this.result = result;
            }

            public int getNode() {
                return node;
            }

            public int getResult() {
                return result;
            }
        }
    }
}
