package toss_assistant_algorithm_study.seaaon4.week3;

import java.util.ArrayDeque;
import java.util.Queue;

public class SurroundedRegions {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solve(new char[][]{
                {'X','X','X','X'},
                {'X','O','O','X'},
                {'X','X','O','X'},
                {'X','O','X','X'}
        });
    }

    static class Solution {
        static final char O = 'O';
        static final char X = 'X';
        static int[] moveX = {1,-1,0,0};
        static int[] moveY = {0,0,1,-1};
        static int n;
        static int m;

        public void solve(char[][] board) {
            n = board.length;
            m = board[0].length;

            if(!isBoardsAllO(board)) return;

            Node node = createNode(board);
            int x = node.x;
            int y = node.y;

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(board[i][j] == O && (i != x || j != y)) {
                        board[i][j] = X;
                    }
                }
            }

            System.out.println(1);
        }

        private boolean isBoardsAllO(char[][] board) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(board[i][j] == X) {
                        return false;
                    }
                }
            }

            return true;
        }

        private Node createNode(char[][] board) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(board[i][j] == O) {
                        int bfs = bfs(i, j, board);
                        if(bfs == 4) {
                            return new Node(i, j);
                        }
                    }
                }
            }

            return new Node(0, 0);
        }

        private int bfs(int startX, int startY, char[][] board) {
            Queue<Node> queue = new ArrayDeque<>();
            queue.add(new Node(startX, startY));

            boolean[][] visited = new boolean[n][m];
            visited[startX][startY] = true;

            int count = 0;
            while (!queue.isEmpty()) {
                Node poll = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int newX = moveX[i] + poll.x;
                    int newY = moveY[i] + poll.y;

                    if(newX < 0 || newY < 0 || newX >= n || newY >= m) {
                        count++;
                        continue;
                    }

                    if(!visited[newX][newX] && board[newX][newY] == X) {
                        visited[newX][newY] = true;
                        count++;
                    }
                }
            }

            return count;
        }

        class Node {
            private int x;
            private int y;

            public Node(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }
}
