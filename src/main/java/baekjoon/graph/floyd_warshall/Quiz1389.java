package baekjoon.graph.floyd_warshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz1389 {

    static int INF = 987654321;

    // 모든 노드의 최단 거리를 구하면 된다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 친구 숫자
        int n = Integer.parseInt(st.nextToken());
        // 친구 관계 수
        int m = Integer.parseInt(st.nextToken());

        // 최단 거리 인접행렬 초기화
        int[][] relation = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) relation[i][j] = 0;
                else relation[i][j] = INF;
            }
        }

        // 친구관계 그래프로 표현
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int friend1 = Integer.parseInt(st.nextToken());
            int friend2 = Integer.parseInt(st.nextToken());

            // 친구 관계는 양방향
            relation[friend1][friend2] = 1;
            relation[friend2][friend1] = 1;
        }

        // 플로이드 워셜 수행
        for(int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n ; j++) {
                    relation[i][j] = Math.min(relation[i][j], relation[i][k] + relation[k][j]);
                }
            }
        }

        // 케빈 베이컨 6단계 법칙 배열 초기화
        List<Member> result = new ArrayList<>();
        // 케빈 베이컨 6단계 법칙 계산
        for(int i = 1; i <= n; i++) {
            int sum = 0;
            for(int j = 1; j <= n; j++) {
                if(relation[i][j] != INF) {
                    sum += relation[i][j];
                }
            }
            result.add(new Member(i, sum));
        }

        // 케빈 베이컨 수를 기준으로 오름차순 정렬
        Collections.sort(result, (o1, o2) -> Integer.compare(o1.getSum(), o2.getSum()));

        System.out.print(result.get(0).getNumber());
        br.close();
    }

    static class Member {
        private int number;
        private int sum;

        public Member(int number, int sum) {
            this.number = number;
            this.sum = sum;
        }

        public int getNumber() {
            return number;
        }

        public int getSum() {
            return sum;
        }
    }
}
