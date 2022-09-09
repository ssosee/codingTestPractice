package inflearn.sort;

import java.util.*;

public class test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        for(int i = 0; i < n; i++) {
            System.out.print(new Solution().getAnswer(n, arr)[i]+" ");
        }
    }

    static class Solution {
        public int[] getAnswer(int n, int[] arr) {
            //버블 정렬은 맨 마지막은 결정
            for(int i = n; i > 1; i--) {
                for(int j = 1; j < n; j++) {
                    if(arr[j-1] > arr[j]) {
                        int temp = arr[j-1];
                        arr[j-1] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
            return arr;
        }
    }
}
