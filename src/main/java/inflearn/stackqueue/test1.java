package inflearn.stackqueue;

import java.util.*;

public class test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(new Solution().getAnswer(s));
    }
    static class Solution {
        public String getAnswer(String s) {

            Stack<Character> stack = new Stack<>();
            // (을 스택에 넣는다.
            // )을 만나면  pop();
            for(char c : s.toCharArray()) {
                if(c == '(') {
                    stack.push(c);
                }
                else if(stack.isEmpty()) {
                    return "NO";
                }
                else {
                    stack.pop();
                }
            }
            if(!stack.isEmpty()) {
                return "NO";
            }
            return "YES";
        }
    }
}
