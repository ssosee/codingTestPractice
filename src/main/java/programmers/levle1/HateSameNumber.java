package programmers.levle1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12906">같은 숫자는 싫어</a>
 */
public class HateSameNumber {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 1, 3, 3, 0, 1, 1}));
    }

    public static int[] solution(int[] arr) {

        int init = 1000;
        List<Integer> temp = new ArrayList<>();

        for(int number : arr) {
            if(init != number) {
                temp.add(number);
            }
            init = number;
        }

        int[] answer = new int[temp.size()];
        for(int i =0; i<answer.length; i++) {
            answer[i] = temp.get(i);
        }

        return answer;
    }
}