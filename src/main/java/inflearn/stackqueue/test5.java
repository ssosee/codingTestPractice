package inflearn.stackqueue;

import java.util.*;

public class test5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(new Solution().getNumber(s));
    }
    static class Solution {
        public int getNumber(String s) {
            Stack<Character> stack = new Stack<>();
            int answer= 0;
            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == '(') {
                    stack.push(s.charAt(i));
                }
                //레이저 확인
                else if(s.charAt(i-1) == '(') {
                    stack.pop();
                    answer += stack.size();
                } else {
                    stack.pop();
                    answer += 1;
                }
            }
            return answer;
        }
    }
}
