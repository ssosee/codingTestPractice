package inflearnReview;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class test6 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(new Solution().getString(str));
    }

    public static class Solution {
        public String getString(String str) {
            String answer = "";
            char[] c = str.toCharArray();

            for(int i = 0; i < str.length(); i++) {
                if(i == str.indexOf(c[i])) {
                    answer += c[i];
                }
            }

            return answer;
        }
    }
}
