package backjoon.search.binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Quiz10816_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] card = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(card);

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

    private static int getUpperBound(int[] card, int target) {
        int start = 0;
        int end = card.length;

        // start가 end보다 작을 때 까지만 실행
        // 같아지면 탈출
        while (start < end) {
            int mid = start + (end - start) / 2;

            // 목표 값보다 탐색한 값이 더 크면
            // mid가 우측에 치우쳐져 있기 때문에 좌측으로 옮겨야함
            if(target < card[mid]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    private static int getLowerBound(int[] card, int target) {
        int start = 0;
        int end = card.length;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if(target <= card[mid]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
