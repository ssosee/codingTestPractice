package toss_assistant_algorithm_study.week3;

public class 코딩_테스트_공부 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(10, 10, new int[][]{{10, 15, 2, 1, 2}, {20, 20, 3, 3, 4}}));
        // System.out.println(solution.solution(0, 0, new int[][]{{3, 3, 2, 1, 2}, {3, 3, 3, 3, 4}}));
    }

    /**
     * <a href="https://tech.kakao.com/2022/07/13/2022-coding-test-summer-internship/">참고1</a>
     * <a href="https://sookr5416.tistory.com/128">참고2</a>
     *
     * 1. 문제에서 요구하는 알고력과 코딩력이 충족되면 문제를 해결
     * 2. 문제를 풀 수 없는 경우 알고력과 코딩력을 높인다.
     * 3. 알고력 또는 코딩력을1 올리는데 1의 시간 필요
     * 4. 문제를 해결해서 알고력과 코딩력을 얻을 수 있다.
     * 5. 같은 문제를 여러번 푸는것 가능(모든 문제를 1번 이상 풀 필요 없음)
     * 6. 모든 문제를 풀 수 있는 알고력, 코딩력에 도달하는 최단시간을 구한다.
     *
     * 아이디어
     * 6번의 모든 문제를 풀수 있다는 뜻은 최대 알고력, 코딩력을 가지는 것
     *
     * dp를 사용
     *  dp 배열
     *  i:       현재 알고력
     *  j:       현재 코딩력
     *  [i][j]:  소요시간
     *
     *  dp[maxAlp][maxCop]를 구한다.
     *
     * 시간복잡도
     *  O(N^3)
     *
     * 자료구조
     *  2차원 배열
     */
    static class Solution {
        public int solution(int alp, int cop, int[][] problems) {

            int maxAlp = Integer.MIN_VALUE;
            int maxCop = Integer.MIN_VALUE;
            // dp 사이즈를 구하기위해 최대 알고력/코딩력을 구한다.
            for(int i = 0; i < problems.length; i++) {
                maxAlp = Math.max(problems[i][0], maxAlp);
                maxCop = Math.max(problems[i][1], maxCop);
            }

            // 현재 알고력/코딩력이 최대 알고력/코딩력보다 크면 문제풀 이유가 없다.
            if(alp >= maxAlp && cop >= maxCop) return 0;

            if(alp >= maxAlp) alp = maxAlp;
            if(cop >= maxCop) cop = maxCop;

            /**
             * dp 배열
             * i:       현재 알고력
             * j:       현재 코딩력
             * [i][j]:  소요시간
             */
            int[][] dp = new int[maxAlp+1+150][maxCop+1+150];
            for(int i = alp; i <= maxAlp; i++) {
                for(int j = cop; j <= maxCop; j++) {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
            dp[alp][cop] = 0;

            for(int i = alp; i <= maxAlp; i++) {
                for(int j = cop; j <= maxCop; j++) {
                    for(int k = 0; k < problems.length; k++) {
                        int alpReq = problems[k][0];
                        int copReq = problems[k][1];
                        int alpRwd = problems[k][2];
                        int copRwd = problems[k][3];
                        int cost = problems[k][4];

                        // 문제 풀이가 가능한 경우
                        if(i >= alpReq && j >= copReq) {
                            // 목표한 알고력, 코딩력을 넘었을 경우 예방
                            int nextAlp = Math.min(maxAlp, i + alpRwd);
                            int nextCop = Math.min(maxCop, j + copRwd);
                            dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + cost);
                        }
                    }

                    // 알고력 +1, 시간 +1
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1);
                    // 코딩력 +1, 시간 +1
                    dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1);
                }
            }

            return dp[maxAlp][maxCop];
        }
    }
}
