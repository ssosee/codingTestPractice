package backjoon.search.binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quiz10815 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] card = new int[n];
        for(int i = 0; i < n; i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }

        // card 오름차순 정렬
        Arrays.sort(card);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            sb.append(search(card, Integer.parseInt(st.nextToken()), 0, n-1)).append(" ");
        }
        System.out.println(sb);
    }

    private static int search(int[] card, int target, int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;
            int midValue = card[mid];
            // 타겟보다 중앙값이 크면
            if(target < midValue) {
                end = mid - 1;
            }
            // 타겟보다 중앙값이 작으면
            else if(target > midValue) {
                start = mid + 1;
            } else {
                return 1;
            }
        }
        return 0;
    }
}
