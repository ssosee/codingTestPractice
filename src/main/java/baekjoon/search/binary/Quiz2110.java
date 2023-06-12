package baekjoon.search.binary;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.Arrays;

public class Quiz2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 집 갯수
        int houseNum = Integer.parseInt(st.nextToken());
        // 라우터 갯수
        int target = Integer.parseInt(st.nextToken());

        int[] house = new int[houseNum];
        long max = Long.MIN_VALUE;
        for(int i = 0; i < houseNum; i++) {
            house[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, house[i]);
        }

        // [1 2 4 8 9]
        Arrays.sort(house);

        // 한 집에서는 1개의 공유기만 설치 가능
        // 가장 인접한 두 공유기 사이의 최대 거리
        long upperBound = getUpperBound(house, target, max + 1);
        System.out.print(upperBound - 1);

        br.close();
    }

    private static long getUpperBound(int[] house, long target, long max) {
        long min = 0;
        while (min < max) {
            // 임의로 정한 집 사이의 거리
            long mid = min + (max - min) / 2;
            long count = installRouter(house, mid);

            // 목표 라우터 갯수가 임의로 설치한 라우터 갯수보다 많으면
            if(target > count) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }

    private static long installRouter(int[] house, long mid) {
        int distance = 0;
        int count = 1; // house[0]는 설치
        int k = 0;
        for(int i = 1; i < house.length; i++) {
            distance = house[i] - house[k];
            // 라우터를 설치한 집으로부터 떨어진 다른 집의 거리(distance)가
            // 우리가 임의로 정한 거리보다 크거나 같을 경우
            if(distance >= mid) {
                // 라우터 설치 가능하다는 의미!!
                count++;
                // 집(i)는 라우터 설치가 완료되었다.
                // 따라서 집(i)와 인접한 집 사이의 거리를 구해야 하기때문에
                // 집의 위치(k)를 (i)로 바꿔 준다.
                k = i;
            }
        }
        return count;
    }
}