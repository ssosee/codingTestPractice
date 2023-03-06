package inflearn.sort;

import java.util.*;

/**
 * 결정 알고리즘(이분탐색)
 * lt ~ rt 에 정답이 있을 경우에만 적용 가능
 */
public class test9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
    }
    static class Solution {
        public int getAnswer(int n, int m, int[] arr) {
            Arrays.sort(arr);
            int lt = Arrays.stream(arr).max().getAsInt();
            int rt = Arrays.stream(arr).sum();
            int mid = (lt + rt)/2;
            
            while (lt <= rt) {

            }

            return 0;
        }

        public int count(int[] arr, int capacity) {
            return 0;
        }
    }
}
