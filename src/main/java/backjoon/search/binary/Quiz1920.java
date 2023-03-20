package backjoon.search.binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quiz1920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[n];
        for(int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 오름차순 정렬
        Arrays.sort(A);

        /**
         * 배열 A에 값이 있는지 확인
         */
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            sb.append(search(A, Integer.parseInt(st.nextToken()), 0, n-1)).append("\n");
//            sb.append(Arrays.binarySearch(A, Integer.parseInt(st.nextToken()))).append("\n");
        }
        System.out.print(sb);

        br.close();
    }

    private static int search(int[] A, int target, int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;
            // 타겟보다 값이 큰 경우
            if(target < A[mid]) {
                // 끝점을 중앙값 - 1로 조정
                // (중앙 값은 탐색하지 않아도 되기 때문)
                end = mid - 1;
            }
            // 타겟보다 값이 작은 경우
            else if(target > A[mid]) {
                // 시작점을 중앙값 + 1로 조정
                // (중앙 값은 탐색하지 않아도 되기 때문)
                start = mid + 1;
            } else {
                return 1;
            }
        }
        return 0;
    }
}
