package baekjoon.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz1504 {

    static int INF = 200_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점 갯수
        int n = Integer.parseInt(st.nextToken());
        // 간선 갯수
        int e = Integer.parseInt(st.nextToken());

        // 인접 리스트 초기화
        List<Node>[] list = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        // 그래프 생성
        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 양방향
            list[start].add(new Node(end, weight));
            list[end].add(new Node(start, weight));
        }

        // 반드시 거쳐야하는 두점
        st = new StringTokenizer(br.readLine());
        int stopOver1 = Integer.parseInt(st.nextToken());
        int stopOver2 = Integer.parseInt(st.nextToken());

        // start -> stopOver1 -> stopOver2 -> end 경로
        int case1 = 0;
        // 시작점(start)에서 stopOver1 까지 최단 거리
        case1 += dijkstra(list, 1, stopOver1, n);
        // stopOver1에서 stopOver2 까지 최단 거리
        case1 += dijkstra(list, stopOver1, stopOver2, n);
        // stopOver2에서 n 까지 최단 거리
        case1 += dijkstra(list, stopOver2, n, n);
        if(case1 >= INF) {
            System.out.print(-1);
            br.close();
            return;
        }

        // start -> stopOver2 -> stopOver1 -> end 경로
        int case2 = 0;
        // 시작점(start)에서 stopOver2 까지 최단 거리
        case2 += dijkstra(list, 1, stopOver2, n);
        // stopOver2에서 stopOver1 까지 최단 거리
        case2 += dijkstra(list, stopOver2, stopOver1, n);
        // stopOver1에서 n 까지 최단 거리
        case2 += dijkstra(list, stopOver1, n, n);
        if(case2 >= INF) {
            System.out.print(-1);
            br.close();
            return;
        }

        // 최소 거리 비교
        int min = Math.min(case1, case2);
        System.out.print(min);
        br.close();
    }

    private static int dijkstra(List<Node>[] list, int start, int end, int n) {
        // 최단거리 저장 배열 초기화
        int[] result = new int[n+1];
        for(int i = 1; i <= n; i++) {
            result[i] = INF;
        }

        // 시작점 설정
        result[start] = 0;

        // 가중치를 기준으로 올림차순 우선순위큐 생성
        Queue<Node> pq = new PriorityQueue<>(
                (o1, o2) -> Integer.compare(o1.getWeight(), o2.getWeight())
        );
        // 큐에 출발 노드 정보 삽입
        pq.add(new Node(start, result[start]));

        // 방문 이력 배열
        boolean[] visited = new boolean[n+1];

        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            // 방문 이력이 없으면
            if(!visited[poll.getNode()]) {
                visited[poll.getNode()] = true;
                for(Node node : list[poll.getNode()]) {
                    // 최단 거리 계산
                    // min(현재 최단거리 + 해당 노드로 갔을 때 가중치, 해당 노드의 최단 거리)
                    result[node.getNode()] = Math.min(result[poll.getNode()] + node.getWeight(), result[node.getNode()]);
                    pq.add(new Node(node.getNode(), result[node.getNode()]));
                }
            }
        }

        return result[end];
    }

    static class Node {
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
