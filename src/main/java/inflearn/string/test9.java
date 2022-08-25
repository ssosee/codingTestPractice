package inflearn.string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <h1>숫자만 추출</h1>
 */
public class test9 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(new Solution().getInt(str));
    }

    public static class Solution {
        public int getInt(String str) {
            String answer = "";
            char[] c = str.toCharArray();
            for(int i = 0; i < str.length(); i++) {
                if(Character.isDigit(c[i])) answer += c[i];
            }

            return Integer.parseInt(answer);
        }

        /***
         * 강의 풀이
         */
        public int getInt2(String str) {
            int answer = 0;
            /**
             * ASCII
             * 48 ~ 57: 0 ~ 9
             */
            for(char c : str.toCharArray()) {
                if(c > 47 && c <= 57) {
                    answer = answer*10+(c-48);
                }
            }

            return answer;
        }

        /***
         * 강의 풀이2
         */
        public int getInt3(String str) {
            String answer = "";
            for(char ch : str.toCharArray()) {
                if(Character.isDigit(ch)) answer += ch;
            }

            return Integer.parseInt(answer);
        }
    }
}
