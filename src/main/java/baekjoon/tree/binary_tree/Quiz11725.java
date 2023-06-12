package baekjoon.tree.binary_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz11725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 노드 갯수
        int n = Integer.parseInt(br.readLine());

        // 트리 초기화
        List<Integer>[] tree = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        // 트리 생성
        for(int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            // 양방향
            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        // 트리의 루트는 1
        boolean[] visited = new boolean[n+1];
        // 부모노드를 담는 배열(index는 자식노드, 값은 부모노드)
        int[] parent = new int[n+1];

        // bfs 탐색 시작
        bfs(tree, visited, parent, 1);

        StringBuilder sb = new StringBuilder();
        for(int i = 2; i <= n; i++) {
            sb.append(parent[i]).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    private static void bfs(List<Integer>[] tree, boolean[] visited, int[] parent, int start) {
        // bfs를 사용하여 루트 노드부터 탐색을 시작한다.
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            // 부모노드의 인접 노드(자식노드)
            for(Integer node : tree[poll]) {
                // 자식노드에 방문 이력이 없으면
                if(!visited[node]) {
                    visited[node] = true;
                    // 부모노드 저장
                    parent[node] = poll;
                    queue.offer(node);
                }
            }
        }
    }
}
