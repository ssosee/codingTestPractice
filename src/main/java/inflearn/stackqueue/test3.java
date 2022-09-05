package inflearn.stackqueue;

import java.util.*;

public class test3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        int m = sc.nextInt();
        int[] mArr = new int[m];
        for (int i = 0; i < m; i++) {
            mArr[i] = sc.nextInt();
        }
        System.out.println(new Solution().getNumber(n, arr, m, mArr));
    }
    static class Solution {
        public int getNumber(int n, int[][] arr, int m, int[] mArr) {
            Stack<Integer> stack = new Stack<>();
            int answer = 0;

            for(int p : mArr) {
                for(int i = 0; i < n; i++) {
                    if(arr[i][p-1] != 0) {
                        int tmp = arr[i][p-1];
                        arr[i][p-1] = 0;
                        if(!stack.isEmpty() && stack.peek() == tmp) {
                            stack.pop();
                            answer += 2;
                        } else {
                            stack.push(tmp);
                        }
                        break;
                    }
                }
            }

            return answer;
        }
    }
}
