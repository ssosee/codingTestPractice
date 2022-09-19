package inflearn.sort;

import java.util.*;
/**
 * 중복 확인
 */
public class test5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(new Solution().getAnswer2(n, arr));
    }
    static class Solution {

        public char getAnswer3(int n, int[] arr) {
            Arrays.sort(arr);
            for(int i = 0; i < n-1; i++) {
                if(arr[i] == arr[i+1]) return 'D';
            }
            return 'U';
        }

        public char getAnswer2(int n, int[] arr) {
            HashSet<Integer> set = new HashSet<>();
            for(int i = 0; i < n; i++) {
                set.add(arr[i]);
            }
            return (set.size() == n ? 'U' : 'D');
        }

        public char getAnswer(int n, int[] arr) {
            for(int i = 0; i < n; i++) {
                for(int k = i+1; k < n; k++) {
                    if(arr[i] == arr[k]) return 'D';
                }
            }
            return 'U';
        }
    }
}
