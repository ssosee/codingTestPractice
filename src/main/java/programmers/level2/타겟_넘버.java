package programmers.level2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class 타겟_넘버 {
    static int ans = 0;
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.print(solution.solution(new int[]{4, 1, 2, 1}, 4));
    }

    static class Solution {
        public int solution(int[] numbers, int target) {
            int answer = 0;
            //return dfs(numbers, target, 0, 0);
            return bfs(numbers, target);
        }

        private static int dfs(int[] numbers, int target, int depth, int result) {
            int count = 0;
            // 깊이 탐색을 마쳤을 때
            if(numbers.length == depth) {
                // 결과가 타겟값과 일치하면
                if(result == target) {
                    count++;
                }
            } else {
                // 깊이를 1 증가, result에 number의 depth에 해당하는 값을 더함
                count += dfs(numbers, target, depth + 1, result + numbers[depth]);
                // 깊이를 1 증가, result에 number의 depth에 해당하는 값을 뺌
                count += dfs(numbers, target, depth + 1, result - numbers[depth]);
            }

            return count;
        }

        private static int bfs(int[] numbers, int target) {
            Queue<Node> q = new ArrayDeque<>();
            q.add(new Node(0, numbers[0]));
            q.add(new Node(0, numbers[0] * -1));
            int count = 0;

            while (!q.isEmpty()) {
                Node poll = q.poll();
                // 너비 탐색을 마치면
                if(poll.getPosition() == numbers.length - 1) {
                    // 타겟값을 찾으면
                    if(poll.getSum() == target) {
                        count++;
                    }
                    continue;
                }

                // 너비 체크
                if(poll.getPosition() >= numbers.length - 1) {
                    continue;
                }

                int newPosition = poll.getPosition() + 1;

                q.add(new Node(newPosition,  poll.getSum() + numbers[newPosition]));
                q.add(new Node(newPosition,  poll.getSum() - numbers[newPosition]));
            }

            return count;
        }

        private static class Node {
            private int position;
            private int sum;

            public Node(int position, int sum) {
                this.position = position;
                this.sum = sum;
            }

            public int getPosition() {
                return position;
            }

            public int getSum() {
                return sum;
            }
        }
    }
}
