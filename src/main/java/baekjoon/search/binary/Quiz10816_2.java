package baekjoon.search.binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quiz10816_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 카드 갯수
        int n = Integer.parseInt(br.readLine());
        // 카드 저장
        int[] card = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }

        // 카드 정렬
        Arrays.sort(card);

        // 카드 비교 군
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());
            int upperBoundValue = getUpperBound(card, target);
            int lowerBoundValue = getLowerBound(card, target);
            sb.append(upperBoundValue - lowerBoundValue).append(" ");
        }
        System.out.print(sb);
        br.close();
    }

    // 타겟의 카드가 위치한 첫번째 위치 반환
    private static int getLowerBound(int[] card, int target) {
        int start = 0;
        int end = card.length;

        // 위치가 같으면 탈출
        while (start < end) {
            // 중간 위치(탐색 위치)
            int mid = start + (end - start) / 2;

            // 타켓이 중간 위치의 값보다 작거나 같으면
            if(target <= card[mid]) {
                // 왼쪽 탐색을 해야함
                end = mid;
            }
            // 타켓이 중간 위치의 값보다 크면
            else {
                // 오른쪽 탐색을 해야함
                start = mid + 1;
            }
        }
        return start;
    }

    // 타겟의 카드가 위치한 마지막 위치 + 1 반환
    private static int getUpperBound(int[] card, int target) {
        int start = 0;
        int end = card.length;
        // 위치가 같으면 탈출
        while (start < end) {
            int mid = start + (end - start) / 2;
            // 타겟이 중간 위치의 값보다 작으면
            if(target < card[mid]) {
                // 왼쪽으로 탐색 해야함
                end = mid;
            }
            // 타겟이 중간 위치의 값보다 크거나 같으면
            else {
                // 오른쪽 탐색 해야함
                start = mid + 1;
            }
        }
        return start;
    }
}
