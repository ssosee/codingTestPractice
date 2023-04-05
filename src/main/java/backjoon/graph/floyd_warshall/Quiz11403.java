package backjoon.graph.floyd_warshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Quiz11403 {
    static int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 정점 갯수
        int n = Integer.parseInt(br.readLine());

        // 최단 거리 인접 행렬 초기화
        int[][] map = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                map[i][j] = INF;
            }
        }

        // 그래프 생성
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                int road = Integer.parseInt(st.nextToken());
                if(road == 1) {
                    map[i][j] = road;
                }
            }
        }

        // 플로이드 워셜 수행
        for(int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int value = map[i][j];
                if(value == INF) sb.append(0).append(" ");
                else sb.append(1).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }


}
