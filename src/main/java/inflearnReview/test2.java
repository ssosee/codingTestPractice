package inflearnReview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Solution sol = new Solution();
        System.out.println(sol.solution(str));
    }

    public static class Solution {
        public String solution(String str) {

            String answer = "";

            for(char c : str.toCharArray()) {
                if(Character.isUpperCase(c)) {
                    answer += Character.toLowerCase(c);
                }
                else if(Character.isLowerCase(c)) {
                    answer += Character.toUpperCase(c);
                }
            }
            return answer;
        }
    }
}
