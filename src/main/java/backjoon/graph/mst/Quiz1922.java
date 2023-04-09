package backjoon.graph.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz1922 {

    // 최소 비용
    static int sum = 0;

    public static void main(String[] args) throws IOException {
        /**
         * 최소 신장 트리?
         * 무방향의 모든 그래프를 연결할 때
         * 최소 비용으로 연결하는 트리
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 컴퓨터 수
        int n = Integer.parseInt(br.readLine());
        // 연결할 수 있는 선의 수
        int m = Integer.parseInt(br.readLine());

        // 엣지 리스트 생성
        List<Node> edgeList = new ArrayList<>();

        // 그래프로 변환
        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList.add(new Node(start, end, weight));
        }

        // 가중치를 기준으로 오름차순 정렬
        Collections.sort(edgeList, Comparator.comparingInt(Node::getWeight));

        // 대표노드 생성
        int[] ceoNode = new int[n+1];
        for(int i = 1; i <= n; i++) {
            ceoNode[i] = i;
        }

        int edgeCount = 0;
        for(int i = 0; i < m; i++) {
            // 생성 경로 = 노드 - 1개 이면 탈출
            if(edgeCount == n - 1) break;

            Node node = edgeList.get(i);
            int start = node.getStart();
            int end = node.getEnd();
            int weight = node.getWeight();

            edgeCount += union(ceoNode, start, end, weight);
        }

        System.out.print(sum);
        br.close();
    }

    private static int union(int[] ceoNode, int start, int end, int weight) {
        // 대표 노드 찾기
        int ceoNode1 = find(ceoNode, start);
        int ceoNode2 = find(ceoNode, end);

        // 대표 노드가 다르면! 유니온 연산 수행
        if(ceoNode1 != ceoNode2) {
            if(ceoNode1 > ceoNode2) {
                ceoNode[ceoNode1] = ceoNode2;
            } else {
                ceoNode[ceoNode2] = ceoNode1;
            }
            // 최소 비용 계산
            sum += weight;
            return 1;
        }
        return 0;
    }

    private static int find(int[] ceoNode, int node) {
        if(ceoNode[node] == node) {
            return node;
        } else {
            return ceoNode[node] = find(ceoNode, ceoNode[node]);
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
