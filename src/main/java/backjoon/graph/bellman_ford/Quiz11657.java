package backjoon.graph.bellman_ford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz11657 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 도시 갯수
        int n = Integer.parseInt(st.nextToken());
        // 버스 노선 갯수
        int m = Integer.parseInt(st.nextToken());

        // 엣지 리스트
        List<Bus> edgeList = new ArrayList<>();

        // 그래프로 표현
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList.add(new Bus(start, end, weight));
        }

        // 최단 거리 배열
        long[] result = new long[n+1];
        for(int i = 1; i <= n; i++) {
            result[i] = Long.MAX_VALUE;
        }

        // 출발 노드
        result[1] = 0;

        // 최단 거리 업데이트 횟수는 노드 갯수 - 1
        for(int i = 0; i < n - 1; i++) {
            // 엣지의 갯수만큼 탐색
            for(int j = 0; j < m; j++) {
                Bus bus = edgeList.get(j);
                if(result[bus.getStart()] != Long.MAX_VALUE
                        && result[bus.getEnd()] > result[bus.getStart()] + bus.getWeight()) {
                    result[bus.getEnd()] = result[bus.getStart()] + bus.getWeight();
                }
            }
        }

        // 음수 사이클 존재 확인(엣지를 한번 씩 사용해서 업데이트 유무 확인)
        if(isMinusCycle(m, edgeList, result)) {
            System.out.print(-1);
            br.close();
            return;
        }

        // 음수 사이클이 없으면
        // 1 -> 2, 1 -> 3, 1 -> 4 ... 최단 거리 출력
        StringBuilder sb = new StringBuilder();
        for(int i = 2; i <= n; i++) {
            // 경로가 없으면 -1 출력
            if (result[i] == Long.MAX_VALUE) {
                sb.append(-1).append("\n");
            } else {
                sb.append(result[i]).append("\n");
            }
        }
        System.out.print(sb);
        br.close();
    }

    // 음수 사이클 존재 확인(엣지를 한번 씩 사용해서 업데이트 유무 확인)
    private static boolean isMinusCycle(int m, List<Bus> edgeList, long[] result) {
        for(int j = 0; j < m; j++) {
            Bus bus = edgeList.get(j);
            if(result[bus.getStart()] != Long.MAX_VALUE
                    && result[bus.getEnd()] > result[bus.getStart()] + bus.getWeight()) {
                return true;
            }
        }
        return false;
    }

    static public class Bus {
        private int start;
        private int end;
        private int weight;

        public Bus(int start, int end, int weight) {
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
