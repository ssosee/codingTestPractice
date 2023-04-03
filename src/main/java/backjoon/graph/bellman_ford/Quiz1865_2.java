package backjoon.graph.bellman_ford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz1865_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        for(int t = 0; t < tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 지점의 수
            int n = Integer.parseInt(st.nextToken());
            // 도로 갯수
            int m = Integer.parseInt(st.nextToken());
            // 웜홀 갯수
            int w = Integer.parseInt(st.nextToken());

            // 도로는 방향이 없고, 웜홀은 방향이 있음
            // 엣지 리스트
            List<Node> edgeList = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                // 시작점
                int start = Integer.parseInt(st.nextToken());
                // 끝점
                int end = Integer.parseInt(st.nextToken());
                // 걸리는 시간
                int time = Integer.parseInt(st.nextToken());

                // 양 방향
                edgeList.add(new Node(start, end, time));
                edgeList.add(new Node(end, start, time));
            }

            for (int i = 0; i < w; i++) {
                st = new StringTokenizer(br.readLine());
                // 시작점
                int start = Integer.parseInt(st.nextToken());
                // 끝점
                int end = Integer.parseInt(st.nextToken());
                // 걸리는 시간
                int time = Integer.parseInt(st.nextToken());

                // 단방향
                edgeList.add(new Node(start, end, time * -1));
            }

            // 적절한 값으로 최단거리 배열 초기화
            // 단순 그래프에서의 사이클 유무만 파악할 때는 result[]의 초기화를 어떤 값으로 해주어도 상관 없습니다.
            // 거리를 구하는게 아니라 음의 사이클이 존재할 때, 변화만 파악하면 되기 때문
            long[] result = new long[n+1];
            for(int i = 1; i < n; i++) {
                result[i] = 987654321;
            }

            // 출발 노드 설정
            result[1] = 0;

            bellmanFord(n, edgeList, result);

            if(isMinusCycle(edgeList, result)) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }

        System.out.print(sb);
        br.close();
    }

    private static void bellmanFord(int n, List<Node> edgeList, long[] result) {
        for(int i = 0; i < n - 1; i++) {
            boolean flag = false;
            for(Node node : edgeList) {
                int start = node.getStart();
                int end = node.getEnd();
                int time = node.getTime();

                // 거리를 구하는게 아니라 음의 사이클이 존재할 때, 변화만 파악하면 되기 때문에
                // result[start] != INF 가 없어도 된다~
                if(result[end] > result[start] + time) {
                    result[end] = result[start] +time;
                    flag = true;
                }
            }

            if (!flag) {
                break;
            }
        }
    }

    private static boolean isMinusCycle(List<Node> edgeList, long[] result) {
        for(Node node : edgeList) {
            int start = node.getStart();
            int end = node.getEnd();
            int time = node.getTime();

            if(result[end] > result[start] + time) {
                return true;
            }
        }
        return false;
    }

    static class Node {
        private int start;
        private int end;
        private int time;

        public Node(int start, int end, int time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getTime() {
            return time;
        }
    }
}
