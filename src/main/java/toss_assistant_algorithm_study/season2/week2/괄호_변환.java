package toss_assistant_algorithm_study.season2.week2;

public class 괄호_변환 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("(()())()"));
    }

    static class Solution {
        /**
         * 아이디어
         * (와 )만 이루어진 문자열 = 균형잡힌 괄호 문자열
         * (와 )가 짝이 맞을 경우 = 올바른 괄호 문자열
         *
         * (()))( = 균형잡힌 문자열(O), 올바른 괄호 문자열(X)
         * (())() = 균형잡힌 문자열(O), 올바른 괄호 문자열(O)
         * -> 올바른 괄호 문자열이면, 균형잡힌 문자열이다.
         *
         * 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
         * 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다.
         *    단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
         * 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
         *   3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
         * 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
         *   4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
         *   4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
         *   4-3. ')'를 다시 붙입니다.
         *   4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
         *   4-5. 생성된 문자열을 반환합니다.
         *
         * 자료구조
         *  StringBuilder(동기화 보장X)
         *
         * 시간복잡도
         *  O(N^2)
         */

        public String solution(String p) {
            String answer;

            // 빈문자열 이면
            if(p.isBlank()) return "";

            // 올바른 괄호 문자열이면
            if(isRightString(p)) return p;

            return splitUAndV(p);
        }

        private String splitUAndV(String p) {
            StringBuilder u = new StringBuilder();
            StringBuilder v = new StringBuilder();

            // ( 갯수
            int count = 0;
            for(int i = 0; i < p.length(); i++) {
                if(p.charAt(i) == '(') count++;
                else count--;

                // ( 갯수가 0이면 -> 균형 잡힌 괄호
                if(count == 0) {
                    u.append(p.substring(0, i+1));
                    v.append(p.substring(i+1, p.length()));

                    // 올바른 괄호 문자열이면
                    if(isRightString(u.toString())) {
                        u.append(splitUAndV(v.toString()));
                    }
                    // 올바른 괄호가 아니면
                    else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("(");
                        sb.append(splitUAndV(v.toString()));
                        sb.append(")");
                        // 첫번째 마지막 문자 제거, 괄호 방향 반대로
                        String newU = u.substring(1, u.length() - 1);
                        for(int j = 0; j < newU.length(); j++) {
                            if(newU.charAt(j) == '(') sb.append(")");
                            else sb.append("(");
                        }
                        return sb.toString();
                    }
                    break;
                }
            }

            return u.toString();
        }

        // 올바른 괄호 문자열 확인
        private boolean isRightString(String str) {
            // ( 갯수
            int count = 0;
            // 첫 문자가 ) 인 경우
            if(str.charAt(0) == ')') return false;

            for(int i = 0; i < str.length(); i++) {
                if(str.charAt(i) == '(') count++;
                else {
                    count--;
                    // ( 갯수가 음수인 경우 )가 더 많다는 의미이기 때문에 올바른 괄호가 아님
                    if(count < 0) return false;
                }
            }

            return true;
        }
    }
}
