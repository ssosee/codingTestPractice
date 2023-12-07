package toss_assistant_algorithm_study.season5.week3;

import java.util.Arrays;

public class HIndex {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //System.out.println(solution.hIndex(new int[]{3,0,6,1,5}));
        System.out.println(solution.hIndex(new int[]{1,3,1}));
        System.out.println(solution.hIndex(new int[]{100,101,102,103,104,105}));
        System.out.println(solution.hIndex(new int[]{200,105,103,102,101,100}));
    }

    /**
     * 아이디어
     * - 어떤 과학자가 발표한 논문 n편 중,
     *   h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용 되었다면
     *   h의 최댓값이 이 과학자의 H-Index입니다.
     *   즉, h번 이상 인용된 논문이 h편 이상인 h를 구하자.
     *
     *   [100, 101, 102, 103, 104, 105]
     *   1) 0번 이상 인용된 논문이 0(6)편 이상? && 나머지 논문이 0번 이하 인용? -> X  나머지 논문: [100, 101, 102, 103, 104, 105]
     *   2) 1번 이상 인용된 논문이 1(6)편 이상? && 나머지 논문이 1번 이하 인용? -> X  나머지 논문: [101, 102, 103, 104, 105]
     *   3) 2번 이상 인용된 논문이 2(6)편 이상? && 나머지 논문이 2번 이하 인용? -> X  나머지 논문: [102, 103, 104, 105]
     *   4) 3번 이상 인용된 논문이 3(6)편 이상? && 나머지 논문이 3번 이하 인용? -> X  나머지 논문: [103, 104, 105]
     *   5) 4번 이상 인용된 논문이 4(6)편 이상? && 나머지 논문이 4번 이하 인용? -> X  나머지 논문: [104, 105]
     *   6) 5번 이상 인용된 논문이 5(6)편 이상? && 나머지 논문이 5번 이하 인용? -> X  나머지 논문: [105]
     *   7) 6번 이상 인용된 논문이 6(6)편 이상? && 나머지 논문이 6번 이하 인용? -> X  나머지 논문: []
     *
     *
     *   [0,1,3,5,6]
     *   1) 0번 이상 인용된 논문 0편 이상? 5개 O
     *   2) 1번 이상 인용된 논문 1편 이상? 4개 O
     *   3) 2번 이상 인용된 논문 2편 이상? 3개 O
     *   4) 3번 이상 인용된 논문 3편 이상? 3개 O    -> 최대
     *   5) 4번 이상 인용된 논문? 4편 이상? 2개 X
     *   6) 5번 이상 인용된 논문? 5편 이상? 2개 X
     *
     *
     *   [1,1,3]
     *   1) 3번 이상 인용된 논문 3편 이상? 1개 X
     *   2) 2번 이상 인용된 논문 2편 이상? 1개 X
     *   3) 1번 이상 인용된 논문 1편 이상? 3개 O    -> 최대
     *   4) 0번 이상 인용된 논문 0편 이상? 3개 O
     *
     *
     *   hIndex를 최대 수치에서 점점 감소시킨다.
     *   논문의 인용 횟수 배열을 오름차순 정렬한다.
     *
     *   논문이 10편이고
     *   [9, ...]
     *   1) hIndex(10) >= 9 -> X
     *   2) hIndex - 1(9) >= ?(9보다 작거나 같겠지)
     *
     *   논문이 10편이고
     *   [11, ...]
     *   1) hIndex(10) >= 11 -> O
     *
     *   즉, hIndex의 최대는 논문의 갯수를 넘어가지 못한다.
     */
    static class Solution {
        public int hIndex(int[] citations) {
            // 오름차순 정렬
            Arrays.sort(citations);

            for(int i = 0; i < citations.length; i++) {
                int h = citations.length - i; // h번 이상 인용된 논문 갯수
                int citation = citations[i];  // i번째 논문의 인용 횟수

                if(citation >= h) {
                    return h;
                }
            }

            return 0;
        }
    }
}
