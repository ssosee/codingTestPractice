package programmers.level2;

public class 마법의_엘리베이터 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.print(solution.solution(5555));
    }

    static class Solution {
        public int solution(int storey) {
            /**
             * 주어진 숫자의 일의 숫자부터 확인해야 합니다.
             *
             * 1. 일의 자리 숫자와 십의 자리 숫자 확인
             * 2. 일의 자리 숫자가 5보다 클 경우 올림
             * 2.1 일의 자리 숫자가 5일 경우 십의 자리숫자가 5이상이면 올림, 5미만일 경우 내림
             * 2.2 일의 자리 숫자가 5보다 작을 경우 내림
             * 3. storey를 10으로 나누고 1~3 과정 반복
             */
            int answer = 0;
            while (storey > 0) {
                // 일의 자리 숫자
                int oneDigit = storey % 10;
                // 십의 자리 숫자
                int tenDigit = (storey / 10) % 10;

                // 일의 자리 숫자가 5보다 크면
                if(oneDigit > 5) {
                    // 일의자리 숫자만큼 돌 사용
                    answer += 10 - oneDigit;
                    // 올림
                    storey += 10;
                }
                // 일의 자리 숫자가 5 이면
                else if(oneDigit == 5) {
                    // 5만큼 돌 사용
                    answer += 5;
                    // 십의 자리가 5이상이면 올림
                    if(tenDigit >= 5) {
                        storey += 10;
                    }
                }
                // 일의 자리 숫자가 5보다 작으면
                else {
                    // 일의 자리 숫자만큼 돌 사용
                    answer += oneDigit;
                }

                // 10으로 나눔(나눠야 다음 루프일때 일의 자리, 십의 자리를 파악)
                storey /= 10;
            }

            return answer;
        }
    }
}
