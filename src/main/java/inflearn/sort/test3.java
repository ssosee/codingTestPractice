package inflearn.sort;

import java.util.*;

public class test3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
//        for(int i = 0; i < n; i++) {
//            System.out.print(new Solution().getAnswer(n, arr)[i]+" ");
//        }
        for(int i = 0; i < n; i++) {
            System.out.print(new Solution().getAnswer2(n, arr)[i]+" ");
        }
    }
    static class Solution {

        public int[] getAnswer2(int n, int[] arr) {
            for(int i = 1; i < n; i++) {
                int tmp = arr[i];
                int j = 0;
                for(j = i - 1; j >= 0; j--) {
                    if(arr[j] > tmp) {
                        arr[j+1] = arr[j];
                    } else {
                        break;
                    }
                }
                arr[j+1] = tmp;
            }

            return arr;
        }

        public int[] getAnswer(int n, int[] arr) {
            for(int i = 0; i < n-1; i++) {
                for(int j = i+1; j > 0; j--) {
                    if(arr[j-1] > arr[j]) {
                        int tmp = arr[j];
                        arr[j] = arr[j-1];
                        arr[j-1] = tmp;
                    }
                }
            }
            return arr;
        }
    }
}
