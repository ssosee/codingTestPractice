package baekjoon.primenumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Quiz4948 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        /**
         * 임의의 자연수 n에 대하여
         * n보다 크고, 2n보다 작거나 같은 소수는 적어도 1개 존재
         *
         * n보다 크고 2n 보다 작거나 같은 소수 갯수 출력
         */
        List<Integer> input = new ArrayList<>();
        while (true) {
            int n = Integer.parseInt(br.readLine());
            input.add(n);
            // 0이면 입력의 마지막
            if(n == 0) break;
        }


        for(Integer n : input) {
            // 0이면 입력의 마지막이기 때문에 탈출
            if(n == 0) break;

            int[] arr = new int[2 * n];
            for (int i = 1; i <= 2 * n; i++) {
                arr[i - 1] = i;
            }

            // 에르테네스의 체
            for (int i = 0; i <= Math.sqrt(2 * n); i++) {
                if (arr[i] == 1) continue;
                for (int j = i + 1; j < 2 * n; j++) {
                    // 나누어 떨어지면 1로 변경
                    if (arr[j] != 1 && arr[j] % arr[i] == 0) {
                        arr[j] = 1;
                    }
                }
            }

            // 소수 갯수 계산
            int count = 0;
            for (int i = 0; i < 2 * n; i++) {
                if (arr[i] != 1 && arr[i] > n) {
                    count++;
                }
            }
            sb.append(count).append("\n");
        }
        System.out.print(sb);
        br.close();
    }
}
