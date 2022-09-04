package inflearn.hashandtree;

import java.util.*;

public class test5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(new Solution().getNumber(n, k, arr));
    }
    static class Solution {
        public int getNumber(int n, int k, int[] arr) {
            int answer = -1;
            //내림차순
            TreeSet<Integer> tree = new TreeSet<>(Collections.reverseOrder());
            for(int i = 0; i < n; i ++) {
                for(int j = i + 1; j < n; j++) {
                    for(int s = j + 1; s < n; s++) {
                        tree.add(arr[i] + arr[j] + arr[s]);
                    }
                }
            }
            int cnt = 0;
            for(int x : tree){
                cnt++;
                if(cnt == k) return x;
            }
            return answer;
        }
    }
}
