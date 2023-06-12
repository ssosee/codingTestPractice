package baekjoon.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz1238 {
    /**
     * 각각의 학생들은 파티에 참석하기 위해 걸어가서 다시 그들의 마을로 돌아와야 한다!!
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // n명
        int n = Integer.parseInt(st.nextToken());
        // m개의 도로
        int m = Integer.parseInt(st.nextToken());
        // x번 마을(파티 장소)
        int x = Integer.parseInt(st.nextToken());

        // 인접 리스트 초기화
        List<Node>[] listFromX = new ArrayList[n+1];
        List<Node>[] listToX = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            listFromX[i] = new ArrayList<>();
            listToX[i] = new ArrayList<>();
        }

        // 그래프로 표현
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            // x에서 오는 길
            listFromX[start].add(new Node(end, time));
            // x로 가는 길
            listToX[end].add(new Node(start, time));
        }

        // X에서 다른 정점까지 최단 거리 저장 배열
        int[] distanceFromX = new int[n+1];
        // 다른 정점에서 X까지 최단 거리 저장 배열
        int[] distanceToX = new int[n+1];
        for(int i = 1; i <=n; i++) {
            distanceFromX[i] = Integer.MAX_VALUE;
            distanceToX[i] = Integer.MAX_VALUE;
        }

        // x에서 각 노드의 최단거리 distance
        // 각각의 학생들은 파티에 참석하기 위해 걸어가서 다시 그들의 마을로 돌아와야 한다.
        int[] dijkstara1 = dijkstara(listToX, distanceToX, m, x);
        int[] dijkstara2 = dijkstara(listFromX, distanceFromX, m, x);

        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++) {
            max = Math.max(max, dijkstara1[i] + dijkstara2[i]);
        }
        System.out.print(max);
        br.close();
    }

    public static int[] dijkstara(List<Node>[] list, int[] distance, int n, int x) {
        // 최단거리 == 최소시간, 최단거리를 기준으로 내림차순
        Queue<Node> priorityQueue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.getTime(), o2.getTime());
            }
        });
        // 노드 방문 이력
        boolean[] visited = new boolean[n+1];
        distance[x] = 0;
        priorityQueue.offer(new Node(x, distance[x]));

        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            if(!visited[node.getNode()]) {
                // 방문 완료
                visited[node.getNode()] = true;
                // 선택 노드의 인접 노드 탐색
                for(Node nd : list[node.getNode()]) {
                    // 선택 노드의 최단 거리 배열 값 + 엣지 가중치, 연결 노드의 최단거리 배열의 값
                    int min = Math.min(distance[node.getNode()] + nd.getTime(), distance[nd.getNode()]);
                    distance[nd.getNode()] = min;
                    priorityQueue.add(new Node(nd.getNode(), distance[nd.getNode()]));
                }
            }
        }

        return distance;
    }

    static class Node {
        private int node;
        private int time;

        public Node(int node, int time) {
            this.node = node;
            this.time = time;
        }

        public int getNode() {
            return node;
        }

        public int getTime() {
            return time;
        }
    }
}
