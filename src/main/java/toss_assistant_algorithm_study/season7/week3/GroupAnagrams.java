package toss_assistant_algorithm_study.season7.week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.groupAnagrams(new String[]{
                "eat","tea","tan","ate","nat","bat"}
        ));
    }

    /**
     * 아이디어
     * - 아나그램이란?
     *  - 알파벳을 조합하여 만들 수 있는 글자들끼리 집합을 만든다.
     *  - ["eat","tea","tan","ate","nat","bat"]
     *      - 배열의 첫 번째 요소 'e','a','t'로 만들 수 있는 문자열
     *      - "eat", "tea", "ate"
     *      - 배열의 세 번째 요소 't','a','n'로 만들 수 있는 문자열
     *      - "tan", "nat"
     */
    static class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> results = new HashMap<>();
            for(int i = 0; i < strs.length; i++) {
                // 원래 문자열
                String originStr = strs[i];
                char[] chars = strs[i].toCharArray();
                // 정렬
                Arrays.sort(chars);
                // 정렬된 문자열
                String orderedChars = new String(chars);

                // 정렬된 문자열이 있으면
                if(results.containsKey(orderedChars)) {
                    // 정렬 문자열을 key로 하는 원본 문자열 저장
                    results.get(orderedChars).add(originStr);
                }
                // 없으면 신규 추가
                else {
                    results.put(orderedChars, new ArrayList<>());
                    results.get(orderedChars).add(originStr);
                }
            }

            return new ArrayList<>(results.values());
        }
    }
}
