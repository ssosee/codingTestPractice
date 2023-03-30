package backjoon.graph.topological;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz1005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 테스트 케이스
        int t = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 건물 갯수
            int n = Integer.parseInt(st.nextToken());
            // 건설 규칙 수
            int k = Integer.parseInt(st.nextToken());

            // 건물 짓는 시간
            int[] times = new int[n+1];
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++) {
                times[i] = Integer.parseInt(st.nextToken());
            }

            // 인접 리스트 초기화
            List<Integer>[] list = new ArrayList[n+1];
            for(int i = 1; i <= n; i++) {
                list[i] = new ArrayList<>();
            }
            // 진입 차수 배열
            int[] inDegree = new int[n+1];

            // 건설 순서(node1 -> node2)
            for(int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken());
                int node2 = Integer.parseInt(st.nextToken());

                // node1 -> node2
                list[node1].add(node2);
                // 인접 노드의 진입 차수 증가
                inDegree[node2]++;
            }

            // 승리하기 위해 지어야하는 건물
            int finalBuilding = Integer.parseInt(br.readLine());

            // 특정 건물을 짓기 전까지 걸린 시간
            int[] result = new int[n+1];
            // 위상 정렬
            topologicalSort(n, times, list, inDegree, result);

            System.out.println(times[finalBuilding] + result[finalBuilding]);
        }
        br.close();
    }

    private static void topologicalSort(int n, int[] times, List<Integer>[] list, int[] inDegree, int[] result) {
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 1; i <= n; i++) {
            // 진입 차수가 0 이면
            if(inDegree[i] == 0) {
                // 큐에 노드 삽입
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            for(Integer i : list[node]) {
                // 진입 차수 감소
                inDegree[i]--;

                // 특정 건물을 짓기전 걸린시간 계산
                result[i] = Math.max(result[i], result[node] + times[node]);

                // 진입 차수가 0 이면
                if(inDegree[i] == 0) {
                    // 큐에 노드 삽입
                    queue.add(i);
                }
            }
        }
    }
}
