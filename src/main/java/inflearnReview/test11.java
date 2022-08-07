package inflearnReview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test11 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        System.out.println(new Solution().solution(s));
    }

    static class Solution {
        public String solution(String s) {
            String answer = "";
            int cnt = 1;
            s = s + " ";
            //빈문자 전까지만
            for(int i = 0; i < s.length() - 1; i++) {
                if(s.charAt(i) != s.charAt(i+1)) {
                    answer += s.charAt(i);
                    if (cnt > 1) answer += String.valueOf(cnt);
                    cnt = 1;
                }
                else cnt++;
            }

            return answer;
        }
    }
}
