package inflearn.arrayalgorithm;

/**
 * 연속 부분 수열
 */
import java.util.*;

public class test4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int sumResult = sc.nextInt();
        int [] arr = new int[num];
        for(int i = 0; i < num; i++) {
            arr[i] = sc.nextInt();
        }
        //System.out.println(new Solution().getNumber(num, sumResult, arr));
        System.out.println(new Solution().solution(num, sumResult, arr));
    }
    static class Solution {

        public int solution(int num, int sumResult, int[] arr) {
            int answer = 0;
            int sum = 0;
            int lt = 0;
            for(int rt = 0; rt < num; rt++) {
                sum += arr[rt];
                if(sum == sumResult) {
                    answer++;
                }
                //같아도 빼야함
                while (sum >= num) {
                    sum -= arr[lt++];
                    if(sum == num) answer++;
                }
            }

            return answer;
        }

        public int getNumber(int num, int sumResult, int[] arr) {
            int answer = 0;
            int sum = 0;
            int pi = 0;
            int i = 0;
            while(pi < num) {
                sum += arr[pi];
                if(sum > sumResult) {
                    pi = i++;
                    sum = 0;
                }
                else if (sum == sumResult) {
                    answer++;
                }
                pi++;
            }
            return answer;
        }
    }
}
