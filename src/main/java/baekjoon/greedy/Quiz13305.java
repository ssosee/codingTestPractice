package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz13305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 도시 갯수
        int city = Integer.parseInt(br.readLine());

        // 도로 길이
        // 편의를 위해 도로 마지막은 0을 넣음 따라서 배열의 크기를 city와 동일하게 한다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] roadLen = new long[city - 1];
        for(int i = 0; i < roadLen.length; i++) {
            roadLen[i] = Long.parseLong(st.nextToken());
        }

        // 기름 가격
        st = new StringTokenizer(br.readLine());
        long[] price = new long[city];
        for(int i = 0; i < price.length; i++) {
            price[i] = Long.parseLong(st.nextToken());
        }

        // 처음에는 무조건 기름을 넣어야 한다.
        // 기름이 비싼 곳에서 적게 넣고
        // 기름이 싼곳에 많이 넣으면 된다.
        // 1. 기름이 제일 싼곳을 찾을 때 까지 다음 주유소까지 거리만큼 기름을 넣는다.
        // 2. 기름이 제일 싼곳을 찾으면 남은 목표 거리만큼 기름을 넣는다.

        long result = 0;
        long min = price[0];
        for(int i = 0; i < roadLen.length; i++) {
            // 현재 주유소 기름값이 이전 주유소 기름값 보다 저렴하면
            if(min > price[i]) {
                // 제일 저렴한 기름값 변경
                min = price[i];
            }
            result += (min * roadLen[i]);
        }

        System.out.print(result);
        br.close();
    }
}
