package inflearn.stackqueue;

import java.util.*;

public class test4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(new Solution().getAnswer(s));
    }
    static class Solution {

        public int getAnswer2(String s) {
            Stack<Integer> stack = new Stack<>();
            for(char ch : s.toCharArray()) {
                if(Character.isDigit(ch)) {
                    stack.push(Character.getNumericValue(ch));
                } else {
                    int rt = stack.pop();
                    int lt = stack.pop();
                    switch (ch) {
                        case '+' : stack.push(lt+rt); break;
                        case '-' : stack.push(lt-rt); break;
                        case '*' : stack.push(lt*rt); break;
                        case '/' : stack.push(lt/rt); break;
                    }
                }
            }

            return stack.get(0);
        }

        public int getAnswer(String s) {
            int answer = 0;

            Stack<Integer> stack = new Stack<>();
            for(int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '+') {
                        int result = stack.pop() + stack.pop();
                        stack.push(result);
                    } else if (s.charAt(i) == '-') {
                        int fi = stack.pop();
                        int se = stack.pop();
                        int result = se - fi;
                        stack.push(result);
                    } else if (s.charAt(i) == '*') {
                        int result = stack.pop() * stack.pop();
                        stack.push(result);
                    } else if (s.charAt(i) == '/') {
                        int fi = stack.pop();
                        int se = stack.pop();
                        int result = se / fi;
                        stack.push(result);
                    } else {
                        stack.push(Character.getNumericValue(s.charAt(i)));
                    }
            }
            return stack.pop();
        }
    }
}
