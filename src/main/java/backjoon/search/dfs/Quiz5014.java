package backjoon.search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz5014 {
    /**
     * F  S G  U D
     * 10 1 10 2 1
     *
     * S 층에서 G층으로 가기위해 눌러야하는 버튼 수의 최솟값
     * U층 위로, D층 아래로
     */

    static int f;
    static int s;
    static int g;
    static int u;
    static int d;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        visited = new boolean[f+1];
        // dfs(0, s); // 시간초과
        // 엘리베이터로 이동 불가하면
//        if(min == Integer.MAX_VALUE) {
//            System.out.print("use the stairs");
//            br.close();
//            return;
//        }

        // bfs 수행
        min = bfs(s);
        if(min == - 1) {
            System.out.print("use the stairs");
            br.close();
            return;
        }

        System.out.print(min);
        br.close();
    }

    // 시간초과...
    private static void dfs(int depth, int now) {

        if(now > f || now <= 0) return;

        // g층에 도착하면
        if(g == now) {
            min = Math.min(min, depth);
            return;
        }

        // 방문 이력이 없으면
        if(!visited[now]) {
            // 방문
            visited[now] = true;
            // u만큼 올라가기
            dfs(depth + 1, now + u);
            // d만큼 내리기
            dfs(depth + 1, now - d);
            // 방문 이력 삭제(모든 노드를 탐색하기 위해)
            visited[now] = false;
        }
    }

    private static int bfs(int now) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(now, 0));

        while (!queue.isEmpty()) {
            Node poll = queue.poll();

            // 범위 체크
            if(poll.getNode() > f || poll.getNode() <= 0) continue;

            // g층에 도착하면
            if(poll.getNode() == g) {
                return poll.getDepth();
            }

            // 방문 이력이 없으면
            if(!visited[poll.getNode()]) {
                // 방문
                visited[poll.getNode()] = true;
                // u만큼 올라가기
                queue.offer(new Node(poll.getNode() + u, poll.getDepth() + 1));
                // d만큼 내려가기
                queue.offer(new Node(poll.getNode() - d, poll.getDepth() + 1));
            }
        }

        return -1;
    }

    static class Node {
        private int node;
        private int depth;

        public Node(int node, int depth) {
            this.node = node;
            this.depth = depth;
        }

        public int getNode() {
            return node;
        }

        public int getDepth() {
            return depth;
        }
    }
}
