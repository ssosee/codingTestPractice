package inflearnReview.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char c = (char) br.read();

        Solution sol = new Solution();
        System.out.println(sol.solution(str, c));
    }

    public static class Solution {

        public int solution(String str, char c) {
            int answer = 0;

            //모두 다 대문자로 변환
            str = str.toUpperCase();
            c = Character.toUpperCase(c);

            //charAt(index)로 index에 위치한 문자 찾기
            for(int i = 0; i < str.length(); i++) {
                if(c == str.charAt(i)) answer++;
            }

            return answer;
        }
    }
}
