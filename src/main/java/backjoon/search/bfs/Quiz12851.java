package backjoon.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz12851 {
    static int minTime = Integer.MAX_VALUE;
    static int ways = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] time = new int[100001];
        bfs(time, n, k);

        StringBuilder sb = new StringBuilder();
        sb.append(minTime).append("\n");
        sb.append(ways);

        System.out.print(sb);

        br.close();
    }

    private static void bfs(int[] time, int n, int k) {
        /**
         * 중복 방문이 가능함
         * 따라서 최소 시간인지 아닌지만 체크한다.
         */
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(n); // 수빈이 위치
        time[n] = 1; // 걸린 시간

        while (!queue.isEmpty()) {
            // 수빈이가 현재 걸린 시간
            int now = queue.poll();

            // 최소 시간보다 수빈이가 걸린시간이 크면 종료
            if(minTime < time[now]) {
                return;
            }

            int[] move = {now + 1, now - 1, now * 2};
            for(int i = 0; i < 3; i++) {
                int next = move[i];

                // 범위를 벗어난 경우
                if(next < 0 || next > 100_000) continue;

                // 수빈이가 동생을 만난 경우
                if(next == k) {
                    minTime = time[now];
                    ways++;
                }

                // 첫방문 이거나, 방문한 곳이어도 같은 시간에 방문 했다면
                if(time[next] == 0 || time[next] == time[now] + 1) {
                    queue.add(next);
                    time[next] = time[now] + 1;
                }
            }
        }
    }
}
