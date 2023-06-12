package baekjoon.graph.topological;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz1766 {
    /**
     * 1 ~ n 까지 문제(숫자가 작을 수록 쉬운 문제)
     * 1. N개의 문제 모두 풀어야함.
     * 2. 먼저 푸는 것이 좋은 문제부터 풀어야함
     * 3. 가능한 쉬운 문제부터 풀어야함
     */

    /**
     * 4 2  // 문제 수, 문제 순서쌍 갯수
     * 4 2  // 4가 2보다 먼저 풀면 좋은 문제   4 -> 2
     * 3 1  // 3이 1보다 먼저 풀면 좋은 문제   3 -> 1
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());
        // 문제 수
        int n = Integer.parseInt(st.nextToken());
        // 문제 순서쌍 갯수
        int m = Integer.parseInt(st.nextToken());

        // 인접 리스트
        List<Integer>[] list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        // 진입 차수 배열
        int[] inDegree = new int[n+1];
        for(int i = 0; i < m; i++) {
            st =  new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            list[node1].add(node2);
            inDegree[node2]++;
        }

        // 가능한 쉬운 문제 부터 풀어야하기 때문에 우선순위 큐 사용(오름 차순)
        Queue<Integer> queue = new PriorityQueue<>();
        for(int i = 1; i <= n; i++) {
            // 노드의 인접 차수가 0인 노드를 큐에 삽입
            if(inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 위상 정렬 배열
        int[] result = new int[n+1];
        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()) {
            Integer node = queue.poll();
            // 위상 정렬 배열에 노드를 삽입
            result[node] = node;
            sb.append(node).append(" ");
            for(Integer i : list[node]) {
                // 노드의 인접 노드의 인접 차수를 1 감소
                inDegree[i]--;
                // 인접 차수가 0이면
                if(inDegree[i] == 0) {
                    // 큐에 삽입
                    queue.offer(i);
                }
            }
        }

        System.out.print(sb);
        br.close();
    }
}
