package toss_assistant_algorithm_study.season5.week2;

import java.util.ArrayDeque;
import java.util.Queue;

public class NumberOfIslands {
    public static void main(String[] args) {

    }

    static class Solution {

        static int[] moveX = {1,-1,0,0};
        static int[] moveY = {0,0,1,-1};
        static boolean[][] visited;
        static int n;
        static int m;
        public int numIslands(char[][] grid) {

            n = grid.length;
            m = grid[0].length;

            visited = new boolean[n][m];
            int count = 0;

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(grid[i][j] == '1' && !visited[i][j]) {
                        bfs(i, j, grid);
                        count++;
                    }
                }
            }

            return count;
        }

        private void bfs(int x, int y, char[][] grid) {
            Queue<Node> nodes = new ArrayDeque<>();
            visited[x][y] = true;
            nodes.add(new Node(x, y));

            while (!nodes.isEmpty()) {
                Node poll = nodes.poll();
                for(int i = 0; i < 4; i++) {
                    int newX = moveX[i] + poll.x;
                    int newY = moveY[i] + poll.y;

                    if(newX >= n || newY >= m || newX < 0 || newY < 0) continue;

                    if(!visited[newX][newY] && grid[newX][newY] == '1') {
                        visited[newX][newY] = true;
                        nodes.add(new Node(newX, newY));
                    }
                }
            }
        }

        static class Node {
            private int x;
            private int y;

            public Node(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }
}
