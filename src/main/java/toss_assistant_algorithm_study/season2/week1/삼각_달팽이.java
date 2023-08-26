package toss_assistant_algorithm_study.season2.week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 삼각_달팽이 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(4));
    }

    static class Solution {

        /**
         * 아이디어
         *  1. 아래로 내려가는 경우 x좌표가 증가
         *  2. 오른쪽으로 가는 경우 y좌표가 증가
         *  3. 대각선으로 올라가는 경우 x, y좌표 증가
         *  4. 2차원 배열을 사용하기 때문에 2중 loop를 사용
         *  5. step의 x,y조건에 따라 숫자를 채운다.
         *
         * 자료구조
         *  int[][]
         *
         * 시간복잡도
         *  O(N^2)
         */
        public int[] solution(int n) {
            List<Integer> answer = new ArrayList<>();
            int[][] map = new int[n+1][n+1];

            int x = 0;
            int y = 0;
            int num = 1;
            for (int step = 0; step < n; step++) {
                for (int i = step; i < n; i++) {
                    // x좌표 증가(아래)
                    if (step % 3 == 0) {
                        x++;
                    }
                    // y좌표 증가(오른쪽)
                    else if (step % 3 == 1) {
                        y++;
                    }
                    // x, y 좌표 감소(대각선)
                    else {
                        x--;
                        y--;
                    }
                    map[x][y] = num++;
                }
            }

            // map[i][j]가 0인 경우를 제외한다.
            for(int i = 0; i <= n; i++) {
                for(int j = 0; j <= n; j++) {
                    if(map[i][j] == 0)
                        break;
                    answer.add(map[i][j]);
                }
            }

            return answer.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}
