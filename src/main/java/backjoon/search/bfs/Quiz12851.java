package backjoon.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz12851 {
    static int minTime = Integer.MAX_VALUE;
    static int ways = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 수빈이 위치
        int n = Integer.parseInt(st.nextToken());
        // 동생 위치
        int k = Integer.parseInt(st.nextToken());

        // 수빈이가 동생보다 앞에 있으면 뒤로가기만 가능..
        if(n >= k) {
            System.out.print(n-k+"\n1");
            br.close();
            return;
        }

        // 방문(시간: 깊이) 기록 배열
        // n의 범위가 0 ~ 100_000
        int[] visited = new int[100_001];
        // bfs 탐색
        bfs(visited, n, k);

        StringBuilder sb = new StringBuilder();
        sb.append(minTime).append("\n").append(ways);
        System.out.print(sb);

        br.close();
    }

    private static void bfs(int[] visited, int n, int k) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(n); // 수빈이의 위치
        visited[n] = 1; // 방문 이력(시간, 깊이)

        while (!queue.isEmpty()) {
            // 수빈이의 현재 위치
            Integer currentPosition = queue.poll();

            // 수빈이의 현재 위치에 도달한 시간이 최소시간보다 크다면 종료
            if(visited[currentPosition] > minTime) {
                return;
            }

            int[] move = {currentPosition - 1, currentPosition + 1, currentPosition * 2};
            for(int i = 0; i < 3; i++) {

                // 수빈이가 움직여서 동생을 찾았다면
                if(move[i] == k) {
                    minTime = visited[currentPosition];
                    ways++;
                }

                // 범위 확인
                if(move[i] < 0 || move[i] > 100_000) {
                    continue;
                }

                // 수빈이가 움직인 위치에 첫 방문 이거나
                // 수빈이가 움직인 위치에 현재 위치에서 1초 안에 도달할 수 있으면
                // 수빈이가 다른 움직임으로 동일한 시간에 동생을 찾을 수 있음을 의미
                if(visited[move[i]] == 0 || visited[move[i]] == visited[currentPosition] + 1) {
                    queue.add(move[i]);
                    visited[move[i]] = visited[currentPosition] + 1;
                }

            }
        }
    }
}
