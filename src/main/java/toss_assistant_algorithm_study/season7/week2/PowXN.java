package toss_assistant_algorithm_study.season7.week2;

public class PowXN {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.myPow(2.00000, -2));
    }

    /**
     * n = 0이면, x의 0승은 항상 1이므로 1을 반환
     * n < 0이면, 음수 지수는 양수로 변환하고, x의 역수를 계산하여 반환
     * n이 홀수인 경우, 지수를 1 감소시키고 x와 함께 myPow 함수를 재귀적으로 호출하여 거듭제곱을 계산
     * n이 짝수인 경우, 지수를 절반으로 나누고 x를 제곱하여 myPow 함수를 재귀적으로 호출
     */
    static class Solution {
        public double myPow(double x, int n) {
            // 양수의 범위는  2^(31)-1이기 때문에 n이 MIN_VALUE일 때 -1을 곱해버리면 overflow 발생..
            while(n == Integer.MIN_VALUE){
                x = x*x;
                n = n/2;
            }

            if(n == 0) {
                // 0승은 1이다.
                return 1;
            } else if(n < 0) {
                // 양수로 안바꾸면 또 1에서 나눔
                return 1 / myPow(x, -n);
            } else if(n % 2 == 1) {
                // 홀수이면 1감소 시키고 짝수로 만든다.
                return x * myPow(x, n - 1);
            }
            // 짝수이면 반으로 나누고 제곱하여 호출
            return myPow(x * x, n / 2);
        }
    }
}
