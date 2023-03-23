package backjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Quiz11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 동전 종류
        int n = Integer.parseInt(st.nextToken());
        // 동전으로 만들어야 하는 금액
        int k = Integer.parseInt(st.nextToken());
        // 동전 배열
        Integer[] coin = new Integer[n];
        for(int i = 0; i < coin.length; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        // 내림 차순으로 정렬
        Arrays.sort(coin, Comparator.reverseOrder());

        int count = 0;
        int sum = 0;
        for(int i = 0; i < coin.length; i++) {
            if(coin[i] > k) {
                continue;
            }

            // 동전으로 만든 금액의 합이 요구된 금액보다 작거나 같을 경우
            if(sum <= k) {
                // 동전으로 금액을 만든다.
                sum += coin[i];
                // 동전으로 만든 금액의 합이 요구된 금액보다 클 경우
                if(sum > k) {
                    // 방금 동전으로 만든 금액을 빼준다.
                    sum -= coin[i];
                } else {
                    // 같은 동전으로 또 사용 해본다.
                    i--;
                    // 동전을 사용했으니 count를 증가 시킨다.
                    count++;
                }
            }
        }
        System.out.print(count);
        br.close();
    }
}
