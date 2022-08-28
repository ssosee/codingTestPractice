package inflearnReview.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test8 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(new Solution().getString(str));
    }

    public static class Solution {
        public String getString(String str) {
            String answer = "NO";

            str = str.toUpperCase();
            String s = "";
            for(char c : str.toCharArray()) {
                if(Character.isAlphabetic(c)) s += c;
            }

            if(s.equals(new StringBuilder(s).reverse().toString())) return "YES";

            return answer;
        }
    }
}
