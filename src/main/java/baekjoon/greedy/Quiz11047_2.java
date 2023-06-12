package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz11047_2 {
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

        int count = 0;
        int mock = 0;
        // 가장 단위가 큰 동전부터 탐색
        for(int i = n-1; i >= 0; i--) {
            // 목표 금액에서 동전으로 나눈다.
            mock = k / coin[i];
            // 몫이 0 이면 동전(coin[i])을 사용할 수 없다.
            if(mock != 0) {
                // 몫 만큼 해당 동전을 사용한다.
                count += mock;
                // 목표 금액(k)에서 동전(coin[i])으로 나눈 나머지로 목표금액(k)을 변경한다.
                k = k % coin[i];
            }
        }
        System.out.print(count);
        br.close();
    }
}
