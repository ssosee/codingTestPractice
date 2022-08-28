package inflearn.array;

import java.util.Scanner;

/**
 * 점수계산
 */
public class test7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] test = new int[num];
        for(int i = 0; i < num; i++) {
            test[i] = sc.nextInt();
        }
        System.out.println(new Solution().calculateScore(num, test));
    }

    static class Solution {
        public int calculateScore(int num, int[] test) {
            int result = 0;
            int cnt = 0;
            //연속적으로 k번 맞히면 k점 부여
            for(int i = 0; i < test.length; i++) {
                if(test[i] == 1) {
                    cnt++;
                    result += cnt;
                }
                else if(test[i] == 0) {
                    cnt = 0;
                }
            }

            return result;
        }
    }
}
