package programmers.level2;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class 게임_맵_최단거리_다익스트라 {
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

            // 방문 이력 저장 배열
            boolean[][] visited = new boolean[width][height];

            // 최단 거리 저장 배열 초기화
            int[][] result = new int[width][height];
            for(int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    result[i][j] = Integer.MAX_VALUE;
                }
            }

            // 인접 리스트 초기화
            List<Road>[][] list = new ArrayList[width][height];
            for(int i = 0; i < width; i++) {
                for(int j = 0; j < height; j++) {
                    list[i][j] = new ArrayList<>();
                }
            }

            // 그래프로 변환
            for(int i = 0; i < width; i++) {
                for(int j = 0; j < height; j++) {
                    // 벽은 제외
                    if(maps[i][j] == 1) {
                        // 가중치가 1
                        list[i][j].add(new Road(i, j, 1));
                    }
                }
            }

            int[][] dijkstra = dijkstra(maps, visited, result, list, 0, 0, width, height);

            if(dijkstra[width-1][height-1] == Integer.MAX_VALUE) return -1;

            return dijkstra[width-1][height-1];
        }

        private int[][] dijkstra(int[][] maps, boolean[][] visited, int[][] result, List<Road>[][] list, int startX, int startY, int width, int height) {
            // 가중치를 기준으로 오름차순 정렬
            Queue<Road> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.getWeight(), o2.getWeight()));
            // 초기값 설정
            visited[startX][startY] = true;
            result[startX][startY] = maps[startX][startY];
            pq.offer(new Road(startX, startY, result[startX][startY]));

            while (!pq.isEmpty()) {
                Road poll = pq.poll();
                // 상, 하, 좌, 우
                int[] ud = {1, -1, 0, 0};
                int[] lr = {0, 0, 1, -1};

                for(int i = 0; i < 4; i++) {
                    int newX = poll.getX() + ud[i];
                    int newY = poll.getY() + lr[i];

                    // 범위 체크
                    if (newX < 0 || newY < 0 || newX >= width || newY >= height) {
                        continue;
                    }

                    // 방문 이력이 없으면
                    if (!visited[newX][newY]){
                        visited[newX][newY] = true;

                        // road는 newX, newY의 인접 노드
                        for (Road road : list[newX][newY]) {
                            // 최단 거리 계산
                            int min = Math.min(result[poll.getX()][poll.getY()] + road.getWeight(), result[road.getX()][road.getY()]);
                            result[road.getX()][road.getY()] = min;
                            pq.add(new Road(road.getX(), road.getY(), min));
                        }
                    }
                }
            }

            return result;
        }
    }

    static class Road {
        private int x;
        private int y;
        private int weight;

        public Road(int x, int y, int weight) {
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
