package programmers.levle1;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/77484">
 *     로또의 최고순위와 최저순위</a>
 */
public class Lotto {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] result = solution.solution(new int[]{44, 1, 0, 0, 31, 25}, new int[]{31, 10, 45, 1, 6, 19});
        for(int r : result) {
            System.out.println(r);
        }
    }
    static class Solution {
        public int[] solution(int[] lottos, int[] win_nums) {
            int[] answer = new int[2];

            answer[0] = winBestPrize(lottos, win_nums);
            answer[1] = winWorstPrize(lottos, win_nums);

            return answer;
        }

        public int winBestPrize(int[] lottos, int[] win_nums) {

            int realCount = 7;

            for(int i = 0; i < lottos.length; i++) {
                for(int j = 0; j < win_nums.length; j++) {
                    if(lottos[i] == win_nums[j]) {
                        realCount--;
                        break;
                    }
                    else if(lottos[i] == 0) {
                        realCount--;
                        break;
                    }
                }
            }
            if(realCount == 7) return 6;
            return realCount;
        }

        public int winWorstPrize(int[] lottos, int[] win_nums) {
            int realCount = 7;

            for(int i = 0; i < lottos.length; i++) {
                for(int j = 0; j < win_nums.length; j++) {
                    if(lottos[i] == win_nums[j]) {
                        realCount--;
                        break;
                    }
                }
            }
            if(realCount == 7) return 6;
            return realCount;
        }
    }
}