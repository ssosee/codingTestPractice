package backjoon.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz4485 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (true) {
            // 동굴 크기
            int n = Integer.parseInt(br.readLine());

            // 동굴의 크기가 0이면 종료
            if(n == 0) break;

            // 인접 리스트
            List<Node>[][] list = new ArrayList[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    list[i][j] = new ArrayList<>();
                }
            }

            // 동굴, 인접 리스트 생성
            int[][] map = new int[n][n];
            for(int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    list[i][j].add(new Node(i, j, map[i][j]));
                }
            }

            int startX = 0;
            int startY = 0;

            // 최단 거리 저장 배열
            int[][] result = new int[n][n];
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    // 쵀댓값으로 초기화
                    result[i][j] = Integer.MAX_VALUE;
                }
            }
            // 최소비용 탐색
            dijkstra(list, map, result, startX, startY, n);

            count++;
            sb.append("Problem ")
                    .append(count)
                    .append(": ")
                    .append(result[n-1][n-1])
                    .append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    private static void dijkstra(List<Node>[][] list, int[][] map, int[][] result, int startX, int startY, int n) {
        Queue<Node> pq = new PriorityQueue<>(
                (o1, o2) -> Integer.compare(o1.getWeight(), o2.getWeight())
        );
        // 방문 이력
        boolean[][] visited = new boolean[n][n];
        visited[startX][startY] = true;
        // 최단 거리
        result[startX][startY] = map[startX][startY];
        pq.add(new Node(startX, startY, result[startX][startY]));

        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            int[] ud = {1, -1, 0, 0};
            int[] lr = {0, 0, 1, -1};

            for(int i = 0; i < 4; i++) {
                int newX = poll.getX() + ud[i];
                int newY = poll.getY() + lr[i];

                if(newX < 0 || newY < 0 || newX >= n || newY >= n) continue;

                if (!visited[newX][newY]) {
                    // 방문 등록
                    visited[newX][newY] = true;
                    for (Node nodes : list[newX][newY]) {
                        // 최소 비용 계산
                        int min = Math.min(result[poll.getX()][poll.getY()] + nodes.getWeight(), result[nodes.getX()][nodes.getY()]);
                        // 최소 비용 저장
                        result[nodes.getX()][nodes.getY()] = min;
                        // 큐에 삽입
                        pq.add(new Node(nodes.getX(), nodes.getY(), result[nodes.getX()][nodes.getY()]));
                    }
                }
            }
        }
    }

    private static class Node {
        private int x;
        private int y;
        private int weight;

        public Node(int x, int y, int weight) {
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
