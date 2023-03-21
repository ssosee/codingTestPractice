package backjoon.search.binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quiz2512 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 지방 수
        int n = Integer.parseInt(br.readLine());
        // 지방 요청 예산
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] budget = new int[n];
        long max = Long.MIN_VALUE;
        for(int i = 0; i < budget.length; i++) {
            budget[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, budget[i]);
        }
        // 총 예산(목표)
        int target = Integer.parseInt(br.readLine());

        // 예산 중 최댓값을 구하면 된다.(이때, 상한액 이하의 예산요청을 그대로 배정)
        // 최댓값을 구하는 문제이기 때문에, upperBound를 사용한다.
        long upperBound = getUpperBound(budget, max+1, target);
        System.out.print(upperBound - 1);
    }

    private static long getUpperBound(int[] budget, long max, int target) {
        long min = 0;
        while (min < max) {
            // 최대로 줄 수 있는 예산
            long mid = min + (max - min) / 2;
            // 최대로 줄 수 있는 예산 총합
            long sum = 0;
            for(int i = 0; i < budget.length; i++) {
                // 최대로 줄 수 있는 예산 > 지방 요청 예산
                if(budget[i] < mid) {
                    // 상한액 이하의 예산 요청을 그대로 배정
                    sum += budget[i];
                } else {
                    sum += mid;
                }
            }
            // 최대로 줄 수 있는 예산(sum)이 총 예산 보다 크면
            if(target < sum) {
                // 최대로 줄 수 있는 예산을 줄여야 한다.
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }
}
