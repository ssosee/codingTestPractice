package inflearn.arrayalgorithm.twopointers;

import java.util.Scanner;

public class test5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //System.out.println(new Solution().getCount(n));
        System.out.println(new Solution().getCount2(n));
    }

    static class Solution {

        //몫과 나머지
        public int getCount3(int n) {
            int answer = 0;
            int cnt = 1; //연속된 자연수 갯수
            n--; //1감소
            while (n>0) {
                cnt++;
                n = n - cnt;
                //연속된 자연수 가능한지
                if(n % cnt == 0) {
                    answer++;
                }
            }
            return answer;
        }

        public int getCount2(int n) {
            int m = n/2+1;
            int[] arr = new int[m];
            int sum = 0;
            int answer = 0;
            int lt = 0;

            for(int i = 0; i < m; i++) {
                arr[i] = i+1;
            }
            for(int rt = 0; rt < m; rt++) {
                sum += arr[rt];
                if(sum == n) {
                    answer++;
                }
                while (sum >= n) {
                    sum -= arr[lt++];
                    if(sum == n) {
                        answer++;
                    }
                }
            }
            return answer;
        }

        public int getCount(int n) {
            int answer = 0;
            int sum = 0;
            int i = 1;
            int j = 1;
            while(i < n) {
                sum += i;
                i++;
                if(sum == n) {
                    sum = 0;
                    answer++;
                    i = ++j;
                } else if(sum > n) {
                    sum = 0;
                    i = ++j;
                }
            }
            return answer;
        }
    }
}
