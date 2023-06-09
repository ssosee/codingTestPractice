package backjoon2.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 분수찾기_1193 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        /**
         * groupNumber:   1           2               3                     4
         * group:       (1/1) -> (1/2, 2/1) -> (3/1, 2/2, 1/3) -> (1/4, 2/3, 3/2, 4/1) -> ...
         * sum:           1           3(1+2)            6(3+3)                  10(6+4) ... (10+5)
         */

        /**
         * 1 ~ n 까지 합은 n * (n+1) / 2
         * 누적합을 이용하여 계산하는 문제
         * 1. 몇 번째 그룹인지 찾는다.
         * 2. 그 그룹에서 몇 번째인지 찾는다.
         * 3. 홀, 짝인 경우를 고려해서 값을 찾는다.
         */

        // 몇 번째 그룹인지 찾는다.
        // e.g) n = 10
        int groupNumber = 0;
        int sum = 0;
        while (true) {
            groupNumber++; // 4
            sum = groupNumber * (groupNumber + 1) / 2; // 10
            if(n <= sum) break;
        }

        // 그룹에서 몇번째 값인지 찾는다.
        // n에서 직전 그룹까지 속한 누적합을 뺀다.
        int prevSum = (groupNumber - 1) * (groupNumber) / 2; // 6
        int groupInNumber = n - prevSum; // 10 - 6 = 4 -> 10은 4번째 그룹에서 4번째 값

        StringBuilder sb = new StringBuilder();
        // 홀수 그룹인 경우(분자의 값이 1씩 작아진다.)
        if(groupNumber % 2 == 1) {
            sb.append(groupNumber - groupInNumber + 1).append("/").append(groupInNumber);
        }
        // 짝수 그룹인 경우(분모의 값이 1씩 커진다.)
        else {
            sb.append(groupInNumber).append("/").append(groupNumber - groupInNumber + 1);
        }

        System.out.print(sb);
        br.close();
    }
}
