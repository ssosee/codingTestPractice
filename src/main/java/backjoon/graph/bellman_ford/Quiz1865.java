package backjoon.graph.bellman_ford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz1865 {
    public static void main(String[] args) throws IOException {
        /**
         * 2          // 테스트 케이스 갯수
         * 3 3 1    1 // 지점갯수, 도로 갯수(m:3), 웜홀 갯수(w:1)
         * 1 2 2    2 // 도로 정보 (시작점, 끝점, 걸리는 시간) 2부터 m+1 까지
         * 1 3 4    3 // 도로 정보 (시작점, 끝점, 걸리는 시간)
         * 2 3 1    4 // 도로 정보 (시작점, 끝점, 걸리는 시간)
         * 3 1 3    5 // 웜홀 정보 (시작점, 끝점, 걸리는 시간) m+2(5)부터 m+w+1(5) 까지
         * 3 2 1    1 // 지점갯수, 도로 갯수(m:2), 웜홀 갯수(w:1)
         * 1 2 3    2 // 도로 정보 (시작점, 끝점, 걸리는 시간) 2부터 m+1 까지
         * 2 3 4    3 // 도로 정보 (시작점, 끝점, 걸리는 시간) 2부터 m+1 까지
         * 3 1 8    4 // 웜홀 정보 (시작점, 끝점, 걸리는 시간) m+2(4)부터 m+w+1(4) 까지
         */
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

            for(int i = 0; i < m; i++) {
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

            for(int i = 0; i < w; i++) {
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

            // 한 지점에서 출발해서, 시간이 줄면서 출발위치로 돌아오는게 가능한지
            // 이말은 음수 사이클이 존재하는지 파악 하면된다.
            // 그런데 노드끼리 연결되지 않은 노드가 있을 수 있다.
            // 따라서 아무 노드를 출발점으로 하면 안되고, 모든 노드를 테스트 해본다.

            // 노드 갯수만큼 실행
            boolean flag = false;
            for(int k = 1; k <= n; k++) {
                if(bellmanFord(k, n, edgeList)) {
                    sb.append("YES").append("\n");
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                sb.append("NO").append("\n");
            }
        }

        System.out.print(sb);
        br.close();
    }

    private static boolean bellmanFord(int k, int n, List<Node> edgeList) {

        // 최단거리 초기화
        long[] result = new long[n+1];
        for(int i = 1; i <= n; i++) {
            result[i] = Long.MAX_VALUE;
        }

        // 시작 노드
        result[k] = 0;

        for(int i = 0; i < n - 1; i++) {
            boolean flag = false;
            // 엣지 갯수만큼 실행
            for(int j = 0; j < edgeList.size(); j++) {
                int start = edgeList.get(j).getStart();
                int end = edgeList.get(j).getEnd();
                int time = edgeList.get(j).getTime();

                if(result[start] != Long.MAX_VALUE
                    && result[end] > result[start] + time) {
                    // 최단거리 계산
                    result[end] = result[start] + time;
                    flag = true;
                }
            }
            if(!flag) {
                break;
            }
        }
        // 음수 사이클 존재 확인
        if(isMinusCycle(edgeList, result)) {
            return true;
        }

        return false;
    }

    private static boolean isMinusCycle(List<Node> edgeList, long[] result) {
        // 엣지 갯수만큼 실행
        for (int j = 0; j < edgeList.size(); j++) {
            int start = edgeList.get(j).getStart();
            int end = edgeList.get(j).getEnd();
            int time = edgeList.get(j).getTime();

            if (result[start] != Long.MAX_VALUE
                    && result[end] > result[start] + time) {
                // 음수 사이클 존재!
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
