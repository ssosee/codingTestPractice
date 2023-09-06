package channelTalk;

public class Test1 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(4, 3, 20));
    }

    static class Solution {
        public int solution(int mod1, int mod2, int max_range) {
            // mod1로 나누어 떨어지는 수의 개수
            int count = max_range / mod1;

            // mod1과 mod2의 최소 공배수
            int count_common = max_range / lcm(mod1, mod2);

            // mod1로 나누어 떨어지지만 mod2로 나누어 떨어지지 않는 수의 개수
            int count_mod_only = count - count_common;

            return count_mod_only;
        }

        // 최소 공배수를 계산하는 함수
        private int lcm(int a, int b) {
            return a * b / gcd(a, b);
        }

        // 최대 공약수를 계산하는 함수
        private int gcd(int a, int b) {
            if (b == 0) {
                return a;
            }
            return gcd(b, a % b);
        }
    }


}

