package backjoon.graph.topological;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz2252 {
    /**
     * 3 2 // 학생 수, 키 비교 횟수
     * 1 3 // 1이 3보다 앞에 서야함 3 -> 1
     * 2 3 // 2가 3보가 앞에 서야함 3 -> 2
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 학생 수
        int n = Integer.parseInt(st.nextToken());
        // 키 비교 횟수
        int m = Integer.parseInt(st.nextToken());

        // 정렬 배열
        List<Integer> result = new ArrayList<>();
        // 진입 차수 배열
        int[] inDegree = new int[n+1];
        // 인접 리스트 초기화
        List<Integer>[] list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        // 인접 리스트 생성, 진입차수 배열 생성
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            // node1이 node2보다 앞에 서야함
            // node1 -> node2
            list[node1].add(node2);
            inDegree[node2]++; // 인접노드의 진입차수 증가
        }

        StringBuilder sb = new StringBuilder();
        int node = 1;
        // 진입차수 방문 이력 배열
        boolean[] visited = new boolean [n+1];
        // 위상 정렬
        while(result.size() < n) {
            int degree = inDegree[node];
            // 진입 차수가 0이면
            if(degree == 0 && !visited[node]) {
                // 노드를 정렬 배열에 저장
                result.add(node);
                sb.append(node).append(" ");
                // 노드의 인접 노드의 진입 차수를 1씩 감소
                for(Integer j : list[node]) {
                    inDegree[j]--;
                }
                visited[node] = true;
                node = 1;
            } else {
                node++;
            }
        }
        System.out.print(sb);
        br.close();
    }
}
