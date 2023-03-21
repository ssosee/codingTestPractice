package backjoon.search.binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 소유한 랜선 갯수
        int lanNum = Integer.parseInt(st.nextToken());
        // 만들고 싶은 랜선 갯수
        int target = Integer.parseInt(st.nextToken());

        // 소유한 랜선 길이 저장(랜선 길이는 최대 2^31 - 1)
        int[] lanCable = new int[lanNum];
        long max = Long.MIN_VALUE;
        for(int i = 0; i < lanNum; i++) {
            lanCable[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, lanCable[i]);
        }

        // 랜선의 최대 길이를 출력
        // 랜선의 길이를 줄이면서 목표하는 랜선 갯수를 만족하는 최대 랜선 길이를 찾아야 한다.
        // upperBound를 사용하면 된다.
        long upperBound = getUpperBound(lanCable, target, max + 1);
        System.out.print(upperBound - 1);
        br.close();
    }
    private static long getUpperBound(int[] lanCable, int target, long max) {
        long min = 0;
        while (min < max) {
            // 탐색 랜선 길이
            long mid = min + (max - min) / 2;
            // 탐색 랜선 길이로 나눈 랜선의 총 갯수
            long lanCount = 0;
            for(int i = 0; i < lanCable.length; i++) {
                lanCount += (lanCable[i] / mid);
            }
            /**
             * lanCount가 target을 초과한 첫 갯수
             * 따라서 target > lanCount
             */
            // 원하는 랜선 갯수가 탐색길이로 자른 랜선 갯수가 더 많으면
            if(target > lanCount) {
                // 탐색 랜선 길이를 너무 길게 선정함
                // 탐색 랜선 길이를 줄여보자.
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }
}
