package programmers.levle1;

import java.util.HashMap;
import java.util.Map;

public class KakaoMBTI {

    private static final String R = "R";
    private static final String T = "T";
    private static final String C = "C";
    private static final String F = "F";
    private static final String J = "J";
    private static final String M = "M";
    private static final String A = "A";
    private static final String N = "N";

    public static void main(String[] args) {
        String result = solution(new String[]{"AN", "CF", "MJ", "RT", "NA"}, new int[]{5, 3, 2, 7, 5});
        System.out.println(result);
    }

    public static String solution(String[] survey, int[] choices) {

        Member member = new Member();
        member.initMemberMbtiScore();

        MemberService memberService = new MemberService(member);
        Map<String, Integer> mbtiScoreResult = memberService.calculateMbtiScore(survey, choices);

        return memberService.extractMbti(mbtiScoreResult);
    }

    static class Member {
        private Map<String, Integer> mbtiTypeScore = new HashMap<>();
        private String mbti;

        public void initMemberMbtiScore() {
            this.mbtiTypeScore.put(R, 0);
            this.mbtiTypeScore.put(T, 0);
            this.mbtiTypeScore.put(C, 0);
            this.mbtiTypeScore.put(F, 0);
            this.mbtiTypeScore.put(J, 0);
            this.mbtiTypeScore.put(M, 0);
            this.mbtiTypeScore.put(A, 0);
            this.mbtiTypeScore.put(N, 0);
        }

        public Map<String, Integer> getMbtiTypeScore() {
            return mbtiTypeScore;
        }

        public String getMbti() {
            return mbti;
        }

        public void setMbti(String mbti) {
            this.mbti = mbti;
        }
    }
    static class MemberService {
        private Member member;

        public MemberService(Member member) {
            this.member = member;
        }

        public Map<String, Integer> calculateMbtiScore(String[] survey, int[] choices) {

            for(int i = 0; i < survey.length; i++) {
                String firstType = String.valueOf(survey[i].charAt(0));
                String lastType = String.valueOf(survey[i].charAt(1));
                //4점보다 작으면
                if(choices[i] < 4) {
                    Integer score = member.getMbtiTypeScore().get(firstType);
                    member.getMbtiTypeScore().put(firstType, score + (4 - choices[i]));
                }
                //4점보다 크면
                else if(choices[i] > 4) {
                    Integer score = member.getMbtiTypeScore().get(lastType);
                    member.getMbtiTypeScore().put(lastType, score + (choices[i] - 4));
                }
            }

            return member.getMbtiTypeScore();
        }

        public String extractMbti(Map<String, Integer> mbtiScoreResult) {

            String result="";

            if(mbtiScoreResult.get(T) > mbtiScoreResult.get(R)) {
                result += T;
            } else {
                result += R;
            }
            if(mbtiScoreResult.get(F) > mbtiScoreResult.get(C)) {
                result += F;
            } else {
                result += C;
            }
            if(mbtiScoreResult.get(M) > mbtiScoreResult.get(J)) {
                result += M;
            } else {
                result += J;
            }
            if(mbtiScoreResult.get(N) > mbtiScoreResult.get(A)) {
                result += N;
            } else {
                result += A;
            }

            member.setMbti(result);
            return member.getMbti();
        }
    }
}
