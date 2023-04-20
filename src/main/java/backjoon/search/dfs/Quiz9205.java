package backjoon.search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz9205 {
    static int n;
    static boolean[] visited;
    static List<Integer>[] newMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < t; tc++) {
            n = Integer.parseInt(br.readLine());

            // x,y를 저장하는 리스트 생성
            List<Node> path = new ArrayList<>();

            // 시작점, 편의점, 도착점 저장
            for(int i = 0; i < n + 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                path.add(new Node(x, y));
            }

            // 인접 리스트 생성
            newMap = new ArrayList[n+2];
            for(int i = 0; i < n + 2; i++) {
                newMap[i] = new ArrayList<>();
            }

            for(int i = 0; i < n + 2; i++) {
                for(int j = i + 1; j < n + 2; j++) {
                    Node node1 = path.get(i);
                    Node node2 = path.get(j);

                    // 거리가 1000m 이하이면
                    if(getManhattanDistance(node1.getX(), node1.getY(), node2.getX(), node2.getY()) <= 1000) {
                        // i, j를 노드라고 생각하고 경로 생성
                        // 양방향
                        newMap[i].add(j);
                        newMap[j].add(i);
                    }
                }
            }

            // newMap에는 인접한 거리가 1000m이하인 경로들로 이루어져 있다.
            // 1000m이하이니까 무조건 happy 아닌가? 라고 생각할 수 있는데
            // 1000m이하로 이루어져 있더라도, 도착점에 가지 못하면 sad입니다.
            visited = new boolean[n+2];
            String bfs = bfs(0);

            sb.append(bfs).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    private static String bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        // 시작점 삽입
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            // 노드를 꺼낸다.
            Integer poll = queue.poll();

            // 마지막 지점에 도착하면
            if(poll == n + 1) {
                return "happy";
            }

            // 인접 노드를 탐색
            for(Integer node : newMap[poll]) {
                // 인접 노드에 방문 이력이 없으면
                if(!visited[node]) {
                    visited[node] = true;
                    queue.add(node);
                }
            }
        }

        return "sad";
    }

    // 맨하튼 거리 계산
    private static int getManhattanDistance(int startX, int startY, int endX, int endY) {
        return Math.abs(startX - endX) + Math.abs(startY - endY);
    }

    static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
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
