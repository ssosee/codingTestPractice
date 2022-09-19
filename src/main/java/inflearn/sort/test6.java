package inflearn.sort;

import java.util.*;

/**
 * 장난 꾸러기
 */
public class test6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int[] answer = new Solution().getAnswer(n, arr);
        for(int x : answer) {
            System.out.print(x+" ");
        }
    }
    static class Solution {
        public int[] getAnswer(int n, int[] arr) {
            int[] answer = new int[2];
            int[] tmp = arr.clone();
            Arrays.sort(arr);
            int idx = 0;
            for(int i = 0; i < n; i++) {
                if(arr[i] != tmp[i]) {
                    answer[idx++] = i+1;
                }
            }
            return answer;
        }
    }
}
