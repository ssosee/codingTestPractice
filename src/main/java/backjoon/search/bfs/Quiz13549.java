package backjoon.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz13549 {

    static int minTime = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 수빈이 위치
        int n = Integer.parseInt(st.nextToken());
        // 동생 위치
        int k = Integer.parseInt(st.nextToken());

        // 수빈이가 동생보다 앞에 있으면 뒤로만 갈 수 있음
        if(n >= k) {
            System.out.print(n-k);
            br.close();
            return;
        }

        int[] visitedTime = new int[100_001];
        boolean[] visited = new boolean[100_001];
        bfs(visitedTime, visited, n, k);

        System.out.print(minTime);
        br.close();
    }

    private static void bfs(int[] visitedTime, boolean[] visited, int n, int k) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(n); // 수빈이의 현재 위치
        visitedTime[n] = 1; // 수빈이의 방문 시간

        while (!queue.isEmpty()) {
            Integer currentPosition = queue.poll();

            int[] move = {currentPosition - 1, currentPosition + 1, currentPosition * 2};
            for(int i = 0; i < 3; i++) {

                // 수빈이 움직여서 동생을 찾으면
                if(move[i] == k) {
                    minTime = Math.min(minTime, visitedTime[currentPosition]);
                }

                if(move[i] < 0 || move[i] > 100_000) {
                    continue;
                }

                // 방문 이력이 없으면
                if(!visited[move[i]]) {
                    queue.add(move[i]);
                    // 순간이동이면
                    if(i == 2) {
                        // 0초 소요
                        visitedTime[move[i]] = visitedTime[currentPosition];
                        // 방문하지 않은 것으로 한다.
                        visited[move[i]] = false;
                    }
                    // 순간이동이 아니면
                    else {
                        // 1초 소요
                        visitedTime[move[i]] = visitedTime[currentPosition] + 1;
                        // 방문한 것으로 한다.
                        visited[move[i]] = true;
                    }
                }
            }
        }
    }
}
