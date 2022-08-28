package inflearn.array;

import java.util.*;

/**
 * 등수구하기
 */
public class test9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] arr = new int[num];
        for(int i = 0; i < num; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(new Solution().rank(arr));
    }
}
class Solution {
    public String rank(int[] arr) {
       int[] answer = new int[arr.length];
       String result = "";

       //1로 초기화
       for(int i = 0; i < answer.length; i++) {
           answer[i] = 1;
       }

       for(int i = 0; i < arr.length; i++) {
           for(int j = 0; j < arr.length; j++) {
               //i보다 j일 때 점수가 더 크면, i의 등수 증가
               if(arr[i] < arr[j]) {
                   answer[i]++;
               }
           }
       }

       for(int i = 0; i < answer.length; i++) {
           result += answer[i]+" ";
       }

       return result;
    }
}

