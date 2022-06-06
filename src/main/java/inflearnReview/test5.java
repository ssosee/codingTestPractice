package inflearnReview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Solution solution = new Solution();
        System.out.println(solution.getRevString(str));
    }

    public static class Solution {
        public String getRevString(String str) {
            String answer = "";

            int lt = 0;
            int rt = str.length() - 1;
            char[] c = str.toCharArray();

            while (lt < rt) {
                if (!Character.isAlphabetic(c[lt])) lt++;
                else if (!Character.isAlphabetic(c[rt])) rt--;
                else {
                    char temp = c[lt];
                    c[lt] = c[rt];
                    c[rt] = temp;
                    lt++;
                    rt--;
                }
            }
            answer = String.valueOf(c);

            return answer;
        }
    }
}
