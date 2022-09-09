package inflearn.stackqueue;

import java.util.*;

public class test7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.next();
        System.out.println(new Solution().getAnswer(s1, s2));
    }
    static class Solution {
        public String getAnswer(String s1, String s2) {
            Queue<Character> queue = new LinkedList<>();
            for(char ch : s1.toCharArray()) {
                queue.offer(ch);
            }
            for(int i = 0; i < s2.length(); i++) {
                if(queue.contains(s2.charAt(i))) {
                    if(s2.charAt(i) != queue.poll()) {
                        return "NO";
                    }
                }
            }
            if(!queue.isEmpty()) return "NO";
            return "YES";
        }
    }
}
