package ohouse;

public class Test4 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[]{3, 0, 6, 1, 5}));
        System.out.println(solution.solution(new int[]{1, 3, 1}));
    }

    static class Solution {
        public int solution(int[] references) {
            int left = 0, right = getMax(references); // 이분 탐색 범위: 0부터 최대 인용 횟수까지

            while (left <= right) {
                int k = (left + right) / 2; // 중간값 계산

                // 현재 k값으로 인용 횟수를 카운트
                int count = getCount(references, k);

                // 조건을 만족하는 최대 k를 찾기 위한 이분 탐색
                if (count >= k) {
                    left = k + 1;
                } else {
                    right = k - 1;
                }
            }

            return right; // right가 최대 인용 횟수 k를 나타냄
        }

        // 배열에서 최대 인용 횟수를 찾는 메소드
        private static int getMax(int[] references) {
            int max = Integer.MIN_VALUE;
            for (int ref : references) {
                max = Math.max(max, ref);
            }
            return max;
        }

        // 배열에서 k보다 큰 값을 갖는 원소의 개수를 세는 메소드
        private static int getCount(int[] references, int k) {
            int count = 0;
            for (int ref : references) {
                if (ref >= k) {
                    count++;
                }
            }
            return count;
        }
    }
}
