package backjoon.search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz1260 {

    static int n;
    static int m;
    static int v;
    static List<Integer>[] lists;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    // 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        lists = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            // 양방향
            lists[node1].add(node2);
            lists[node2].add(node1);
        }

        // 오름차순 정렬
        for(int i = 1; i <= n; i++) {
            Collections.sort(lists[i]);
        }

        // 방문 이력 배열 초기화
        visited = new boolean[n+1];
        visited[v] = true;
        sb.append(v).append(" ");
        // dfs 수행
        dfs(v, 0, String.valueOf(v));

        // 방문 이력 배열 초기화
        visited = new boolean[n+1];
        bfs(v);

        // 출력~
        System.out.print(sb);
        sb.setLength(0);
        br.close();
    }

    private static void bfs(int v) {
        Queue<Integer> queue = new ArrayDeque<>();
        // 초기화
        queue.offer(v);
        sb.append("\n");

        while (!queue.isEmpty()) {
            // 노드를 꺼낸다.
            Integer poll = queue.poll();
            // 방문 이력이 없으면
            if(!visited[poll]) {
                // 방문
                visited[poll] = true;
                // 경로 저장
                sb.append(poll).append(" ");

                // 인접 노드 탐색
                for (Integer node : lists[poll]) {
                    queue.offer(node);
                }
            }
        }
    }

    private static void dfs(int start, int depth, String path) {
        // 인접 노드 탐색
        for(Integer node : lists[start]) {
            // 방문 이력이 없으면
            if(!visited[node]) {
                // 방문 처리
                visited[node] = true;
                // 경로 저장
                sb.append(node).append(" ");
                // dfs 수행
                dfs(node, depth + 1, String.valueOf(node));
            }
        }
    }
}
