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
            Queue<Character> queue1 = new LinkedList<>();
            for(int i = 0; i < s1.length(); i++) {
                queue1.offer(s1.charAt(i));
            }
            for(int i = 0; i < s2.length(); i++) {
                if(queue1.contains(s2.charAt(i))) {
                    if(s2.charAt(i) != queue1.poll()) {
                        return "NO";
                    }
                }
            }
            if(!queue1.isEmpty()) return "NO";
            return "YES";
        }
    }
}
