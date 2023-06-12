package baekjoon.graph.bipartite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz1707 {
    static boolean flag = true;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 테스트 갯수
        int k = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < k; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 노드 갯수
            int v = Integer.parseInt(st.nextToken());
            // 간선 갯수
            int e = Integer.parseInt(st.nextToken());

            // 인접 리스트 생성(index 0은 사용하지 않음)
            List<Integer>[] lists = new ArrayList[v+1];
            // 인접 리스트 초기화
            for(int i = 1; i <= v; i++) {
                lists[i] = new ArrayList<>();
            }

            // 인접 리스트 저장
            for(int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                // 양방향
                lists[start].add(end);
                lists[end].add(start);
            }

            // 노드 탐색 이력 배열
            boolean[] visited = new boolean[v+1];
            // 그룹 배열(0 또는 1로 표현)
            int[] group = new int[v+1];
            // 노드 갯수만큼 이분 그래프 탐색
            for(int i = 1; i <= v; i++) {
                dfs(lists, visited, group, i);
            }
            if(flag) {
                sb.append("YES").append("\n");
            }
            else {
                sb.append("NO").append("\n");
            }
            // 초기화
            flag = true;
        }
        System.out.print(sb);
        br.close();
    }

    private static void dfs(List<Integer>[] lists, boolean[] visited, int[] group, int start) {
        visited[start] = true;
        // 해당 노드의 인접노드의 갯수만큼 탐색(시작노드의 도착노드 갯수만큼 탐색)
        for(int j = 0; j < lists[start].size(); j++) {
            // 인접 노드
            Integer adjacentNode = lists[start].get(j);
            // 인접 노드에 탐색 이력이 없으면
            if(!visited[adjacentNode]) {
                // 인접노드의 그룹을 시작노드의 그룹과 다른 집합으로 분류
                group[adjacentNode] = (group[start] + 1) % 2; // 0이면 1로, 1이면 0으로
                // 인접노드 dfs 탐색
                dfs(lists, visited, group, adjacentNode);
            }
            // 시작 노드와 인접 노드가 같은 그룹이면
            else if(group[start] == group[adjacentNode]) {
                // 이분 그래프가 아님!
                flag = false;
            }
        }
    }
}
