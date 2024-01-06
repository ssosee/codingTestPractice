package toss_assistant_algorithm_study.season7;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestCommonPrefix(new String[]{"flower","flow","flight"}));
    }

    /**
     * 아이디어
     * - 배열의 첫번째 값을 기준으로 비교한다.
     */
    static class Solution {
        public String longestCommonPrefix(String[] strs) {
            if(strs.length == 0) return "";

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < strs[0].length(); i++) {
                char ch = strs[0].charAt(i);
                for(int j = 1; j < strs.length; j++) {
                    // 비교대상의 문자열의 길이(strs[j].length())가 기준 문자열에서 비교하려는 인덱스(i) 보다 작거나
                    // 비교대상의 문자열의 문자가 기준 문자열의 문자와 같이 않으면
                    if(strs[j].length() <= i || ch != strs[j].charAt(i)) {
                        // 최대 prefix가 아니다!
                        return sb.toString();
                    }
                }

                // prefix에 추가
                sb.append(ch);
            }

            return sb.toString();
        }
    }
}
