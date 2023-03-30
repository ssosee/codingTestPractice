package backjoon.graph.topological;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz1516 {
    /**
     * 건물은 동시에 지을 수 있다.
     *
     * 5            // 건물 종류 수
     * 10 -1        // 1번 건물 짓는데 걸리는 시간(10)
     * 10 1 -1      // 2번 건물 짓는데 걸리는 시간(10), 건물 번호(1)부터 먼저 지어야함
     * 4 1 -1       // 3번 건물 짓는데 걸리는 시간(4), 건물 번호(1)부터 먼저
     * 4 3 1 -1     // 4번 건물 짓는데 걸리는 시간(4), 건물 번호(3, 1)먼저
     * 3 3 -1       // 5번 건물 짓는데 걸리는 시간(3), 건물 번호(3) 먼저
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 건물 종류 수
        int n = Integer.parseInt(br.readLine());

        // 인접 리스트
        List<Integer>[] list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        // 진입 차수(특정 건물을 짓기 전에 먼저 지어야할 건물의 갯수)
        int[] inDegree = new int[n+1];
        // 걸리는 시간(특정 건물을 짓는데 걸리는 시간)
        int[] times = new int[n+1];

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            while (true) {
                int building = Integer.parseInt(st.nextToken());

                if(building == -1) {
                    break;
                }

                // building -> i번 건물(building을 지어야 i번째 건물 짓기 가능)
                list[building].add(i);
                inDegree[i]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 1; i <= n; i++) {
            if(inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 특정 건물을 짓기 전까지 걸린 시간
        int[] result = new int[n+1];
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            // i 건물을 짓기위해서는 node 건물을 먼저 지어야 한다.
            for(Integer i : list[node]) {
                // 노드의 인접 노드의 인접 차수를 1 감소
                inDegree[i]--;
                // 인접 노드(i)의 건물을 짓기 전 노드(node)의 건물을 짓는데 걸린시간 중 최댓값
                result[i] = Math.max(result[i], result[node] + times[node]);

                if(inDegree[i] == 0) {
                    queue.offer(i);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        // 특정 건물을 짓는 데 걸린 시간을 출력.
        for (int i = 1; i <= n; i++) {
            sb.append((result[i] + times[i]) + "\n");
        }

        System.out.print(sb);
    }
}
