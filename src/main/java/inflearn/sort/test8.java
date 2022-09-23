package inflearn.sort;

import java.util.*;

/**
 * 이분 검색 알고리즘
 */
public class test8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(new Solution().getAnswer(n, m, arr));
    }

    static class Solution {
        public int getAnswer(int n, int m, int[] arr) {
            /**
             * 1. 오름차순 정렬
             * 2. mid(중간 값) = (lt + rt) / 2
             * 3. arr[mid]
             */
            Arrays.sort(arr);
            int lt = 0;
            int rt = n-1;
            while (lt <= rt) {
                int mid = (lt + rt)/2;
                if(arr[mid] == m) {
                    return mid+1;
                }
                else if(arr[mid] > m) {
                    rt = mid - 1;
                } else {
                    lt = mid + 1;
                }
            }
            return 0;
        }
    }
}
