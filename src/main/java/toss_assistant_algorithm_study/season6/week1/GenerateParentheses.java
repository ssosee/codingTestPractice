package toss_assistant_algorithm_study.season6.week1;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.generateParenthesis(3));
    }

    static class Solution {

        static final String open = "(";
        static final String close = ")";
        static List<String> parentheses;
        public List<String> generateParenthesis(int n) {
            parentheses = new ArrayList<>();
            createParentheses(open, n-1, n);
            return parentheses;
        }

        private void createParentheses(String parenthesis, int openCount, int closeCount) {
            if(openCount == 0 && closeCount == 0) {
                parentheses.add(parenthesis);
                return;
            }

            // ( 괄호 넣기
            if(openCount > 0) {
                createParentheses(parenthesis + open, openCount - 1, closeCount);
            }

            // ) 괄호 넣기
            if(closeCount > openCount) {
                createParentheses(parenthesis + close, openCount, closeCount - 1);
            }
        }
    }
}
