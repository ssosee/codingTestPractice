package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DevelopFuntion {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] progress = {93, 30, 55};
        int[] speeds = {1, 30, 5};
        solution.solution(progress, speeds);
    }

    static class Solution {
        public int[] solution(int[] progress, int[] speeds) {
            List<Integer> answer = new ArrayList<>();
            Queue<Integer> remainDayQ = new LinkedList<>();

            /**
             * Q에 개발소요 기간 저장
             */
            for (int i = 0; i < progress.length; i++) {
                remainDayQ.add((int) Math.ceil((100 - Integer.valueOf(progress[i]))/speeds[i]));
            }

            while(!remainDayQ.isEmpty()) {
                int day = remainDayQ.poll();
                int cnt = 1;

                while (!remainDayQ.isEmpty() && day >= remainDayQ.peek()) {
                    cnt++;
                    remainDayQ.poll();
                }
                answer.add(cnt);
            }
            return answer.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}
