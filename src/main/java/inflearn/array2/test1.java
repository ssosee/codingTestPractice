package inflearn.array2;

/**
 * 두 배열 합치기
 */
import java.util.*;

public class test1 {
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

        //System.out.println(new Solution().sumArr(arr1, arr2).trim());
        System.out.println(new Solution().twoPointersAlgorithm(arr1, arr2).trim());

    }
    static class Solution {

        /**
         * 투포인트 알고리즘
         */
        public String twoPointersAlgorithm(int[] arr1, int[] arr2) {
            List<Integer> answer = new ArrayList<>();
            String result = "";
            int p1 = 0;
            int p2 = 0;

            while(p1 < arr1.length && p2 < arr2.length) {
                if(arr1[p1] < arr2[p2]) {
                    answer.add(arr1[p1++]);
                } else {
                    answer.add(arr2[p2++]);
                }
            }
            //나머지 삽입
            while(p1 < arr1.length) {
                answer.add(arr1[p1++]);
            }
            while(p2 < arr2.length) {
                answer.add(arr2[p2++]);
            }

            for(int i : answer) {
                result += i+" ";
            }

            return result;
        }

        public String sumArr(int[] arr1, int[] arr2) {
            String answer = "";
            List<Integer> sumArray = new ArrayList<>();
            for(Integer i : arr1) {
                sumArray.add(i);
            }
            for(Integer i : arr2) {
                sumArray.add(i);
            }
            int[] result = new int[sumArray.size()];
            for(int i = 0; i < sumArray.size(); i++) {
                result[i] = sumArray.get(i);
            }
            //오름차순 정렬
            Arrays.sort(result);

            for(int r : result) {
                answer += r+" ";
            }

            return answer;
        }
    }
}
