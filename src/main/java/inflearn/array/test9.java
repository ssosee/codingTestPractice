package inflearn.array;

import java.util.*;
/**
 * 격자판 최대합
 */
public class test9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[][] matrix = new int[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        Solution solution = new Solution();
        int max = Math.max(solution.maxColumnSum(matrix), Math.max(solution.maxRowSum(matrix), solution.maxDiagonal(matrix)));
        System.out.println(max);
    }

    static class Solution {
        public int maxRowSum(int[][] matrix) {
            int sum = 0;
            int max = 0;
            for(int i = 0; i < matrix.length; i++) {
                sum = 0;
                for(int j = 0; j < matrix.length; j++) {
                    sum += matrix[i][j];
                }
                if(sum > max) {
                    max = sum;
                }
            }

            return max;
        }

        public int maxColumnSum(int[][] matrix) {
            int sum = 0;
            int max = 0;
            for(int i = 0; i < matrix.length; i++) {
                sum = 0;
                for(int j = 0; j < matrix.length; j++) {
                    sum += matrix[j][i];
                }
                if(sum > max) {
                    max = sum;
                }
            }

            return max;
        }

        public int maxDiagonal(int[][] matrix) {
            int sum1 = 0;
            int sum2 = 0;
            int max = 0;
            for(int i = 0; i < matrix.length; i++) {
                sum1 += matrix[i][i];
            }
            for(int i = 0; i < matrix.length; i++) {
                sum2 += matrix[i][matrix.length-i-1];
            }

            return Math.max(sum1, sum2);
        }
    }
}

