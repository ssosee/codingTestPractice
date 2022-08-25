package programmers.levle1;

import java.util.ArrayList;
import java.util.List;

/**
 *<a href="https://school.programmers.co.kr/learn/courses/30/lessons/42840">모의고사</a>
 */
public class Test {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new int[] {1,2,3,4,5});
    }
    static class Solution {
        public int[] solution(int[] answers) {
            int[] answer = {};
            int score = 0;

            MathMaster mathMaster1 = new MathMaster(new int[]{1, 2, 3, 4, 5}, 0);
            MathMaster mathMaster2 = new MathMaster(new int[]{2, 1, 2, 3, 2, 4, 2, 5}, 0);
            MathMaster mathMaster3 = new MathMaster(new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5}, 0);

            for (int i = 0; i < answers.length; i++) {
                if (answers[i] == mathMaster1.getAnswer()[i % 5]) {
                    mathMaster1.increcentScore();
                }
                if (answers[i] == mathMaster2.getAnswer()[i % 8]) {
                    mathMaster2.increcentScore();
                }
                if (answers[i] == mathMaster3.getAnswer()[i % 10]) {
                    mathMaster3.increcentScore();
                }
            }
            List<Integer> answerTemp = new ArrayList<>();
            int max = Math.max(Math.max(mathMaster1.getScore(), mathMaster2.getScore()), mathMaster3.getScore());
            if (max == mathMaster1.getScore()) answerTemp.add(1);
            if (max == mathMaster2.getScore()) answerTemp.add(2);
            if (max == mathMaster3.getScore()) answerTemp.add(3);

            answer = new int[answerTemp.size()];
            for (int i = 0; i < answer.length; i++) {
                answer[i] = answerTemp.get(i);
            }

            return answer;
        }
    }

    static class MathMaster {
        private int[] answer = {};
        private int score;

        public MathMaster(int[] answer, int score) {
            this.answer = answer;
            this.score = score;
        }

        public int[] getAnswer() {
            return answer;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public void increcentScore() {
            this.score++;
        }
    }
}
