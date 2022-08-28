package inflearnReview.string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test10 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();
        Solution sol = new Solution();
        int[] result = sol.solution(inputStr);

        for (Integer integer : result) {
            System.out.print(integer+" ");
        }
    }

    static class Solution {
        public int[] solution(String inputStr) {

            String[] str = inputStr.split(" ");
            String s = str[0];
            String t = str[1];
            char[] chars = s.toCharArray();
            int d = 1000; //거리
            int[] answer = new int[s.length()];

            //왼쪽으로 거리 비교
            for(int i = 0; i < s.length(); i++) {
                if(chars[i] == t.charAt(0)) d = 0;
                else d++;
                answer[i] = d;
            }

            d = 1000; //초기화

            //오른쪽으로 거리 비교
            for(int i = s.length() - 1; i >= 0; i--) {
                if(chars[i] == t.charAt(0)) d = 0;
                else d++;
                //작은 값으로 대체
                if(answer[i] > d) answer[i] = d;
            }

            return answer;
        }
    }
}
