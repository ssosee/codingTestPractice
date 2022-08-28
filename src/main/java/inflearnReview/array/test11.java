package inflearnReview.array;

import java.util.Scanner;

public class test11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int [][]array = new int[num][5];
        for(int i = 0; i < num; i++) {
            for(int j = 0; j < 5; j++) {
                array[i][j] = scanner.nextInt();
            }
        }
        System.out.println(new Solution().solution(num, array));
    }

    static class Solution {
        public int solution(int n, int [][] array) {
            int result = 0;
            int max = 0;
            //같은 반이었던 사람이 가장 많은 학생을 임시반장으로
            for(int i = 0; i < n; i++) {
                int count = 0;
                for(int j = 0; j < n; j++) {
                    for(int k = 0; k < 5; k++) {
                        if(array[i][k] == array[j][k]) {
                            count++;
                            break; //j학생을 1번만 카운팅 해야하기 때문에
                        }
                    }
                }
                if(count > max) {
                    max = count;
                    result = i;
                }
            }
            //index는 0부터 이므로 1을 더한다.
            return result+1;
        }
    }
}
