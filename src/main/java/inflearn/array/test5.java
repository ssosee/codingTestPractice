package inflearn.array;

import java.util.*;
/**
 * 소수(에라토스테네스 체)
 */
public class test5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        Solution solution = new Solution();
        System.out.println(solution.solution(input));
    }

    static class Solution {
        public int solution(int input) {
            int answer = 0;
            int[] ch = new int[input+1]; //인덱스번호는 0부터 시작이므로 1을 더한다.
            //소수는 2부터 시작
            for(int i = 2; i <=input; i++) {
                if(ch[i] == 0) {
                    answer++; //소수
                    //j는 i의 배수로 증가
                    for(int j = i; j <= input; j = j+i) {
                        //소수의 배수인 것을 1로 체킹
                        ch[j] = 1;
                    }
                }
            }

            return answer;
        }
    }
}
