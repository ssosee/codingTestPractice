package inflearn.stackqueue;

import java.util.*;

public class test6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        System.out.println(new Solution().getPrince(n, k));
    }
    static class Solution {
        public int getPrince(int n, int k) {
            int answer = 0;
            Queue<Integer> queue = new LinkedList<>();
            for(int i = 0; i < n; i++) {
                queue.offer(i+1);
            }

            while (!queue.isEmpty()) {
                for(int i = 0; i < k; i++) {
                    if(i == k-1) {
                        queue.poll();
                    } else {
                        queue.offer(queue.poll());
                    }
                }
                if(queue.size() == 1) answer = queue.poll();
            }

            return answer;
        }
    }
}
