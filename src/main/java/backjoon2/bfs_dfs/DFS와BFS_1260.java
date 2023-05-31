package backjoon2.bfs_dfs;

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

        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            list[node1].add(node2);
            list[node2].add(node1);
        }

        // 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문
        for(int i = 1; i <= n; i++) {
            Collections.sort(list[i]);
        }

        sb = new StringBuilder();
        visited = new boolean[n+1];
        visited[v] = true;
        sb.append(v).append(" ");
        dfs(v);

        visited = new boolean[n+1];
        sb.append("\n");
        sb.append(v).append(" ");
        bfs();

        System.out.print(sb);
        br.close();
    }

    private static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            for(Integer n : list[poll]) {
                if(!visited[n]) {
                    visited[n] = true;
                    sb.append(n).append(" ");
                    queue.offer(n);
                }
            }
        }
    }

    private static void dfs(int start) {
        for(Integer n : list[start]) {
            if(!visited[n]) {
                visited[n] = true;
                sb.append(n).append(" ");
                dfs(n);
            }
        }
    }


}
