package inflearn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 보이는 학생
 */
public class test14 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        String s = br.readLine();

        System.out.println(new Solution().solution(num, s));
    }

    static class Solution {
        public int solution(int num, String s) {
            int answer = 1; //첫번째 사람은 무조건 볼수 있음
            int max = 0;
            String[] str = s.split(" ");
            max = Integer.parseInt(str[0]);

            for (int i = 1; i < num; i++) {
                if(max < Integer.parseInt(str[i])) {
                    answer++;
                    max = Integer.parseInt(str[i]);
                }
            }

            return answer;
        }
    }
}
