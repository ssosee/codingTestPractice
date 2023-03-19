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
        // 수빈이의 위치
        int n = Integer.parseInt(st.nextToken());
        // 동생의 위치
        int k = Integer.parseInt(st.nextToken());

        // 수빈이가 동생보다 앞에 있으면 뒤로만 갈 수 있음
        // 뒤로 1칸 이동 1초 소요
        if(n >= k) {
            System.out.print(n-k);
            br.close();
            return;
        }

        // 방문 이력 배열(시간(깊이)도 같이 표시)
        // n의 범위가 0 ~ 100_000 -> 100_001개
        int[] visited = new int[100_001];
        bfs(visited, n, k);
        System.out.print(result);
        br.close();
    }

    private static void bfs(int[] visited, int n, int k) {
        Queue<Integer> queue = new ArrayDeque<>();
        // 수빈이의 위치 큐에 삽입
        queue.add(n);
        // 수빈이의 위치 방문 표시
        visited[n] = 1;

        while (!queue.isEmpty()) {
            // 수빈이의 현재 위치
            Integer currentPosition = queue.poll();
            // 수빈이가 움직일 수 있는 방법
            int[] move = {currentPosition - 1, currentPosition + 1, currentPosition * 2};
            for (int i = 0; i < 3; i++) {
                // 수빈이가 움직였는데 동생을 만날 수 있으면
                if(move[i] == k) {
                    result = visited[currentPosition];
                    return;
                }

                // 범위 확인
                if(move[i] < 0 || move[i] > 100_000) {
                    continue;
                }

                // 방문 이력이 없으면
                if(visited[move[i]] == 0) {
                    queue.add(move[i]);
                    // 시간(깊이)을 1 증가
                    visited[move[i]] = visited[currentPosition] + 1;
                }
            }
        }
    }
}
