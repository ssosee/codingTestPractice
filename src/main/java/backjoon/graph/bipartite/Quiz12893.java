package backjoon.graph.bipartite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz12893 {
    static boolean theory = true;
    public static void main(String[] args) throws IOException {
        /**
         * 적의 적은 친구
         * 적대관계, 우호관계 2가지 그룹으로 존재할 수 있는지
         * 즉, 이분 그래프가 가능한지?
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 노드 수
        int n = Integer.parseInt(st.nextToken());
        // 간선 수
        int e = Integer.parseInt(st.nextToken());

        // 인접 리스트 초기화
        List<Integer>[] list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        // 적대 관계 저장
        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            // 서로 적대 관계
            list[start].add(end);
            list[end].add(start);
        }

        // 탐색 이력
        boolean[] visited = new boolean[n+1];
        // 관계(적대 관계: 0, 우호관계: 1)
        int[] group = new int[n+1];
        // 노드 수 만큼 탐색
        for(int i = 1; i <= n; i++) {
            dfs(list, visited, group, i);
        }

        if(theory) {
            System.out.print(1);
        } else {
            System.out.print(0);
        }
        br.close();
    }

    private static void dfs(List<Integer>[] list, boolean[] visited, int[] group, int start) {
        visited[start] = true;
        for(int i = 0; i < list[start].size(); i++) {
            // 인접 노드
            Integer adjacentNode = list[start].get(i);
            // 탐색 이력이 없으면
            if(!visited[adjacentNode]) {
                // 관계 변경(우호 관계는 적대로, 적대관계는 우호로 변경)
                group[adjacentNode] = (group[start] + 1) % 2;
                dfs(list, visited, group, adjacentNode);
            }
            // 관계가 동일하면
            else if (group[adjacentNode] == group[start]) {
                // 이론 성립 안함
                theory = false;
            }
        }
    }
}
