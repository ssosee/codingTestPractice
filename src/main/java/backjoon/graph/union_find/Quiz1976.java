package backjoon.graph.union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz1976 {
    /**
     * 3        // 도시의 수
     * 3        // 여행예정 도시 수
     * 0 1 0    // 1번 도시는 2번 도시만 연결
     * 1 0 1    // 2번 도시는 1, 3번 도시만 연결
     * 0 1 0    // 3번 도시는 2번 도시와 연결
     * 1 2 3    // 여행 계획 도시번호
     */

    public static void main(String[] args) throws IOException {
        /**
         * 유니온 파인드를 이용해서 대표노드를 설정한다.
         * 대표노드가 같으면 여행 계획이 가능하다!
         *
         * 왜냐하면, 대표노드가 같다는 것은
         * 선택한 여행지(노드1)에서 다른 여행지(노드2)는 대표 노드를 통하면 갈 수 있다는 의미
         * 즉, 대표노드가 같으면 노드끼리 서로 연결되어 있다는 의미!!
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 도시 수
        int n = Integer.parseInt(br.readLine());
        // 여행 예정 도시 수
        int m = Integer.parseInt(br.readLine());

        // 도시는 1번부터 시작
        int[] arr = new int[n+1];
        // 대표 도시 배열 생성, 초기화
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        // i 번째 도시
        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // j 번째 도시
            for(int j = 1; j <= n; j++) {
                int comm = Integer.parseInt(st.nextToken());
                // i번째 도시와 j번째 도시가 같지 않은 경우만 고려하면 된다.
                if(i != j) {
                    if(comm == 1) {
                        // 연결관계를 보고 대표노드 설정
                        union(arr, i, j);
                    }
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 여행계획 도시
        int[] cityTour = new int[m];
        for(int i = 0; i < m; i++) {
            cityTour[i] = Integer.parseInt(st.nextToken());
        }

        // 첫 번째 여행계획 도시의 대표노드
        int ceoNode = find(arr, cityTour[0]);
        for(int i = 1; i < m; i++) {
            // 첫 번째 여행계획 도시의 대표노드와 i번째 여행가능 도시의 대표노드가 다르면
            if(ceoNode != find(arr, cityTour[i])) {
                // 여행계획으로 여행 불가!
                System.out.print("NO");
                br.close();
                return;
            }
        }

        // 여행 가능^^
        System.out.print("YES");
        br.close();
    }

    // 대표 노드 설정
    private static void union(int[] arr, int i, int j) {
        int ceoNode1 = find(arr, i);
        int ceoNode2 = find(arr, j);

        // 값이 작은 값으로 대표노드 설정
        if(ceoNode1 != ceoNode2) {
            if(ceoNode1 > ceoNode2) {
                arr[ceoNode1] = ceoNode2;
            } else {
                arr[ceoNode2] = ceoNode1;
            }
        }
    }

    // 대표 노드 반환
    private static int find(int[] arr, int node) {
        // index와 node값이 같으면
        if(arr[node] == node) {
            // index가 곧 대표노드
            return node;
        }
        return arr[node] = find(arr, arr[node]);
    }
}
