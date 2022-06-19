package inflearnReview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test9 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(new Solution().getInt(str));
    }

    public static class Solution {
        public int getInt(String str) {
            String answer = "";

            for(char c : str.toCharArray()) {
                if(Character.isDigit(c)) answer += c;
            }

            return Integer.parseInt(answer);
        }
    }
}
