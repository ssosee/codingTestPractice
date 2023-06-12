package baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 분수찾기_1193 {
    public static void main(String[] args) throws IOException {

        /**
         *  1          2             3                   4                   5
         * (1/1), (1/2, 2/1), (3/1, 2/2, 1/3), (1/4, 2/3, 3/2, 4/1) (1/5 2/4 3/3 4/2 5/1)
         *  1      2  3(1+2)  4     5   6(3+3)   7    8    9 10(6+4) 11  12  13  14  15(10+5)
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int groupNumber = 1;
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        while (true) {
            if(n <= groupNumber + sum) {
                // 홀수 그룹이면 분모가 점점 커지고, 분자는 점점 작아진다.
                if(groupNumber % 2 == 1) {
                    /**
                     * e.g) n = 5
                     * (3 - (5 - 3 - 1)) / 5 - 3
                     */
                    int up = groupNumber - (n - sum - 1);
                    int down = n - sum;
                    sb.append(up).append("/").append(down);
                    break;
                } else {
                    /**
                     * e.g) n = 10
                     * 10 - 6 / (6 - (4 - 10 - 1))
                     */
                    int up = n - sum;
                    int down = groupNumber - (n - sum - 1);
                    sb.append(up).append("/").append(down);
                    break;
                }
            } else {
                sum += groupNumber;
                groupNumber++;
            }
        }

        System.out.print(sb);
        br.close();
    }
}
