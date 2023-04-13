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
     * 3. 경로를 만들때 주의할 점
     *  주어진 사각형을 2배로 확장하여 경로를 만들어야 한다.
     *  그 이유는 ]같은 모양이면 ㅁ으로 인식하여 위로 이동할 수 있기 때문이다....!
     *  x---x                o---x
     *      |     ---x-->    ⬆️  |
     *  o->-o                o---x
     *  오른쪽으로 가야함        위로 감
     */
    static class Solution {
        public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

            // 인접 리스트 초기화
            List<Path>[][] path = new ArrayList[101][101];
            for(int i = 1; i < 101; i++) {
                for(int j = 1; j < 101; j++) {
                    path[i][j] = new ArrayList<>();
                }
            }

            // 경로 생성
            path = createPath(rectangle, path);

            // 최단 거리 구하기
            int bfs = bfs(rectangle, path, characterX*2, characterY*2, itemX*2, itemY*2);

            return bfs;
        }

        public List<Path>[][] createPath(int[][] rectangle, List<Path>[][] path) {
            /**
             * [x1, y1, x2, y2]에서
             * x1과 같으면 y2까지 경로 생성가능
             * x1보다 크면 y1, y2만 경로 생성 가능
             */
            for(int i = 0; i < rectangle.length; i++) {
                // 경로를 2배로 늘리기
                int x1 = rectangle[i][0] * 2;
                int y1 = rectangle[i][1] * 2;
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
                            path[x][y].add(new Path(x, y));
                        }
                        // x가 x1보다 크면
                        else if (x > x1) {
                            // y와 y1이 같거나 y2와 같으면
                            if(y == y1 || y == y2) {
                                // y1, y2만 경로 생성 가능
                                path[x][y].add(new Path(x, y));
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

            // 최단 거리 저장 배열
            int[][] result = new int[maxX+1][maxY+1];

            // 탐색 시작
            while (!queue.isEmpty()) {
                // 현재 위치를 꺼냄
                Path poll = queue.poll();
                int x = poll.getX();
                int y = poll.getY();

                // 상, 하, 좌, 우
                int[] ud = {1, -1, 0, 0};
                int[] lr = {0, 0, 1, -1};

                for(int i = 0; i < 4; i++) {
                    // 이동 위치
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
                            // 최단 거리 계산
                            result[newX][newY] = result[x][y] + 1;

                            // 도착지점에 도착하면 탈출
                            // BFS이기 때문에 그것이 최단 경로
                            if (newX == endX && newY == endY) {
                                // 경로를 2배로 늘렸기 때문에 2로 나눈다.
                                return result[newX][newY] / 2;
                            }

                            queue.add(new Path(p.getX(), p.getY()));
                        }
                    }
                }
            }
            return 0;
        }

        public boolean checkRange(int[][] rectangle, int x, int y) {

            // x, y 가 0보다 작거나 같고, 최대 x,y보다 크면
            if(x <= 0 || y <= 0 || x > maxX || y > maxY) return false;

            // 주어진 모든 사각형으로 범위 체크
            for(int i = 0; i < rectangle.length; i++) {
                int x1 = rectangle[i][0] * 2;
                int y1 = rectangle[i][1] * 2;
                int x2 = rectangle[i][2] * 2;
                int y2 = rectangle[i][3] * 2;

                // x1 < x < x2 && y1 < y < y2
                // x, y가 사각형의 내부이면
                if(x < x2 && x > x1 && y > y1 && y < y2) {
                    return false;
                }
            }
            return true;
        }

        class Path {
            private int x;
            private int y;

            public Path(int x, int y) {
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
}
