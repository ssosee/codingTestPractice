package programmers.level2;

import java.util.ArrayDeque;
import java.util.Queue;

public class 게임_맵_최단거리_BFS {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[][]{
                {1,0,1,1,1},
                {1,0,1,0,1},
                {1,0,1,1,1},
                {1,1,1,0,1},
                {0,0,0,0,1}
        }));
    }
    static class Solution {
        public int solution(int[][] maps) {
            int width = maps.length;
            int height = maps[0].length;

            int[][] visited = new int[width][height];


            return bfs(maps, visited, 0, 0, width, height);
        }

        private int bfs(int[][] maps, int[][] visited, int x, int y, int width, int height) {
            Queue<Road> queue = new ArrayDeque<>();
            visited[x][y] = 1;
            queue.offer(new Road(x, y));

            while (!queue.isEmpty()) {
                Road poll = queue.poll();
                int[] ud = {1, -1, 0, 0};
                int[] lr = {0, 0, 1, -1};

                for (int i = 0; i < 4; i++) {
                    int newX = poll.getX() + ud[i];
                    int newY = poll.getY() + lr[i];

                    // 범위 체크
                    if (newX < 0 || newY < 0 || newX >= width || newY >= height) {
                        continue;
                    }


                    // 방문 이력이 없고, 벽이 아니면
                    if(visited[newX][newY] == 0 && maps[newX][newY] == 1) {
                        // 거리 계산
                        visited[newX][newY] = visited[poll.getX()][poll.getY()] + 1;
                        // 목표에 도달하면
                        if(newX == width - 1 && newY == height - 1) {
                            return visited[newX][newY];
                        }

                        queue.offer(new Road(newX, newY));
                    }
                }
            }
            return -1;
        }
    }
    static class Road {
        private int x;
        private int y;

        public Road(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

    }
}
