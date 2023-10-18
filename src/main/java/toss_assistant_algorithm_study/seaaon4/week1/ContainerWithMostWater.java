package toss_assistant_algorithm_study.seaaon4.week1;

public class ContainerWithMostWater {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }

    static class Solution {

        /**
         * 아이디어
         *  - 투포인터
         *  - startIndex, endIndex를 이용하여 O(N)으로 문제를 해결해야함
         *
         * 시간복잡도
         *  - O(N^2)
         *
         * 자료구조
         * - 배열
         */

        public int maxArea(int[] height) {
            int max = Integer.MIN_VALUE;
            int start = 0;
            int end = height.length - 1;

            while (start < end) {
                int element1 = height[start];
                int element2 = height[end];
                int distance = end - start;

                if(element1 <= element2) {
                    max = Math.max(max, element1 * distance);
                    start++;
                } else {
                    max = Math.max(max, element2 * distance);
                    end--;
                }
            }

            return max;
        }

        // 시간초과
        public int maxAreaTimeOut(int[] height) {
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < height.length; i++) {
                for(int j = i+1; j < height.length; j++) {
                    int distance = j - i;
                    int h = Math.min(height[i], height[j]);
                    int area = distance * h;
                    max = Math.max(area, max);
                }
            }

            return max;
        }
    }
}
