package baekjoon.search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz10971 {
    static int n;
    static boolean[] visited;
    static List<Node>[] list;
    static int[] ud = {1, -1, 0, 0};
    static int[] lr = {0, 0, 1, -1};
    static int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // 인접 리스트 생성
        list = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int weight = Integer.parseInt(st.nextToken());
                list[i].add(new Node(j, weight));
                list[j].add(new Node(i, weight));
            }
        }

        // 시작점에서 출발해서 모든 노드를 순회하고 다시 시작점으로 돌아올 때 비용이 가장 적은것 찾기
        int min = INF;
        for(int i = 0; i < n; i++) {
            int[] dijkstra1 = dijkstra(i);
            int[] dijkstra2 = dijkstra(n - i - 1);
            min = Math.min(dijkstra1[n-1] + dijkstra2[i], min);
        }

        System.out.println(min);
    }

    private static int[] dijkstra(int start) {
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));
        pq.offer(new Node(start, 0));
        visited = new boolean[n];
        visited[start] = true;

        int[] result = new int[n];
        for(int i = 0; i < n; i++) {
            result[i] = INF;
        }
        result[start] = 0;

        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            int node = poll.node;

            for(Node nd : list[node]) {
                if(!visited[nd.node] && nd.node != 0) {
                    visited[nd.node] = true;
                    // 최단 거리 계산
                    int min = Math.min(result[node] + nd.weight, result[nd.node]);
                    result[nd.node] = min;

                    pq.offer(new Node(nd.node, min));
                }
            }
        }

        return result;
    }

    static class Node {
        private int node;
        private int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}
