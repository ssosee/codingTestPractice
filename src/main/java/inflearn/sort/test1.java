package inflearn.sort;

import java.util.*;

public class test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
//        for(int i = 0; i < n; i++) {
//            System.out.print(new Solution().getSort(n, arr)[i]+" ");
//        }
        for(int i = 0; i < n; i++) {
            System.out.print(new Solution().getSort2(n, arr)[i]+" ");
        }
    }

    static class Solution {

        public int[] getSort3(int n, int[] arr) {
            for(int i = 0; i < n-1; i++) {
                int idx = i;
                for(int j = i+1; j < n; j++) {
                    if(arr[j] < arr[idx]) {
                        idx = j;
                    }
                }
                int tmp = arr[i];
                arr[i] = arr[idx];
                arr[idx] = tmp;
            }
            return arr;

        }

        public int[] getSort2(int n, int[] arr) {
            int idx = 0; //인덱스 번수
            for(int i = 0; i < n; i++) {
                for(int j = i+1; j < n; j++) {
                    if(arr[j] < arr[i]) {
                        int tmp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = tmp;
                    }
                }
            }
            return arr;

        }
        public int[] getSort(int n, int[] arr) {
            Arrays.sort(arr);
            return arr;
        }
    }
}
