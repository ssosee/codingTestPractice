package ohouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Test2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //System.out.println(solution.solution(new String[]{"2", "3", "+", "D", "0", "3", "R"}));
        System.out.println(solution.solution(new String[]{"9", "D", "3", "4", "D", "R", "R", "+"}));
    }

    static class Solution {
        public int solution(String[] rolls) {
            Stack<Integer> stack = new Stack<>();
            for(int i = 0; i < rolls.length; i++) {
                String roll = rolls[i];
                if(roll.equals("R")) {
                    stack.pop();
                } else if(roll.equals("D")) {
                    Integer peek = stack.peek();
                    peek *= 2;
                    stack.push(peek);
                } else if(roll.equals("+")) {
                    Integer pop1 = stack.pop();
                    Integer pop2 = stack.pop();
                    Integer sum = pop1 + pop2;
                    stack.push(pop2);
                    stack.push(pop1);
                    stack.push(sum);
                } else {
                    int score = Integer.parseInt(roll);
                    stack.push(score);
                }
            }

            return stack.stream().mapToInt(Integer::intValue).sum();
        }
    }
}
