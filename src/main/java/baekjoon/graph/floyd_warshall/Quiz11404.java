package baekjoon.graph.floyd_warshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz11404 {
    static int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 도시 갯수
        int n = Integer.parseInt(br.readLine());
        // 버스 갯수
        int m = Integer.parseInt(br.readLine());

        // 최단 거리 저장 배열
        long[][] result = new long[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <=n; j++) {
                if(i == j) result[i][j] = 0;
                else result[i][j] = INF;
            }
        }

        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
            // 비용이 저렴한 것으로 저장한다.
            result[start][end] = Math.min(result[start][end], weight);
        }

        // 플로이드 워셜 수행
        for(int k = 1; k <= n; k++) {
            for (int s = 1; s <= n; s++) {
                for (int e = 1; e <= n; e++) {
                    // result[s][k] + result[k][e] 연산시 overFlow 주의!
                    result[s][e] = Math.min(result[s][e], result[s][k] + result[k][e]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <=n; j++) {
                // i에서 j로 가지 못하는 경우 INF 이기 때문에 0을 출력
                if(result[i][j] == INF) sb.append(0).append(" ");
                else {
                    sb.append(result[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}
