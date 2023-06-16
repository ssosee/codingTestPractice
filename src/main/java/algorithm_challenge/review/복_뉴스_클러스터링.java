package algorithm_challenge.review;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 복_뉴스_클러스터링 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int solution1 = solution.solution("FRANCE", "french");
        System.out.println(solution1);
    }

    static class Solution {
        /**
         * 같은 원소
         * 교집합: min(3, 5)
         * 합집합: max(3, 5)
         */
        public int solution(String str1, String str2) {
            int answer = 0;

            List<String> group1 = createGroup(str1);
            List<String> group2 = createGroup(str2);

            // 비교를 위해 정렬
            Collections.sort(group1);
            Collections.sort(group2);

            // 교집합, 합집합
            List<String> same = new ArrayList<>();
            List<String> sum = new ArrayList<>();

            for(String s : group1) {
                // group1과 group2에 같은 원소가 있으면
                if(group2.remove(s)) {
                    same.add(s);
                }
                sum.add(s);
            }
            for(String s : group2) {
                sum.add(s);
            }

            if(sum.size() == 0)  return  65536;

            float result = (float) same.size() / sum.size() * 65536;

            return (int) result;
        }

        private List<String> createGroup(String s) {

            List<String> group = new ArrayList<>();

            s = s.toUpperCase();
            char[] chars = s.toCharArray();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < chars.length; i++) {
                // 알파벳인 경우
                if(Character.isAlphabetic(chars[i])) {
                    sb.append(chars[i]);
                    int length = sb.length();
                    if(length % 2 == 0) {
                        group.add(sb.toString());
                        sb.setLength(0);
                        i--;
                    }
                } else {
                    sb.setLength(0);
                }
            }
            return group;
        }
    }
}
