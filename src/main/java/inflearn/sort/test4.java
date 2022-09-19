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

            //miss
            //hit
            for(int i : arr) {
                int pos = -1;
                //hit OR miss check
                for(int ii = 0; ii < s; ii++) {
                    if(cache[ii] == i) {
                        pos = ii;
                        break;
                    }
                }
                //miss
                if(pos == -1) {
                    for(int k = s-1; k > 0; k--) {
                        cache[k] = cache[k-1];
                    }
                }
                //hit
                else {
                    for(int k = pos; k > 0; k--) {
                        cache[k] = cache[k-1];
                    }
                }
                cache[0] = i;
            }

            return cache;
        }
    }
}
