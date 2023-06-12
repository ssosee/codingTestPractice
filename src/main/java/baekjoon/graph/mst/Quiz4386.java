package baekjoon.graph.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz4386 {

    static double sum = 0f;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 별의 수
        int n = Integer.parseInt(br.readLine());

        // 별 위치
        List<Star> starPositions = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            starPositions.add(new Star(i, x, y));
        }

        // 엣지 리스트 생성
        List<Star> edgeList = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            Star star1 = starPositions.get(i);
            double x1 = star1.getX();
            double y1 = star1.getY();
            int start = star1.getStart();

            for(int j = i+1; j < n; j++) {
                Star star2 = starPositions.get(j);
                double x2 = star2.getX();
                double y2 = star2.getY();
                int end = star2.getStart();

                // 거리 계산
                double weight = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
                edgeList.add(new Star(start, end, weight));
            }
        }

        // 가중치를 기준으로 오름차순 정렬
        Collections.sort(edgeList, Comparator.comparingDouble(Star::getWeight));

        // 대표 노드 저장 배열
        int[] ceoNode = new int[n];
        for(int i = 0; i < n; i++) {
            ceoNode[i] = i;
        }

        int edgeCount = 0;
        for(int i = 0; i < edgeList.size(); i++) {

            if(edgeCount == n - 1) break;

            Star star = edgeList.get(i);
            int start = star.getStart();
            int end = star.getEnd();
            double weight = star.getWeight();

            edgeCount += union(ceoNode, start, end, weight);
        }

        System.out.print(sum);
        br.close();
    }

    private static int union(int[] ceoNode, int star1Node, int star2Node, double weight) {
        int ceoNode1 = find(ceoNode, star1Node);
        int ceoNode2 = find(ceoNode, star2Node);

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

    private static class Star {
        private int start;
        private int end;
        private double x;
        private double y;
        private double weight;


        public Star(int start, int end, double weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public Star(int start, double x, double y) {
            this.start = start;
            this.x = x;
            this.y = y;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getWeight() {
            return weight;
        }
    }
}
