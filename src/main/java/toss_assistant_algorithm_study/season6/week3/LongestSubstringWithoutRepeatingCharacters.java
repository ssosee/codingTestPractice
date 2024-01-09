package toss_assistant_algorithm_study.season6.week3;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("abcabcbb"));
    }

    /**
     * 아이디어
     * - 반복되는 문자가 없는 문자열 중에 가장 긴 녀석을 구해야한다.
     * - loop를 돌면서 순차적으로 문자를 탐색
     * - 투포인트를 사용한다.(현재 인덱스, 이전 인덱스)
     * - 이전에 만난 문자가 있으면, lengthOfLongestSubstring를 계산한다.
     *
     *  s = "abcabcbb"
     *  1. a    a(defaultIndex)는 만난적이 없었다. a(defaultIndex)를 저장하자     ->  a        defaultIndex = 0, deleteIndex = 0
     *  2. b    b(defaultIndex)는 만난적이 없었다. a(defaultIndex)를 저장하자     ->  ab       defaultIndex = 1, deleteIndex = 0
     *  3. c    c(defaultIndex)는 만난적이 없었다. a(defaultIndex)를 저장하자     ->  abc      defaultIndex = 2, deleteIndex = 0
     *  4. a    a(defaultIndex)는 만난적이 있다. a(deleteIndex)를 제거하자       ->  bc       defaultIndex = 3, deleteIndex = 0
     *  5. a    a(defaultIndex)는 만난적이 없었다. a(defaultIndex)를 저장하자     ->  bca      defaultIndex = 3, deleteIndex = 1
     *  6. b    b(defaultIndex)는 만난적이 있다. b(deleteIndex)를 삭제하자       ->  ca       defaultIndex = 4, deleteIndex = 1
     *  7. b    b(defaultIndex)는 만난적이 없었다. b(defaultIndex)를 저장하자     ->  cab      defaultIndex = 4, deleteIndex = 2
     *  7. c    c(defaultIndex)는 만난적이 있다. c(deleteIndex)를 삭제하자       ->  ab       defaultIndex = 5, deleteIndex = 2
     *  8. c    c(defaultIndex)는 만난적이 없었다. c(deleteIndex)를 저장하자      ->  abc      defaultIndex = 5, deleteIndex = 3
     *  9. b    b(defaultIndex)는 만난적이 있다. a(deleteIndex)를 삭제하자       ->  bc       defaultIndex = 6, deleteIndex = 3
     *  9. b    b(defaultIndex)는 만난적이 있다. b(deleteIndex)를 삭제하자       ->  c        defaultIndex = 6, deleteIndex = 4
     *  10. b    b(defaultIndex)는 만난적이 없었다. b(defaultIndex)를 저장하자    ->  cb       defaultIndex = 6, deleteIndex = 5
     *  11. b    b(defaultIndex)는 만난적이 있다. c(deleteIndex)를 삭제하자      ->  b        defaultIndex = 7, deleteIndex = 5
     *  12. b    b(defaultIndex)는 만난적이 있다. b(deleteIndex)를 삭제하자      ->           defaultIndex = 7, deleteIndex = 6
     *  13. b    b(defaultIndex)는 만난적이 없었다. b(defaultIndex)를 저장하자   ->   b        defaultIndex = 7, deleteIndex = 7
     *
     *
     * 자료구조
     * - Set
     *
     * 
     * 시간복잡도
     *  - O(N)
     *  
     */
    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            int defaultIndex = 0;  // 기본 시작점
            int deleteIndex = 0; // 제거용 시작점
            int lengthOfLongestSubstring = 0;
            Set<Character> subStrings = new HashSet<>();

            while (defaultIndex < s.length()) {
                char ch = s.charAt(defaultIndex);
                // 문자를 갖고 있지 않으면(만난적 없으면)
                if(!subStrings.contains(ch)) {
                    // 만난적 없으면 저장
                    subStrings.add(ch);
                    defaultIndex++;
                    lengthOfLongestSubstring =  Math.max(subStrings.size(), lengthOfLongestSubstring);
                } else {
                    // 만난적 있으면 subString의 deleteIndex에서 부터 제거
                    subStrings.remove(s.charAt(deleteIndex++));
                }
            }

            return lengthOfLongestSubstring;
        }
    }
}
