package backjoon.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz1697 {

    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[1000001];
        bfs(visited, n, k);
        System.out.println(result);

        br.close();
    }

    private static void bfs(boolean[] visited, int n, int k) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(n, 1));
        while (!queue.isEmpty()) {
            // 현재 수빈이의 위치와 탐색 깊이 조회
            Node node = queue.poll();

            int back = node.getPosition() - 1;
            int forward = node.getPosition() + 1;
            int teleport = node.getPosition() * 2;

            // 수빈이가 동생 위치를 찾으면
            if(node.getPosition() == k) {
                // depth는 1부터 시작하기 때문에 걸린시간은 1을 빼줘야함
                result = node.getDepth() - 1;
                break;
            }

            // 범위를 벗어나지 않고 뒤로 1칸 가는 수빈이
            if(back >= 0 && !visited[back]) {
                visited[back] = true;
                queue.add(new Node(back, node.getDepth()+1));
            }
            // 범위를 벗어나지 않고 앞으로 1칸 가는 수빈이
            if(forward <= 100000 && !visited[forward]) {
                visited[forward] = true;
                queue.add(new Node(forward, node.getDepth()+1));
            }
            // 범위를 벗어나지 않고 순간이동하는 수빈이
            if(teleport <= 100000 && !visited[teleport]) {
                visited[teleport] = true;
                queue.add(new Node(teleport, node.getDepth()+1));
            }
        }
    }

    static class Node {
        private int position;
        private int depth;

        public Node(int position, int depth) {
            this.position = position;
            this.depth = depth;
        }

        public int getPosition() {
            return position;
        }

        public int getDepth() {
            return depth;
        }
    }
}
