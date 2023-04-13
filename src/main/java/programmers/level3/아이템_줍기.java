package programmers.level3;

import java.util.*;

public class 아이템_줍기 {
    static int maxX;
    static int maxY;
    public static void main(String[] args) {
        Solution solution = new Solution();
        int solution1 = solution.solution(new int[][]{
                {1, 1, 7, 4},
                {3, 2, 5, 5},
                {4, 3, 6, 9},
                {2, 6, 8, 8}}, 1, 3, 7, 8);

        //int solution1 = solution.solution(new int[][]{{1, 1, 5, 7}}, 1, 1, 4, 7);

        System.out.print(solution1);
    }

    /**
     * 다각형의 바깥쪽 테두리가 이동 경로
     * 1. 주어진 사각형의 테두리의 경로를 구한다.
     * 2. BFS 과정에서 사각형끼리 겹치는 부분을 제외하고 탐색을 한다.
     */
    static class Solution {
        public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
            int answer = 0;

            List<Path>[][] path = new ArrayList[101][101];
            for(int i = 1; i < 101; i++) {
                for(int j = 1; j < 101; j++) {
                    path[i][j] = new ArrayList<>();
                }
            }

            path = createPath(rectangle, path);
            int bfs = bfs(rectangle, path, characterX, characterY, itemX*2, itemY*2);

            return bfs;
        }

        public List<Path>[][] createPath(int[][] rectangle, List<Path>[][] path) {
            /**
             * 주어진 사각형을 2배로 확장하여 경로를 만들어야 한다.
             * 그 이유는 ]같은 모양이면 ㅁ으로 인식하여 위로 이동할 수 있기 때문이다....!
             *  x---x                o---x
             *      |     ---x-->    ⬆️  |
             *  o->-o                o---x
             * 오른쪽으로 가야함        위로 감
             *
             * [x1, y1, x2, y2]에서
             * x1과 같으면 y2까지 경로 생성가능
             * x1보다 크면 y1, y2만 경로 생성 가능
             */

            for(int i = 0; i < rectangle.length; i++) {
                int x1 = rectangle[i][0];
                int y1 = rectangle[i][1];
                int x2 = rectangle[i][2] * 2;
                int y2 = rectangle[i][3] * 2;

                for(int x = x1; x <= x2; x++) {
                    for(int y = y1; y <= y2; y++) {

                        // 최대 x, y를 찾는다.
                        maxX = Math.max(maxX, x);
                        maxY = Math.max(maxY, y);

                        // x가 x1또는 x2와 같으면
                        if (x == x1 || x == x2) {
                            // y까지 경로 생성 가능
                            path[x][y].add(new Path(x, y, 1));
                        }
                        // x가 x1보다 크면
                        else if (x > x1) {
                            // y와 y1이 같거나 y2와 같으면
                            if(y == y1 || y == y2) {
                                // y1, y2만 경로 생성 가능
                                path[x][y].add(new Path(x, y, 1));
                            }
                        }
                    }
                }
            }

            return path;
        }

        public int bfs(int[][] rectangle, List<Path>[][] path, int startX, int startY, int endX, int endY) {
            Queue<Path> queue = new ArrayDeque<>();
            // bfs 초기화
            queue.offer(new Path(startX, startY));
            boolean[][] visited = new boolean[maxX+1][maxY+1];
            visited[startX][startY] = true;

            int[][] result = new int[maxX+1][maxY+1];

            while (!queue.isEmpty()) {
                // 현재 위치를 꺼냄
                Path poll = queue.poll();
                int x = poll.getX();
                int y = poll.getY();

                // 상, 하, 좌, 우
                int[] ud = {1, -1, 0, 0};
                int[] lr = {0, 0, 1, -1};

                for(int i = 0; i < 4; i++) {
                    int newX = x + ud[i];
                    int newY = y + lr[i];

                    // 범위 체크
                    if(!checkRange(rectangle, newX, newY)) continue;

                    // 사각형 전체 탐색
                    for(Path p : path[newX][newY]) {
                        // 방문 이력이 없으면
                        if (!visited[newX][newY]) {
                            // 방문
                            visited[newX][newY] = true;
                            result[newX][newY] = result[x][y] + 1;
                            System.out.println("newX=" + newX + ", newY=" + newY + ", r=" + result[newX][newY]);

                            // 도착지점에 도착하면 탈출
                            // BFS이기 때문에 그것이 최단 경로
                            if (newX == endX && newY == endY) {
                                return result[newX][newY];
                            }

                            queue.add(new Path(p.getX(), p.getY(), result[newX][newY]));
                        }
                    }
                }
            }
            System.out.println(1);
            return 0;
        }

        public boolean checkRange(int[][] rectangle, int x, int y) {

            if(x <= 0 || y <= 0 || x > maxX || y > maxY) return false;

            for(int i = 0; i < rectangle.length; i++) {
                int x1 = rectangle[i][0];
                int y1 = rectangle[i][1];
                int x2 = rectangle[i][2];
                int y2 = rectangle[i][3];

                // x1 < x < x2 && y1 < y < y2
                if(x < x2*2 && x > x1 && y > y1 && y < y2*2) {
                    return false;
                }
            }
            return true;
        }

        class Path {
            private int x;
            private int y;
            private int weight;

            public Path(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public Path(int x, int y, int weight) {
                this.x = x;
                this.y = y;
                this.weight = weight;
            }

            public int getX() {
                return x;
            }

            public int getY() {
                return y;
            }

            public int getWeight() {
                return weight;
            }
        }
    }
}

/*
newX=3, newY=6, r=5
newX=4, newY=1, r=5
newX=4, newY=6, r=6
newX=2, newY=6, r=6
newX=5, newY=1, r=6
newX=2, newY=7, r=7
newX=6, newY=1, r=7
newX=2, newY=8, r=8
newX=7, newY=1, r=8
newX=3, newY=8, r=9
newX=7, newY=2, r=9
newX=4, newY=8, r=10
newX=7, newY=3, r=10
newX=4, newY=9, r=11
newX=7, newY=4, r=11
newX=5, newY=9, r=12
newX=6, newY=4, r=12
newX=6, newY=9, r=13
newX=6, newY=5, r=13
newX=6, newY=8, r=14
newX=6, newY=6, r=14
newX=7, newY=8, r=15
 */
