package programmers.levle1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/92334">
 *     신고 결과 받기
 *     </a>
 */
public class KakaoReceiveReportResult {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new String[]{"muzi", "frodo", "apeach", "neo"}, new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"}, 2);
    }

    public static class Solution {
        public int[] solution(String[] id_list, String[] report, int k) {
            int[] answer = new int[id_list.length];
            //유저별 신고자 목록 map
            Map<String, HashSet<String>> userReportInfo = new HashMap<>();
            //user를 index로 맵핑
            Map<String, Integer> user = new HashMap<>();
            for (int i = 0; i < id_list.length; i++) {
                user.put(id_list[i], i);
                //초기화
                userReportInfo.put(id_list[i], new HashSet<>());
            }
            //유저별 신고자 목록
            for (int i = 0; i < report.length; i++) {
                String[] splitResult = report[i].split(" ");
                String reporter = splitResult[0];
                String reported = splitResult[1];
                userReportInfo.get(reported).add(reporter);
            }
            //회원들 신고 당한 횟수 목록
            for (int i = 0; i < id_list.length; i++) {
                //k번 이상 신고 당했을 경우
                if (userReportInfo.get(id_list[i]).size() >= k) {
                    for(String name : userReportInfo.get(id_list[i])) {
                        answer[user.get(name)]++;
                    }
                }
            }

            return answer;
        }
    }
}
