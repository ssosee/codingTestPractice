package backjoon.graph.union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz20040 {
    /**
     * 선 플레이어가 홀수번째
     * 후 플레이어가 짝수번째
     * 평면상에 0 ~ n-1까지 고유한 번호 n개 부여
     * 사이클을 완성하면 게임 종료 -> 노드1, 노드2의 같으면 사이클이 생성!! 게임 종료
     *
     * union 과정에서 find를 하는데 이때 find(노드1) == find(노드2) 이면 사이클이 발생한것!
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 점의 갯수
        int n = Integer.parseInt(st.nextToken());
        // 진행된 차례 수
        int m = Integer.parseInt(st.nextToken());

        // 고유번호의 대표 노드 생성
        int[] parent = new int[n];
        for(int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int point1 = Integer.parseInt(st.nextToken());
            int point2 = Integer.parseInt(st.nextToken());

            boolean isCycle = union(parent, point1, point2);
            // 사이클 발생
            if(isCycle) {
                System.out.print(i+1);
                br.close();
                return;
            }
        }
        System.out.print(0);
        br.close();
    }

    private static boolean union(int[] parent, int point1, int point2) {
        int ceoNode1 = find(parent, point1);
        int ceoNode2 = find(parent, point2);
        // 대표노드가 다르면 대표노드를 작은것으로 설정
        if(ceoNode1 != ceoNode2) {
            if(ceoNode1 > ceoNode2) {
                parent[ceoNode1] = ceoNode2;
            } else {
                parent[ceoNode2] = ceoNode1;
            }
            // 사이클 발생 X
            return false;
        }
        // 대표노드가 같으면 사이클 발생
        return true;
    }

    private static int find(int[] parent, int point) {
        if(parent[point] == point) {
            return point;
        } else {
            return parent[point] = find(parent, parent[point]);
        }
    }
}
