package toss_assistant_algorithm_study.week2;

public class 두_원_사이의_쌍 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(2, 3));
    }

    static class Solution {
        public long solution(int r1, int r2) {
            long answer = 0;

            /**
             * 1. x^2 + y^2 = r^2
             * y1과 y2의 차이를 구하면 그것이 (x,y) 갯수
             */
            double r1Pow = Math.pow(r1, 2);
            double r2Pow = Math.pow(r2, 2);
            for (int x = 1; x <= r2; x++) {
                double xPow = Math.pow(x, 2);
                long y1 = (long) Math.ceil(Math.sqrt(r1Pow - xPow)); // 올림: y1은 r1보다 큼
                long y2 = (long) Math.floor(Math.sqrt(r2Pow - xPow)); // 내림: y2는 r2보다 작음

                answer += y2 - y1 + 1; // 차이를 구한다.
            }

            return answer * 4;
        }
    }
}
