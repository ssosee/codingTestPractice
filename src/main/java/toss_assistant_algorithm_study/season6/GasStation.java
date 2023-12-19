package toss_assistant_algorithm_study.season6;

public class GasStation {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //System.out.println(solution.canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));
        System.out.println(solution.canCompleteCircuit(new int[]{5,1,2,3,4}, new int[]{4,4,1,5,1}));
    }

    /**
     * 아이디어
     * - 문제에서 시계방향으로 회전할 때 무조건 존재하거나 없다고 함
     * - 마지막까지 돌았는데 없으면 없는 것! 따라서 1바퀴만 돌려보면 된다.
     *
     * 자료구조
     * - 배열
     *
     * 시간복잡도
     * - O(N)
     */
    static class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int startIndex = 0;
            int sumGas = 0;
            int total = 0;

            for (int i = 0; i < gas.length; i++) {
                int restGas = gas[i] - cost[i];
                sumGas += restGas;
                total += restGas;

                if (sumGas < 0) {
                    // 초기화
                    sumGas = 0;
                    // 현재 시작점은 순환이 불가하기 때문에 다음 시작점을 저장
                    startIndex = i + 1;
                }
            }

            // 한 바퀴 돌았는데 음수이면
            if (total < 0) {
                return -1;
            }

            return startIndex;
        }
    }
}
