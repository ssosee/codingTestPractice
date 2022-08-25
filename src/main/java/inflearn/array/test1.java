package inflearn.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1>큰 수 출력하기</h1>
 */
public class test1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        String str = br.readLine();
        System.out.println(new Solution().getInt(str, num).trim());

        /**
         * 강의 풀이
         */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        for(int x : new Solution().solution2(n, arr)) {
            System.out.println(x+" ");
        }
    }

    public static class Solution {
        public String getInt(String str, int num) {

            String[] s = str.split(" ");
            String answer = s[0] + " ";

            for (int i = 1; i < num; i++) {
                int temp = Integer.parseInt(s[i - 1]);
                if (Integer.parseInt(s[i]) > temp) answer += s[i] + " ";
            }

            return answer;
        }
        /**
         * 강의 풀이
         */
        public ArrayList<Integer> solution2(int n, int[] arr) {
            ArrayList<Integer> answer = new ArrayList<>();
            answer.add(arr[0]);

            for (int i = 1; i < n; i++) {
                if(arr[i] > arr[i-1]) answer.add(arr[i]);
            }

            return answer;
        }
    }
}
