package baekjoon.graph.union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz1717 {
    /**
     *  0 ~ n이 있을때
     *  합집합 연산과, 두 원소가 같은 집합에 포함되어 있는지 확인하는 코드 작성
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 0 ~ n
        int n = Integer.parseInt(st.nextToken());
        // 연산 횟수
        int m = Integer.parseInt(st.nextToken());

        // 대표노드 배열 생성 및 초기화
        int[] ceoArr = new int[n+1]; // 0부터 n까지
        // 초기화(index를 대표노드로 설정)
        for(int i = 0; i < ceoArr.length; i++) {
            ceoArr[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int condition = Integer.parseInt(st.nextToken());
            int index1 = Integer.parseInt(st.nextToken());
            int index2 = Integer.parseInt(st.nextToken());

            if(condition == 0) {
                // union 연산
                union(ceoArr, index1, index2);
            } else if(condition == 1) {
                // find 연산
                if(find(ceoArr, index1) == find(ceoArr, index2)) {
                    sb.append("YES").append("\n");
                } else {
                    sb.append("NO").append("\n");
                }
            }
        }
        System.out.print(sb);
        br.close();
    }

    // 대표 노드를 설정 해준다.
    private static void union(int[] ceoArr, int index1, int index2) {
        // index의 대표 노드 반환
        int ceoNode1 = find(ceoArr, index1);
        int ceoNode2 = find(ceoArr, index2);

        // 대표노드가 다르면 대표노드 중 작은 값으로 대표노드로 설정
        if(ceoNode1 != ceoNode2) {
            if(ceoNode1 > ceoNode2) {
                ceoArr[ceoNode1] = ceoNode2;
            } else {
                ceoArr[ceoNode2] = ceoNode1;
            }
        }
    }

    // 대표 노드 반환
    private static int find(int[] ceoArr, int index) {
        // index의 대표노드와 index와 같으면
        if(index == ceoArr[index]) {
            // index가 대표노드!
            return index;
        }
        // index의 대표노드와 index와 다르면
        // ceoArr[index]를 index로 해서 대표 노드를 찾고
        // find 전 index의 대표노드로 갱신 해준다.
        return ceoArr[index] = find(ceoArr, ceoArr[index]);
    }
}
