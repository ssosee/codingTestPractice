package groom;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CountingWords {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        Solution solution = new Solution(input);

        System.out.println(solution.sol());
    }

    public static class Solution {

        private String input = "";

        Solution(String input) {
            this.input = input;
        }

        public int sol() {
            int count = 0;
            String[] str = input.split(" ");
            for(String s : str) {
                if(!s.isEmpty()) {
                    count++;
                }
            }

            return count;
        }

    }
}
