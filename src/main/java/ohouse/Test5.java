package ohouse;

import java.util.ArrayDeque;
import java.util.Queue;

public class Test5 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new String[][]{
                {"O", "A", "C", "D", "E", "O"},
                {"H", "O", "T", "C", "A", "P"},
                {"P", "U", "S", "E", "T", "Z"}
        }, "OHOUSE"));
    }

    static class Solution {
        static int[] moveX = {1, -1, 0, 0};
        static int[] moveY = {0, 0, 1, -1};
        static boolean[][] visited;
        public boolean solution(String[][] board, String word) {
            boolean result = false;
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[0].length; j++) {
                    result = bfs(i, j, board, word);
                    if(result) break;
                }
                if(result) break;
            }

            return result;
        }

        private boolean bfs(int startX, int startY, String[][] board, String target) {
            Queue<Words> queue = new ArrayDeque<>();
            queue.offer(new Words(board[startX][startY], startX, startY));
            visited = new boolean[board.length][board[0].length];
            visited[startX][startY] = true;

            while (!queue.isEmpty()) {
                Words poll = queue.poll();
                String words = poll.words;
                int x = poll.x;
                int y = poll.y;

                if(words.equals(target)) return true;

                for(int i = 0; i < 4; i++) {
                    int newX = x + moveX[i];
                    int newY = y + moveY[i];

                    if(newX < 0 || newY < 0 || newX >= board.length || newY >= board[0].length) continue;

                    if(!visited[newX][newY] && target.contains(words)) {
                        String newWord = board[newX][newY];
                        queue.offer(new Words(words+newWord, newX, newY));
                    }
                }
            }

            return false;
        }

        class Words {
            private String words;
            private int x;
            private int y;

            public Words(String words, int x, int y) {
                this.words = words;
                this.x = x;
                this.y = y;
            }
        }
    }
}
