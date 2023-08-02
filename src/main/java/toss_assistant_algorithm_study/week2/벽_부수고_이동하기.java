package toss_assistant_algorithm_study.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 벽_부수고_이동하기 {

    /**
     * 1. 아이디어
     *  dfs를 이용하여 x벽을 1개씩 부셨을 때의 최단거리를 모두 계산한다.
     *
     * 2. 시간복잡도
     *
     * 3. 자료구조
     */

    static int n;
    static int m;
    static int[] moveX = {1, -1, 0 ,0};
    static int[] moveY = {0, 0, 1 ,-1};
    static int[][] map;
    static boolean[][] visited;
    static int min = Integer.MAX_VALUE;
    static List<Integer> results = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n+1][m+1];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for(int j = 1; j <= m; j++) {
                map[i][j] = str.charAt(j-1) - '0';
            }
        }

        // 벽을 안부순 경우
        int bfs = bfs();
        // 벽 1개 부순경우
        dfs(0);

        results.add(bfs);
        for(Integer i : results) {
            if(i != -1) {
                min = Math.min(i, min);
            }
        }

        if(min == Integer.MAX_VALUE) {
            System.out.print(-1);
            br.close();
            return;
        }

        System.out.print(min);
        br.close();
    }

    private static void dfs(int wall) {

        // 벽 1개 부셨을 경우
        if(wall == 1) {
            results.add(bfs());
            return;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(map[i][j] == 1) {
                    map[i][j] = 0; // 벽뚫기
                    dfs(wall + 1);
                    map[i][j] = 1; // 벽뚫은거 원복
                }
            }
        }
    }

    private static int bfs() {
        visited = new boolean[n+1][m+1];
        visited[1][1] = true;

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(1, 1, 1));

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            int x = poll.x;
            int y = poll.y;
            int count = poll.count;

            if(x == n && y == m) {
                return count;
            }

            for(int i = 0 ; i < 4; i++) {
                int newX = x + moveX[i];
                int newY = y + moveY[i];

                // 범위체크
                if(newX < 1 || newY < 1 || newX > n || newY > m) continue;

                if(!visited[newX][newY] && map[newX][newY] == 0) {
                    visited[newX][newY] = true;
                    queue.offer(new Node(newX, newY, count + 1));
                }
            }
        }

        return -1;
    }

    static class Node {
        private int x;
        private int y;
        private int count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
