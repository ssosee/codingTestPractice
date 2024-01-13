package toss_assistant_algorithm_study.season6.week3;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class CoinChange {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.coinChange(new int[]{1, 2, 5}, 11));
        //System.out.println(solution.coinChange(new int[]{1, 2}, 2));
        //System.out.println(solution.coinChange(new int[]{1, 2, 5}, 100));
    }

    /**
     * 아이디어
     * - 코인에 있는 배열을 조합해서 가장 적은 코인을 사용해서 amount를 만들어야 함
     *
     */
    static class Solution {
        public int coinChange(int[] coins, int amount) {
            if(amount == 0) {
                return 0;
            }

            return bfs(coins, amount);
        }

        private int bfs(int[] coins, int amount) {
            Queue<Coin> queue = new ArrayDeque<>();
            queue.offer(new Coin(0, 0));
            Set<Integer> visited = new HashSet<>();

            while (!queue.isEmpty()) {
                Coin poll = queue.poll();
                if(amount == poll.amountTotal) {
                    return poll.count;
                }

                /**
                 * 중복 제거
                 * 2
                 *     2 + 2 = 4
                 *     2 + 3 = 5
                 *     2 + 5 = 7 ✅
                 * 3
                 *     3 + 2 = 5
                 *     3 + 3 = 6
                 *     3 + 5 = 8 > 7 ❌
                 * 5
                 *     5 + 2 = 7 ✅
                 *     5 + 3 = 8 > 7 ❌
                 *     5 + 5 = 10 > 7 ❌
                 */
                if(visited.contains(poll.amountTotal)) {
                    continue;
                }

                visited.add(poll.amountTotal);

                for(int i = 0; i < coins.length; i++) {
                    if(poll.amountTotal < amount) {
                        queue.offer(new Coin(poll.count + 1, poll.amountTotal + coins[i]));
                    }
                }
            }

            return -1;
        }

        static class Coin {
            private int count;
            private int amountTotal;

            public Coin(int count, int amountTotal) {
                this.count = count;
                this.amountTotal = amountTotal;
            }
        }
    }
}
