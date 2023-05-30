package backjoon.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DFS와BFS_1260 {

    static int n;
    static int m;
    static int v;
    static List<Integer>[] list;
    static boolean[] visited;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        // 인접 리스트 생성
        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            // 양방향
            list[node1].add(node2);
            list[node2].add(node1);
        }

        // 내림차순 정렬
        for(int i = 1; i <= n; i++) {
            Collections.sort(list[i]);
        }

        visited = new boolean[n+1];
        visited[v] = true;
        sb = new StringBuilder();
        sb.append(v).append(" ");
        dfs(v, String.valueOf(v));

        sb.append("\n");
        visited = new boolean[n+1];
        sb.append(v).append(" ");
        bfs();

        System.out.print(sb);
        br.close();
    }

    private static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            Integer node = queue.poll();

            for(Integer n : list[node]) {
                if(!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                    sb.append(n).append(" ");
                }
            }
        }
    }

    private static void dfs(int start, String path) {
        for(Integer n : list[start]) {
            if(!visited[n]) {
                visited[n] = true;
                sb.append(n).append(" ");
                dfs(n, String.valueOf(n));
            }
        }
    }
}
