package inflearn.array2;

import java.util.*;

public class test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int arr1Size = sc.nextInt();
        int [] arr1 = new int [arr1Size];
        for(int i = 0; i < arr1Size; i++) {
            arr1[i] = sc.nextInt();
        }

        int arr2Size = sc.nextInt();
        int [] arr2 = new int [arr2Size];
        for(int i = 0; i < arr2Size; i++) {
            arr2[i] = sc.nextInt();
        }

        System.out.println(new Solution().twoPointersAlgorithm(arr1, arr2));
    }

    static class Solution {
        public String twoPointersAlgorithm(int[] arr1, int[] arr2) {
            List<Integer> result = new ArrayList<>();
            String answer = "";
            int p1 = 0;
            int p2 = 0;

            /**
             * 1. 오름차순으로 먼저 정렬
             * 2. arr1[p1] < arr2[p2] 이면 p1 증가(작은쪽 증가)
             * 3. arr1[p1] == arr2[p2] p1, p2 증가
             */
            Arrays.sort(arr1);
            Arrays.sort(arr2);

            while(p1 < arr1.length && p2 < arr2.length) {
                if(arr1[p1] < arr2[p2]) {
                    p1++;
                }
                else if(arr1[p1] > arr2[p2]) {
                    p2++;
                }
                else if(arr1[p1] == arr2[p2]) {
                    result.add(arr1[p1]);
                    p2++;
                    p1++;
                }
            }


            for(int i : result) {
                answer += i +" ";
            }

            return answer;
        }
    }
}
