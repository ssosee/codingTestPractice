package toss_assistant_algorithm_study.season1.week1;

import java.util.*;

public class 뉴스_클러스터링 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("FRANCE", "french"));
        //System.out.println(solution.solution("handshake", "shake hands"));
        // System.out.println(solution.solution("E=M*C^2", "e=m*c^2"));
    }

    static class Solution {
        public int solution(String str1, String str2) {
            /**
             * 자카드 유사도
             * A = {1, 2, 3}, B = {2, 3, 4}
             * A and B = {2, 3}, A or B = {1, 2, 3, 4}
             * J(A, B) = 2/4 = 0.5
             * 단, x/0 = 1
             *
             * A = {1, 1, 2, 2, 3}, B = {1, 2, 2, 4, 5}
             * A and B = {1, 2, 2}, A or B = {1, 1, 2, 2, 3, 4, 5}
             * J(A, B) = 3/7 = 0.42
             *
             * A = {FR, RA, AN, NC, CE}, B = {FR, RE, EN, NC, CH}
             * A and B = {FR, NC}, A or B = {FR, RA, AN, NC, CE, RE, EN, CH}
             * J(A, B) = 2/8 = 0.25
             *
             * 1. 문자열을 2글자씩 끊어서 집합을 만든다.(영문자로 된 글자만 유효, 대소문자 무시)
             * 2. 교집합, 합집합을 구한다.
             * 3. 자카드 유사도 계산 * 65536
             */
            int answer = 0;

            str1 = str1.toLowerCase();
            str2 = str2.toLowerCase();

            // 주어진 문자열을 2글자씩 끊어서 집합을 만든다.
            List<String> groupA = createGroup(str1);
            List<String> groupB = createGroup(str2);

            // 비교를 위해 오름차순 정렬
            Collections.sort(groupA);
            Collections.sort(groupB);

            // 교집합 생성
            List<String> andGroup = new ArrayList<>();
            // 합집합 생성
            List<String> orGroup = new ArrayList<>();

            /**
             * A = {1, 1, 2, 2, 3}, B = {1, 2, 2, 4, 5}
             * A and B = {1, 2, 2}, A or B = {1, 1, 2, 2, 3, 4, 5}
             * J(A, B) = 3/7 = 0.42
             */
            for(String a : groupA) {
                // A집합과 B집합에 같은 원소가 있으면
                if(groupB.remove(a)) {
                    // 교집합에 추가
                    andGroup.add(a);
                }
                // 합집합에 추가
                orGroup.add(a);
            }

            // 나머지를 합집합에 추가
            for(String b : groupB) {
                orGroup.add(b);
            }

            if(orGroup.isEmpty()) return 65536;

            float result = (float) andGroup.size() / orGroup.size() * 65536;
            answer = (int) result;
            return answer;
        }


        private List<String> createGroup(String str) {
            List<String> stringList = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if(Character.isAlphabetic(c)) {
                    sb.append(c);
                    if(sb.length() == 2) {
                        stringList.add(sb.toString());
                        sb.setLength(0);
                        i--;
                    }
                }
                else {
                    sb.setLength(0);
                }
            }

            return stringList;
        }
    }
}
