package toss_assistant_algorithm_study.seaaon4.week1;

public class ContainerWithMostWater {
    public static void main(String[] args) {

    }

    static class Solution {
        public int maxArea(int[] height) {
            int max = Integer.MIN_VALUE;
            int index1 = 0;
            int index2 = 1;
            while (index1 < height.length) {
                int width = index2 - index1;
                int h1 = height[index1];
                int h2 = height[index2];
                int area = 0;
                if(h1 < h2) {
                    area = width * h1;
                } else {
                    area = width + h2;
                }
                max = Math.max(area, max);
            }

            return max;
        }
    }
}
