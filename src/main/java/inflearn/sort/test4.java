package inflearn.sort;

import java.util.*;

/**
 * LRU
 */
public class test4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int[] answer = new Solution().getAnswer(s, n, arr);
        for(int i : answer) {
            System.out.print(i+" ");
        }
    }
    static class Solution {
        public int[] getAnswer(int s, int n, int[] arr) {
            int[] cache = new int[s];

            for(int x : arr) {
                int pos = -1;
                //hit, miss check
                for(int i = 0; i < s; i++) {
                    if(x == cache[i]) {
                        pos = i;
                    }
                }
                //miss
                if(pos == -1) {
                    for(int i = s-1; i >= 1; i--) {
                        cache[i] = cache[i-1];
                    }
                }
                //hit
                else {
                    for(int i = pos; i >= 1; i--) {
                        cache[i] = cache[i-1];
                    }
                }
                cache[0] = x;
            }
            return cache;
        }
    }
}
