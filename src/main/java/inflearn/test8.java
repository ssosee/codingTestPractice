package inflearn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test8 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(new Solution().solution(str));
    }

    public static class Solution {
        public String solution(String str) {
            String answer = "NO";
            String s = "";
            str = str.toUpperCase();

            for(int i = 0; i < str.length(); i++) {
                if(Character.isAlphabetic(str.charAt(i))) {
                    s += str.charAt(i);
                }
            }

            if(s.equals(new StringBuilder(s).reverse().toString())) return "YES";

            return answer;
        }

        /***
         * 강의 풀이
         * @param str
         * @return
         */
        public String solution2(String str) {
            String answer = "NO";
            //[^A-Z] -> A-Z이 아니면
            str = str.toUpperCase().replaceAll("^[A-Z]", "");
            String tmp = new StringBuilder(str).reverse().toString();
            if(str.equals(tmp)) return "YES";

            return answer;
        }
    }

}
