package toss_assistant_algorithm_study.seaaon4.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href="https://velog.io/@yanghl98/%EB%B0%B1%EC%A4%80-2156-%ED%8F%AC%EB%8F%84%EC%A3%BC-%EC%8B%9C%EC%8B%9D">참고</a>
 */
public class 포도주_시식 {

    /**
     * 아이디어
     * - 와인을 마실 수 있는 경우의 수
     *  - OOX
     *  - OXO
     *  - XOO
     *
     * - 다이나믹 프로그래밍
     * - 아래 3가지 경우
     *  - OOX: wines[1] + wines[2]
     *  - OXO: wines[1] + wines[3]
     *  - XOO: wines[2] + wines[3]
     *
     *  [6, 10, 13, 9, 8, 1]
     *  dp[1] = 6   (OXX)
     *  dp[2] = 16  (OOX)
     *  dp[3] = OOX | OXO | XOO 중 최대 = XOO
     *  dp[4] = XOO | OOXO | OXOO 중 최대 = OXOO
     *  dp[5] = OXXOO | XOOXO | OOXOO 중 최대 = OOXOO
     *  dp[6] = OOXOO | OXOOXO | XOOXOO 중 최대 = OOXOO
     *
     *  dp[4] = dp[3] | dp[2] + wines[4] | dp[1] + wines[3] + wines[4]
     *        -> 23, 16+9, 6+13+9
     *        = 28
     *
     *  dp[5] = dp[4] | dp[3] + wines[5] | dp[2] + wines[4] + wines[5]
     *        -> 28, 23 + 8, 16 + 9 + 8
     *        = 33
     *
     *  dp[6] = dp[5] | dp[4] + wines[6] | dp[3] + wines[5] + wines[6]
     *        -> 33, 28 + 1, 23 + 8 + 1
     *        = 33
     *
     * 자료구조
     * - 2차원 배열
     *
     * 시간복잡도
     * - O(N)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] wines = new int[N+1];
        int[] dp = new int[N+1];

        for(int i = 1; i < N+1; i++) {
            wines[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 1; i <= N; i++) {
            if(i == 1) {
                dp[1] = wines[1];
                continue;
            }
            else if(i == 2) {
                dp[2] = wines[1] + wines[2];
                continue;
            }
            else if(i == 3) {
                dp[3] = Math.max(dp[2], Math.max(wines[1] + wines[3], wines[2] + wines[3]));
                continue;
            }
            dp[i] = Math.max(dp[i-1], Math.max(dp[i-2] + wines[i], dp[i-3] + wines[i-1] + wines[i]));
        }

        System.out.println(dp[N]);
        br.close();
    }
}

