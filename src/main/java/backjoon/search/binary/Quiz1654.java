package backjoon.search.binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] lanCable = new int[k];
        long max = Integer.MIN_VALUE;
        for(int i = 0; i < k; i++) {
            lanCable[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, lanCable[i]);
        }

        /**
         * max가 0이고 min이 0이면 이진탐색을 하지 않습니다.
         * 0 ~ max + 1의 범위를 탐색해야함
         */

        // 랜선 최대 길이 -> upperBound - 1
        long result = upperBound(lanCable, n, 0, max+1);
        System.out.print(result - 1);

    }

    /**
     * 랜선 길이를 이진탐색 한다.
     * 만들고자 하는 랜선 갯수가 target이 된다.
     * 이진탐색 중간에 랜선 갯수를 세어서 만족하는지 확인한다.
     */
    private static long upperBound(int[] lanCable, int target, long min, long max) {
        while (min < max) {
            long count = 0;
            long mid = min + (max - min) / 2;
            // 중간 길이로 잘라서 몇 개가 만들어지는 지
            for(int i = 0; i < lanCable.length; i++) {
                count += (lanCable[i] / mid);
            }

            if(count < target) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }
}
