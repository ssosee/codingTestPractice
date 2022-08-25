package inflearn.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 가위 바위 보
 */
public class test3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int gameCount = Integer.parseInt(br.readLine());
        String A = br.readLine();
        String B = br.readLine();

        for(int i = 0; i < gameCount; i++) {
            System.out.println(new Solution().solution(gameCount, A, B).get(i));
        }
    }

    static class Solution {
        public List<String> solution(int gameCount, String A, String B) {
            List<String> winner = new ArrayList<>();

            String[] a = A.split(" ");
            String[] b = B.split(" ");

            for(int i = 0; i < gameCount; i++) {
                winner.add(rockScissorsPaper(a[i], b[i]));
            }

            return winner;
        }

        public String rockScissorsPaper(String A, String B) {
            if(A.equals("1")) {
                if(B.equals("1")) return "D";
                else if(B.equals("2")) return "B";
                return "A";
            }
            else if (A.equals("2")) {
                if(B.equals("1")) return "A";
                else if(B.equals("2")) return "D";
                return "B";
            }
            else if (A.equals("3")) {
                if(B.equals("1")) return "B";
                else if(B.equals("2")) return "A";
                return "D";
            }
            return "";
        }
    }
}
