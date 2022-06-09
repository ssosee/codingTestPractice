package inflearn;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1>가장 짧은 문자거리</h1>
 */
public class test10 {
    public static void main(String[] args) throws Exception{
        Scanner sr = new Scanner(System.in);
        String str = sr.next();
        char c = sr.next().charAt(0);

        for (int i : new Solution().solution(str, c)) {
            System.out.print(i+" ");
        }
    }

    public static class Solution {
        public int[] solution(String str, char c) {
            int[] answer = new int[str.length()];
            int d = 1000;

            /**
             * 1. 왼쪽으로 한번, 오른쪽으로 한번 for문이 돌아야한다.!
             * 2. 해당 문자를 만나면 거리 0으로 초기화
             */
            for(int i = 0; i < str.length(); i++) {
                if(c == str.charAt(i)) d = 0;
                else d++;
                answer[i] = d;
            }

            d = 1000; //초기화

            for(int i = str.length() - 1; i >= 0; i--) {
                if(c == str.charAt(i)) d = 0;
                else d++;
                if(answer[i] > d) answer[i] = d;
            }


            return answer;
        }
    }
}
