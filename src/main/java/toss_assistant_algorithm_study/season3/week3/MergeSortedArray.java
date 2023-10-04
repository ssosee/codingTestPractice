package toss_assistant_algorithm_study.season3.week3;

import java.util.Arrays;

public class MergeSortedArray {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //solution.merge(new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3);
        solution.merge2(new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3);
    }

    static class Solution {

        public void merge2(int[] nums1, int m, int[] nums2, int n) {
            int index = m + n - 1;
            int i = m - 1;
            int j = n - 1;

            while (i >= 0 && j >= 0) {
                if(nums1[i] < nums2[j]) nums1[index--] = nums2[j--];
                else nums1[index--] = nums1[i--];
            }

            while (j >= 0) {
                nums1[index--] = nums2[j--];
            }
        }


        public void merge(int[] nums1, int m, int[] nums2, int n) {
            for(int i = m; i < nums1.length; i++) {
                nums1[i] = nums2[i - m];
            }

            Arrays.sort(nums1);
        }
    }
}
