package backjoon.graph.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz1197 {
    static long sum = 0;
    public static void main(String[] args) throws IOException {
        /**
         * 가중치가 있는 무방향 그래프에서
         * 최소 비용으로 노드를 연결할 수 있는 알고리즘
         * 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점 갯수
        int v = Integer.parseInt(st.nextToken());
        // 간선 갯수
        int e = Integer.parseInt(st.nextToken());
        
        // 엣지 리스트 생성
        List<Node> list = new ArrayList<>();
        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list.add(new Node(start, end, weight));
        }

        // 가중치를 기준으로 오름차순 정렬
        Collections.sort(list, Comparator.comparingInt(Node::getWeight));

        // 대표노드 저장 배열 초기화
        int[] parent = new int[v+1];
        for(int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        int edgeCount = 0;
        // 노드-1개 만큼 수행
        for(int i = 0; i < e; i++) {

            if(edgeCount == v - 1) break;

            Node node = list.get(i);
            int start = node.getStart();
            int end = node.getEnd();
            int weight = node.getWeight();

            edgeCount += union(parent, start, end, weight);
        }

        System.out.print(sum);
        br.close();
    }

    private static int union(int[] parent, int start, int end, int weight) {
        // 대표노드 찾기
        int ceoNode1 = find(parent, start);
        int ceoNode2 = find(parent, end);

        // 대표 노드가 다르면 유니온 연산 수행
        if(ceoNode1 != ceoNode2) {
            if(ceoNode1 > ceoNode2) {
                parent[ceoNode1] = ceoNode2;
            } else {
                parent[ceoNode2] = ceoNode1;
            }
            // 최소 비용 계산
            sum += weight;
            return 1;
        }
        return 0;
    }

    // 대표노드 찾기
    private static int find(int[] parent, int node) {
        if(parent[node] == node) {
            return node;
        } else {
            return parent[node] = find(parent, parent[node]);
        }
    }

    private static class Node {
        private int start;
        private int end;
        private int weight;

        public Node(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getWeight() {
            return weight;
        }
    }
}
