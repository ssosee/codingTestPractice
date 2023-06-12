package baekjoon.graph.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz1674 {
    // 최소 비용
    static int sum = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 마을 갯수
        int n = Integer.parseInt(st.nextToken());
        // 도로 갯수
        int m = Integer.parseInt(st.nextToken());

        // 엣지 리스트 생성
        List<Node> edgeList = new ArrayList<>();

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList.add(new Node(start, end, weight));
        }

        // 가중치를 기준으로 오름차순 정렬
        Collections.sort(edgeList, Comparator.comparingInt(Node::getWeight));

        // 대표노드 저장 배열
        int[] ceoNode = new int[n+1];
        for(int i = 1; i <= n; i++) {
            ceoNode[i] = i;
        }

        /**
         * 길의 유지비가 최소가 되도록 2개의 마을을 분리하기 위해서는
         * n - 1 번째 도로를 기준으로 마을을 분리하면 된다.
         */

        // 최소 비용 계산
        int edgeCount = 0;
        for(int i = 0; i < m; i++) {
            // 필요한 도로를 완성하면 탈출
            if(edgeCount == n - 2) break;

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
        int ceoNode1 = find(ceoNode, start);
        int ceoNode2 = find(ceoNode, end);

        if(ceoNode1 != ceoNode2) {
            if(ceoNode1 > ceoNode2) {
                ceoNode[ceoNode1] = ceoNode2;
            } else {
                ceoNode[ceoNode2] = ceoNode1;
            }
            sum += weight;
            return 1;
        }
        return 0;
    }

    private static int find(int[] ceoNode, int node) {
        if(ceoNode[node] == node) {
            return node;
        }
        return ceoNode[node] = find(ceoNode, ceoNode[node]);
    }

    static class Node {
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
