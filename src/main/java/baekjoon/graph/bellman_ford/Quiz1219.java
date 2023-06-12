package baekjoon.graph.bellman_ford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz1219 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 도시 갯수
        int n = Integer.parseInt(st.nextToken());
        // 시작 도시
        int startCity = Integer.parseInt(st.nextToken());
        // 도착 도시
        int endCity = Integer.parseInt(st.nextToken());
        // 교통 수단 갯수
        int m = Integer.parseInt(st.nextToken());

        List<City> list = new ArrayList<>();

        // 엣지에 발생하는 비용
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int paid = Integer.parseInt(st.nextToken());

            list.add(new City(start, end, paid));
        }

        // 노드에 방문하면 발생하는 수익
        st = new StringTokenizer(br.readLine());
        List<Integer> profits = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int profit = Integer.parseInt(st.nextToken());
            profits.add(profit);
        }

        // 최단 거리 초기화
        long[] result = new long[n];
        for(int i = 0; i < n; i++) {
            result[i] = Long.MAX_VALUE;
        }

        // 출발 도시
        result[startCity] = profits.get(startCity) *(-1L);

        // 최단 거리 탐색
        bellmanFord(n, m, list, profits, result);

        // 음의 사이클이 발생하는 노드 저장 리스트
        List<Integer> minusCycleNode = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        long value = result[endCity];
        // 도착 지점에 갈 수 없는 경우
        if(value == Long.MAX_VALUE) {
            sb.append("gg");
        }
        // 음의 사이클이 발생하는 경우
        else if(isMinusCycle(m, list, profits, result, minusCycleNode)) {
            boolean flag = false;
            for(Integer node : minusCycleNode) {
                flag = bfs(node, endCity, list, n, m);
                // 음의 사이클에서 종료 노드가 연결되어 있는 경우
                if(flag) {
                    // 돈을 무한으로 벌 수 있다.
                    sb.append("Gee");
                    break;
                }
            }
            if(!flag){
                // 무한대로 돈 못번다.
                sb.append(value*(-1));
            }
        }
        // 음의 사이클이 발생하지 않는 경우
        else {
            // 무한대로 돈 못번다.
            sb.append(value*(-1));
        }

        System.out.print(sb);
        br.close();
    }

    // 음의 사이클이 발생했을 때 특정 두 도시가 연결되어있는지 확인
    private static boolean bfs(int startCity, int endCity, List<City> list, int n, int m) {
        if(startCity == endCity) {
            return true;
        }

        Queue<Integer> q = new ArrayDeque<>();
        // 방문 이력
        boolean[] visited = new boolean[n];
        q.offer(startCity);
        visited[startCity] = true;

        while (!q.isEmpty()) {
            Integer currentNode = q.poll();
            for(int i = 0; i < m; i++) {
                City city = list.get(i);
                int start = city.getStart();
                int end = city.getEnd();
                // 탐색 대상이면
                if(start == currentNode) {
                    // 방문 이력이 없으면
                    if(!visited[end]) {
                        // 노드가 연결 되어 있으면
                        if(endCity == end) {
                            return true;
                        }
                        visited[end] = true;
                        q.add(end);
                    }
                }
            }
        }
        return false;
    }

    // 음의 사이클이 발생하는지
    private static boolean isMinusCycle(int m, List<City> list, List<Integer> profits, long[] result, List<Integer> minusCycleNode) {
        boolean flag = false;
        // 음수 사이클 확인
        for(int j = 0; j < m; j++) {
            City city = list.get(j);
            int start = city.getStart();
            int end = city.getEnd();
            int paid = city.getPaid();
            // 해당 노드에 방문하면 발생하는 수익
            int profit = profits.get(end);
            // 해당 노드에 방문 했을 때 비용 계산
            int money = paid - profit;

            if (result[start] != Long.MAX_VALUE
                    && result[end] > result[start] + money) {
                // 음의 사이클이 발생하는 노드 정보 저장
                minusCycleNode.add(start);
                flag = true;
            }
        }
        return flag;
    }

    private static void bellmanFord(int n, int m, List<City> list, List<Integer> profits, long[] result) {
        // 노드 - 1 개 만큼 탐색
        for(int i = 0; i < n + 100; i++) {
            boolean flag = false;
            // 엣지 갯수 만큼 탐색
            for(int j = 0; j < m; j++) {
                City city = list.get(j);
                int start = city.getStart();
                int end = city.getEnd();
                int paid = city.getPaid();

                // 해당 노드에 방문하면 발생하는 수익
                int profit = profits.get(end);
                // 해당 노드에 방문 했을 때 비용 계산
                int money = paid - profit;

                if(result[start] != Long.MAX_VALUE
                    && result[end] > result[start] + money) {
                    result[end] = result[start] + money;
                    flag = true;
                }
            }
            // 시간 복잡도 감소
            if(!flag) {
                break;
            }
        }
    }

    static class City {
        private int start;
        private int end;
        private int paid;

        public City(int start, int end, int paid) {
            this.start = start;
            this.end = end;
            this.paid = paid;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getPaid() {
            return paid;
        }

        public void setMoney(int paid) {
            this.paid = paid;
        }
    }
}
