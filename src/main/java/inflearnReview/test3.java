package inflearnReview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Solution solution = new Solution();
        System.out.println(solution.getLongestString(str));
    }

    public static class Solution {
        public String getLongestString(String str) {
            String[] s = str.split(" ");
            int min = Integer.MIN_VALUE;
            String answer = "";
            for(int i = 0; i < s.length; i++) {
                if(s[i].length() > min) {
                    min = s[i].length();
                    answer = s[i];
                }
            }

            return answer;
        }
    }
}
