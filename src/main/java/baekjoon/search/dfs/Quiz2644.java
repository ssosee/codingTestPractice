package baekjoon.search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz2644 {
    static int n;
    static int member1;
    static int member2;
    static int m;
    static List<Integer>[] list;
    // 촌수
    static int relation = -1;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        /**
         * 9        // 전체 사람수
         * 7 3      // 촌수를 계산해야하는 사람
         * 7        // 부모 자식들 간의 관계 갯수
         * 1 2      // 부모 자식간의 번호(서로 1촌)
         * 1 3
         * 2 7
         * 2 8
         * 2 9
         * 4 5
         * 4 6
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 노드 갯수
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        member1 = Integer.parseInt(st.nextToken());
        member2 = Integer.parseInt(st.nextToken());

        // 간선 갯수
        m = Integer.parseInt(br.readLine());

        // 인접 리스트 초기화
        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int member1 = Integer.parseInt(st.nextToken());
            int member2 = Integer.parseInt(st.nextToken());

            // 양방향
            list[member1].add(member2);
            list[member2].add(member1);
        }

        // 탐색 초기화
        visited = new boolean[n+1];
        visited[member1] = true;
        dfs(0, member1);

        System.out.print(relation);
        br.close();
    }

    private static void dfs(int depth, int member) {

        // 내가 찾는 사람이 나오면
        if(member == member2) {
            // 촌수 계산
            relation = depth;
            return;
        }

        for(Integer findMember : list[member]) {
            if(!visited[findMember]) {
                // 방문
                visited[findMember] = true;
                // 촌수 +1 증가
                dfs(depth + 1, findMember);
            }
        }
    }
}
