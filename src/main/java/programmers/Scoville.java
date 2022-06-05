package programmers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Scoville {
    public static void main(String[] args) throws IOException {
        int[] s = {1, 2, 3, 9, 10, 12};
        Solution solution = new Solution();
        solution.solution(s, 7);
    }

    static class Solution {
        public int solution(int[] scoville, int K) {

            PriorityQueue<Integer> scovilleLowest = new PriorityQueue<>();
            int[] scvArray = new int[scoville.length];
            int result = 0;
            int count = 0;

            for (int aScoville : scoville) {
                scovilleLowest.offer(aScoville);
            }

            while (scovilleLowest.peek() <= K) {
                if(scovilleLowest.size() == 1) {
                    return -1;
                }

                int a = scovilleLowest.poll();
                int b = scovilleLowest.poll();

                result = a + (b * 2);
                scovilleLowest.offer(result);
                count++;
            }

            return count;
        }
    }
}
