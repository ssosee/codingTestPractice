package ohouse;

public class Test1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //System.out.println(solution.solution("ohouse", "oohoussse"));
        //System.out.println(solution.solution("bucketplace", "buckeetpladce"));
        System.out.println(solution.solution("oohhse", "ooohhse"));
    }

    static class Solution {
        public boolean solution(String target, String typed) {
            target = target.toLowerCase();
            typed = typed.toLowerCase();
            if(target.equals(typed)) return true;

            StringBuilder result = new StringBuilder();
            for (int i = 0; i < typed.length(); i++) {
                char currentChar = typed.charAt(i);

                // 현재 문자와 다음 문자가 같으면 중복된 문자열이므로 넘어감
                if (i < typed.length() - 1 && currentChar == typed.charAt(i + 1)) {
                    continue;
                }

                result.append(currentChar);
            }

            if(result.toString().equals(target)) return true;


            return false;
        }
    }
}
