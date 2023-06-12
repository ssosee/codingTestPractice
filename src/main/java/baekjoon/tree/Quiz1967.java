package baekjoon.tree;

import java.io.*;
import java.util.*;

public class Quiz1967 {

    static List<Node>[] tree;
    static boolean[] visited;
    static int result = 0;
    public static void main(String[] args) throws IOException {
        /**
         * 모든 노드 각 노드까지 DFS를 이용하여 최대거리를 구한다.
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 노드 갯수
        int n = Integer.parseInt(br.readLine());

        // 인접 리스트 초기화
        tree = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        // 인접 리스트 생성
        for(int i = 1; i <= n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 무방향 -> 양방향
            tree[node1].add(new Node(node2, weight));
            tree[node2].add(new Node(node1, weight));
        }

        for(int i = 1; i <= n; i++) {
            visited = new boolean[n+1];
            // 첫 노드는 방문 처리한다.
            // 방문처리 안하면 다시 첫 노드를 방문하기 때문
            visited[i] = true;
            dfs(i, 0);
        }

        System.out.print(result);
        br.close();

    }

    private static void dfs(int start, int sum) {

        // 최대 거리 계산
        result = Math.max(sum, result);

        // 시작점에서 인접노드로 탐색 시작~
        for(Node data : tree[start]) {
            // 인접 노드 꺼낸다.
            int node = data.getNode();
            int weight = data.getWeight();

            // 방문 이력이 없으면
            if(!visited[node]) {
                // 방문 처리
                visited[node] = true;
                dfs(node, sum + weight);
                // 모든 노드를 탐색해야하기 때문에 방문 이력 삭제
                visited[node] = false;
            }
        }
    }

    private static class Node {
        private int node;
        private int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        public int getNode() {
            return node;
        }

        public int getWeight() {
            return weight;
        }
    }
}
