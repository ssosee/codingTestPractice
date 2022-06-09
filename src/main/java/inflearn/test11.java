package inflearn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <h1>문자열 압축</h1>
 */
public class test11 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(new Solution().solution(str));
    }

    public static class Solution {
        public String solution(String str) {
            String answer = "";
            str = str + " ";
            int count = 1;
            for(int i = 0; i < str.length()- 1; i++) {
                if(str.charAt(i) != str.charAt(i+1)) {
                    answer += str.charAt(i);
                    if(count > 1) answer += String.valueOf(count);
                    count = 1;
                }
                else count++;
            }

            return answer;
        }
    }
}
