package inflearn.arrayalgorithm;

import java.util.*;

public class test6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(new Solution().getLength(n, k, arr));
    }

    static class Solution {
        public int getLength(int n, int k, int[] arr) {
            int answer = 0;
            int lt = 0;
            int cnt = 0; //0을 1로 바꾼 횟수
            /**
             * rt는 0을 1로 바꾸고
             * lt는 1을 0으로 바꾼다
             * length = rt - lt + 1
             */
            for(int rt = 0; rt < n; rt++) {
                if(arr[rt] == 0) {
                    cnt++; //0을 1로 바꿈!
                }
                while (cnt > k) {
                    if(arr[lt] == 0) {
                        cnt--; //1을 0으로 바꿈!
                    }
                    lt++;
                }
                answer = Math.max(answer, rt - lt + 1);
            }

            return answer;
        }
    }
}
