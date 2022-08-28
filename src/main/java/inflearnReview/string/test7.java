package inflearnReview.string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class test7 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(new Solution().solution(str));
    }

    public static class Solution {
        public String solution(String str) {
            String answer = "NO";

            str = str.toUpperCase();

            if(str.equals(new StringBuilder(str).reverse().toString())) return "YES";

            return answer;
        }
    }
}
