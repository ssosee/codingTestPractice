package channelTalk;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Test3 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[]{5, 10, 2, 7, 9, 4}, new int[]{7, 1, 3, 8, 5}));
    }

    /**
     * 모든 블록을 넘어뜨리기 위해 직접 밀쳐야하는 최소의 수
     * 블록의 길이와 블록간의 거리를 고려해야함
     * 블록의 길이 >= 블록간의 거리 -> 밀친 방향의 블록 넘어뜨리기 가능
     * 블록의 길이 < 블록간의 거리 -> 밀친 방향의 블록 넘어뜨리기 불가
     */
    static class Solution {
        public int solution(int[] blocks, int[] distances) {
            int answer = 0;
            /**
             * 각각의 블록에서 왼쪽, 오른쪽으로 밀치는 경우 생각
             */
            return bfs(blocks, distances);
        }

        private int bfs(int[] blocks, int[] distances) {
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(blocks[0]);
            boolean[][] visited = new boolean[distances.length][2];
            visited[0][0] = true;

            int leftCount = 0;
            int rightCount = 0;

            if(!queue.isEmpty()) {
                Integer poll = queue.poll();

                for(int i = 0; i < distances.length; i++) {
                    if(poll >= distances[i] && !visited[i][0]) {
                        visited[i][0] = true;
                        queue.offer(blocks[i]);
                        leftCount++;
                    }
                    else if(poll >= distances[i] && !visited[i][1]) {
                        visited[i][1] = true;
                        queue.offer(blocks[i]);
                        rightCount++;
                    }
                }
            }

            return Math.min(leftCount, rightCount);
        }
    }
}
