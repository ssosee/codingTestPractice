package channelTalk;

import java.util.HashMap;
import java.util.Map;

/**
 * SELECT
 *     p.id AS ID,
 *     p.name AS NAME,
 *     SUM(s.price) AS '금액'
 * FROM
 *     places p
 * LEFT JOIN
 *     schedules s
 * ON
 *     p.id = s.place_id
 * WHERE
 *     s.scheduled_at BETWEEN '2019-01-15' AND '2019-01-17'
 * GROUP BY
 *     p.id, p.name
 * HAVING
 *     COUNT(DISTINCT s.scheduled_at) = 3; # 3일 연속
 */
public class Test2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(3, 4, 3, new int[][]{{1,1,2}, {1,2,3}, {2,4,2}}));
    }

    static class Solution {
        public long[] solution(int n, int m, int k, int[][] acts) {
            /**
             * TYPE(1). x, p를 고르고, x행의 모든 칸을 p색으로 칠한다.
             * TYPE(2). x, p를 고르고, x열의 모든 칸을 p색으로 칠한다.
             * 3. 모든 행동이 끝난 뒤, 모든 색이 몇 개의 칸에 칠해져 있는지 확인'
             * acts = [TYPE, x, p];
             */
            long[] result = new long[k+1];
            int[][] map = new int[n+1][m+1];

            for(int i = 0; i < acts.length; i++) {
                int type = acts[i][0];
                int x = acts[i][1];
                int p = acts[i][2];

                // x행의 모든 칸을 p색으로 칠한다.
                if(type == 1) {
                    for(int j = 1; j <= m; j++) {
                        if(map[x][j] != p) {
                            result[p]++;
                            result[map[x][j]]--;
                        }
                        map[x][j] = p;
                    }
                }
                // x열의 모든 칸을 p색으로 칠한다.
                else {
                    for(int j = 1; j <= n; j++) {
                        if(map[j][x] != p) {
                            result[p]++;
                            result[map[j][x]]--;
                        }
                        map[j][x] = p;
                    }
                }
            }

            long[] answer = new long[k];
            for(int i = 0; i < k; i++) {
                answer[i] = result[i+1];
            }

            return answer;
        }
    }
}
