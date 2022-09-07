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
                } else {
                    //막대기 만들기
                    stack.pop();
                    if(!stack.isEmpty()) {
                        if(stack.peek() == '(') {
                            //왼쪽 막대기 조각
                            answer += stack.size();
                        } else { //막대기의 끝
                            answer++;
                        }
                    }
                }
            }
            return answer;
        }
    }
}
