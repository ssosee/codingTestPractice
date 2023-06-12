package baekjoon.primenumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Quiz1929 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        // m 이상 n이하의 소수 출력
        /**
         * 에라토스테네스의 체
         * 1. m이상 n이하의 소수 배열을 생성
         * 2. m이 1이 아닌경우 m부터 m을 제외한 m의 배수를 제거
         * 3. 2의 과정을 n이하까지 반복
         */

        BigInteger bigInteger = new BigInteger("10");
        bigInteger.isProbablePrime(11);

        // n이하의 자연수 생성(2부터 시작)
        int[] arr = new int[n];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = i+1;
        }

        for(int i = 0; i <= Math.sqrt(arr.length); i++) {
            // 1이 아닐때만 배수 제거 작업을 수행(이미 배수로 제거된 수는 작업하지 않아도 된다.)
            if(arr[i] == 1) continue;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] % arr[i] == 0) {
                    // 배수인것을 확인하면 1로 변경
                    arr[j] = 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        // m보다 크거나 같은 소수를 출력한다.
        for(Integer i : arr) {
            if(i != 1 && i >= m) {
                sb.append(i).append("\n");
            }
        }
        System.out.print(sb);
        br.close();
    }
}
