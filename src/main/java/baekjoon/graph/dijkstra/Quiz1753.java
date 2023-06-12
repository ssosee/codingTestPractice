package baekjoon.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz1753 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점 갯수
        int v = Integer.parseInt(st.nextToken());
        // 간선 갯수
        int e = Integer.parseInt(st.nextToken());

        // 시작 노드
        int startNode = Integer.parseInt(br.readLine());

        // 인접 리스트 초기화
        List<Node>[] list = new ArrayList[v+1];
        for(int i = 1; i <= v; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            // 시작, 끝, 가중치
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            // 그래프로 표현
            list[start].add(new Node(end, weight));
        }

        // 최단 거리 배열
        int[] result = new int[v+1];
        for(int i = 1; i <= v; i++) {
            // 최단 거리를 모르기 때문에 MAX로 초기화
            result[i] = Integer.MAX_VALUE;
        }

        // 최단 경로 계산
        dijkstra(list, result, startNode, v);

        StringBuilder sb = new StringBuilder();
        String str = "";
        for(int i = 1; i <= v; i++) {
            if(result[i] == Integer.MAX_VALUE) {
                str = "INF";
            } else {
                str = Integer.toString(result[i]);
            }
            sb.append(str).append("\n");
        }
        System.out.print(sb);

    }

    private static void dijkstra(List<Node>[] list, int[] result, int startNode, int v) {
        // 방문 이력 배열
        boolean[] visited = new boolean[v+1];
        // 가중치를 기준으로 오름차순
        Queue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.getWeight(), o2.weight);
            }
        });
        result[startNode] = 0;
        pq.add(new Node(startNode, result[startNode]));

        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            if(!visited[poll.getNode()]) {
                // 방문으로 처리
                visited[poll.getNode()] = true;
                for(Node node : list[poll.getNode()]) {
                    // 최단거리 계산 -> min(선택 노드의 값 + 인접 노드의 경로 가중치, 인접 노드의 값)
                    int min = Math.min(result[poll.getNode()] + node.getWeight(), result[node.getNode()]);
                    // 인접 노드의 최단거리 갱신
                    result[node.getNode()] = min;
                    // 큐에 삽입
                    pq.add(new Node(node.getNode(), result[node.getNode()]));
                }
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
