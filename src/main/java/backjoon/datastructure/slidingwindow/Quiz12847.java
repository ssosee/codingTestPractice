package backjoon.datastructure.slidingwindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 배열의 합을 구한다.
 * 2.
 */
public class Quiz12847 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 일(급여 배열의 크기)
        int n = Integer.parseInt(st.nextToken());
        // 일할수 있는 일수(윈도우 사이즈)
        int m = Integer.parseInt(st.nextToken());
        // 급여 배열
        st = new StringTokenizer(br.readLine());
        long[] money = new long[n];
        for(int i = 0; i < n; i++) {
            money[i] = Long.parseLong(st.nextToken());
        }
        // 첫 급여
        long max = 0;
        for(int i = 0; i < m; i++) {
            max += money[i];
        }

        /**
         * 슬라이딩 윈도우
         * max에서 기존의 것은 빼고, 새로운 값은 더한다.
         */
        long temp = max;
        for(int i = m; i < n; i++) {
            int j = i - m; // 3 - 3 = 0, 4 - 3 = 1
            temp = temp + money[i] - money[j];
            max = Math.max(temp, max);
        }

        System.out.println(max);
        br.close();
    }
}
