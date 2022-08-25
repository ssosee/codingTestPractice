package inflearn.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * <h1>암호</h1>
 */
public class test12 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        //System.out.println(new Solution().decryption(n, str));
        System.out.println(new Solution().solution2(n, str));
    }

    public static class Solution {
        /**
         * #: 1
         * *: 0
         */
        public String decryption(int n, String str) {
            String answer = "";
            String s = "";
            char ch;

            for(char c : str.toCharArray()) {
                if(c == '*') s += '0';
                else if (c == '#') s += '1';
            }

            for(int i = 0; i < n; i++) {
                String strBin = s.substring(7*i, 7*(i+1));
                ch = (char) Integer.parseInt(strBin, 2);
                answer += ch;
            }

            return answer;
        }

        /**
         * 강의 풀이
         */
        public String solution2(int n, String str) {
            String answer = "";

            for(int i = 0; i < n; i++) {
                String tmp = str.substring(0, 7)
                        .replace('#', '1')
                        .replace('*', '0');
                int num = Integer.parseInt(tmp, 2);
                answer += (char) num;
                str = str.substring(7); //시작 위치를 바꿈
            }

            return answer;
        }
    }
}
