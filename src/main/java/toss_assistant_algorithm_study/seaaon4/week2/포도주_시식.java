package toss_assistant_algorithm_study.seaaon4.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href="https://iseunghan.tistory.com/341">참고</a>
 */
public class 포도주_시식 {

    /**
     * 아이디어
     * - 와인을 마실 수 있는 경우의 수
     *  - OOX
     *  - OXO
     *  - XOO
     *
     * - 다이나믹프로그래밍
     * - 아래 3가지 경우 중 최댓값을 저장
     *  - OOX: dp[i-1]
     *  - OXO: dp[i-2] + wines[i]
     *  - XOO: dp[i-3] + wines[i] + wines[i+1]
     *
     *  [6, 10, 13, 9, 8, 1]
     *  dp[0] = 6   (OXX)
     *  dp[1] = 16  (OOX)
     *  dp[2] = 19  (OXO)
     *  dp[3] = OXO | OOX O | OXX OO 중 최대 = OXX OO
     *  dp[4] = OXX OO | OXO O | OOX OO 중 최대 = OOX OO
     *  dp[5] = OOX OO | OXX OO O | OXO XOO 중 최대 = OOXOO
     *
     *  dp[3] = dp[2] | dp[1] + wines[4] | dp[0] + wines[3] + wines[4]
     *        -> 19, 16+9, 6+13+9
     *        = 28
     *
     *  dp[4] = dp[3] | dp[2] + wines[5] | dp[1] + wines[4] + wines[5]
     *        -> 28, 19 + 8, 16 + 9 + 8
     *        = 33
     *
     *  dp[5] = dp[4] | dp[3] + wines[6] | dp[2] + wines[5] + wines[6]
     *        -> 33, 28 + 1, 19 + 8 + 1
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
        int[] wines = new int[N + 1];
        int[] dp = new int[N];

        for(int i = 1; i < N+1; i++) {
            wines[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 0; i < N; i++) {
            if(i == 0) {
                dp[0] = wines[1];
                continue;
            }
            else if(i == 1) {
                dp[1] = wines[1] + wines[2];
                continue;
            }
            else if(i == 2) {
                dp[2] = Math.max(dp[1], Math.max(wines[1] + wines[3], wines[2] + wines[3]));
                continue;
            }
            dp[i] = Math.max(dp[i-1], Math.max(dp[i-2] + wines[i+1], dp[i-3] + wines[i] + wines[i+1]));
        }

        System.out.println(dp[N-1]);
        br.close();
    }
}

