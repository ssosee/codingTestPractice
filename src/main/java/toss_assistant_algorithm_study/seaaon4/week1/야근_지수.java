package toss_assistant_algorithm_study.seaaon4.week1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class 야근_지수 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(4, new int[]{4, 3, 3}));
    }
    static class Solution {

        /**
         * 아이디어
         * - 제곱의 합이 최소가 되기 위해서는 균등하게 작업량이 분포해야함
         * - 큰 작업량부터 줄이면 작업량이 균등하게 된다.
         *
         * 자료구조
         * - 우선순위큐
         *
         * 시간복잡도
         * - O(N)
         */
        public long solution(int n, int[] works) {
            long answer = 0;

            Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            for(int w : works) {
                pq.offer(w);
            }

            while (n > 0) {
                Integer work = pq.poll();
                // 작업을 줄일 수 없으면
                if(work == 0) break;
                // 작업량 감소
                work--;
                pq.offer(work);
                // 작업시간 감소
                n--;
            }

            for(int w : pq) {
                answer += (long) Math.pow(w, 2);
            }

            return answer;
        }
    }
}
