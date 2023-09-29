package toss_assistant_algorithm_study.season3;

import java.util.*;

public class RemoveDuplicatesFromSortedArray2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //System.out.println(solution.removeDuplicates(new int[]{1,1,1,2,2,3}));
        System.out.println(solution.removeDuplicates(new int[]{-3,-1,0,0,0,3,3}));
        //System.out.println(solution.removeDuplicates(new int[]{0,0,1,1,1,1,2,3,3}));
    }

    /**
     * 아이디어
     *  - nums[index], nums[index]의 count> 로 TreeMap을 이용
     *  - TreeMap을 이용하면 key값으로 정렬이 가능
     *  - 2개 이상이면 nums에 2번만 할당
     *  - 그외 1번만 nums에 할당
     *
     * 자료구조
     *  - TreeMap
     *
     * 시간복잡도
     *  O(N)
     */
    static class Solution {
        public int removeDuplicates(int[] nums) {
            Map<Integer, Integer> map = new TreeMap<>();
            for(int i = 0; i < nums.length; i++) {
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            }

            Set<Integer> keys = map.keySet();
            int index = 0;
            for(Integer key : keys) {
                // 최대 2개 까지 중복 허용
                if(map.get(key) >= 2) {
                    for(int i = 0; i < 2; i++) {
                        nums[index++] = key;
                    }
                } else {
                    nums[index++] = key;
                }
            }

            return index;
        }
    }
}
