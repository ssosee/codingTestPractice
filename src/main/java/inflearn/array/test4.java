package inflearn.array;

import java.util.*;

/**
 * 피보나치 수열
 */
public class test4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        System.out.println(new Solution().solution(num).trim());
    }

    static class Solution {
        public String solution(int num) {
            List<Integer> result = new ArrayList<>();
            result.add(1);
            result.add(1);
            String answer = "";
            for(int i = 1; i < num; i++) {
                int sum = result.get(i - 1) + result.get(i);
                result.add(sum);
            }

            for(int i = 0; i < num; i++) {
                answer += result.get(i) +" ";
            }

            return answer;
        }
    }
}
