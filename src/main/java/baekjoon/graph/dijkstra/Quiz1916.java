package baekjoon.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz1916 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 도시 갯수
        int n = Integer.parseInt(br.readLine());
        // 버스 갯수
        int m = Integer.parseInt(br.readLine());

        // 인접 리스트 초기화
        List<Bus>[] list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 출발 도시 번호
            int start = Integer.parseInt(st.nextToken());
            // 도착 도시 번호
            int end = Integer.parseInt(st.nextToken());
            // 버스 비용
            int paid = Integer.parseInt(st.nextToken());
            // 그래프 생성
            list[start].add(new Bus(end, paid));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 출발 도시
        int startCity = Integer.parseInt(st.nextToken());
        // 도착 도시
        int endCity = Integer.parseInt(st.nextToken());

        // 최단 거리 배열
        int[] result = new int[n+1];
        for(int i = 1; i <= n; i++) {
            // 최단 거리를 모르기 때문에 최대로 저장
            result[i] = Integer.MAX_VALUE;
        }

        // 최단 경로 계산
        int[] dijkstra = dijkstra(list, result, n, startCity);

        System.out.print(dijkstra[endCity]);
        br.close();
    }

    private static int[] dijkstra(List<Bus>[] list, int[] result, int n, int startCity) {
        // 비용을 기준으로 오름차순
        Queue<Bus> pq = new PriorityQueue<>(new Comparator<Bus>() {
            @Override
            public int compare(Bus o1, Bus o2) {
                return Integer.compare(o1.getPaid(), o2.getPaid());
            }
        });
        // 방문 이력 배열
        boolean[] visited = new boolean[n+1];
        // 출발
        result[startCity] = 0;
        pq.add(new Bus(startCity, result[startCity]));

        while (!pq.isEmpty()) {
            Bus poll = pq.poll();
            // 방문하지 않으면
            if(!visited[poll.getNum()]) {
                // 방문!
                visited[poll.getNum()] = true;
                for(Bus bus : list[poll.getNum()]) {
                    // 최단 거리 계산 -> min(선택 노드의 최단거리 + 인접 노드의 가중치, 인접 노드의 최단거리)
                    int min = Math.min(result[poll.getNum()] + bus.getPaid(), result[bus.getNum()]);
                    // 최단 거리 저장
                    result[bus.getNum()] = min;
                    // 큐에 삽입
                    pq.add(new Bus(bus.getNum(), result[bus.getNum()]));
                }
            }
        }
        return result;
    }

    private static class Bus {
        private int num;
        private int paid;

        public Bus(int num, int paid) {
            this.num = num;
            this.paid = paid;
        }

        public int getNum() {
            return num;
        }

        public int getPaid() {
            return paid;
        }
    }
}
