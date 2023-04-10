package backjoon.tree.binary_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz11725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 인접 리스트
        List<Integer>[] list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            // 양방향
            list[node1].add(node2);
            list[node2].add(node1);
        }

        boolean[] visited = new boolean[n+1];
        int[] parent = new int[n+1];
        bfs(list, visited, parent, 1);

        StringBuilder sb = new StringBuilder();
        for(int i = 2; i <= n; i++) {
            sb.append(parent[i]).append("\n");
        }

        System.out.print(sb);
        sb.setLength(0);
        br.close();
    }

    private static void bfs(List<Integer>[] list, boolean[] visited, int[] parent, int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            for (Integer i : list[poll]) {
                // 인접 노드에 방문한 이력이 없으면
                if(!visited[i]) {
                    visited[i] = true;
                    queue.offer(i);
                    // 부모 노드 저장
                    parent[i] = poll;
                }
            }
        }
    }
}
