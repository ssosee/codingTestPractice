package channelTalk;
import java.util.*;

public class Test22 {
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

            long[][] map = new long[n+1][m+1];
            for(int i = 0; i < acts.length; i++) {
                int type = acts[i][0];
                int x = acts[i][1];
                int p = acts[i][2];

                // x행의 모든 칸을 p색으로 칠한다.
                if(type == 1) {
                    for(int j = 1; j <= m; j++) {
                        map[x][j] = p;
                    }
                }
                // x열의 모든 칸을 p색으로 칠한다.
                else {
                    for(int j = 1; j <= n; j++) {
                        map[j][x] = p;
                    }
                }
            }

            Map<Long, Long> colorMap = new HashMap<>();
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= m; j++) {
                    long color = map[i][j];
                    if(color != 0L) {
                        colorMap.put(color, colorMap.getOrDefault(color, 0L) + 1L);
                    }
                }
            }

            long[] answer = new long[k];
            for(int i = 1; i <= answer.length; i++) {
                if (colorMap.get((long) i) == null) {
                    answer[i-1] = 0L;
                } else {
                    answer[i-1] = colorMap.get((long) i);
                }
            }

            return answer;
        }
    }
}
