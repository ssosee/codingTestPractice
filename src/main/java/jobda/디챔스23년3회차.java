package jobda;

import test.equals_hashcode.Person;

import java.util.*;

public class 디챔스23년3회차 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String result = solution.solution(8, new int[]{90, 55, 58, 60, 42, 75, 74, 30}, new int[]{70, 75, 88, 88, 88, 79, 80, 60}, 50);
        System.out.println(result);
    }

    static class Solution {
        public String solution(int N, int[] A, int[] B, int x) {
            StringBuilder sb = new StringBuilder();
            String answer = "";

            // 중간 기말 합산
            int[] sum = new int[N];
            for(int i = 0; i < N; i++) {
                int a = A[i] * x;
                int b = B[i] * (100 - x);
                sum[i] = (int) ((a + b) * 0.01f);
            }

            // 인접 리스트
            List<Integer>[] list = new ArrayList[10001];
            for(int i = 0; i < 10001; i++) {
                list[i] = new ArrayList<>();
            }

            // 인덱스 정보 저장
            for(int i = 0; i < N; i++) {
                list[sum[i]].add(i);
            }


            Integer[] temp = new Integer[sum.length];
            for (int i = 0; i < sum.length; i++) {
                temp[i] = sum[i];
            }
            Arrays.sort(temp, Collections.reverseOrder()); // 시험 점수를 내림차순 정렬


            // 시험 점수를 기준으로 학생들에게 성적 부여
            int numA = sum.length * 30 / 100; // 전체 학생 수의 30%를 A로 부여할 수 있는 최대 학생 수
            int numB = sum.length * 70 / 100 - numA; // 전체 학생 수의 70% 중 A를 부여한 학생 수를 뺀 나머지를 B로 부여할 수 있는 최대 학생 수

            String[] result = new String[N];
            for (int i = 0; i < N; i++) {
                String grade;
                if (i < numA) {
                    grade = "A";
                } else if (i < numA + numB) {
                    grade = "B";
                } else {
                    grade = "C";
                }

                for(Integer index : list[temp[i]]) {
                    result[index] = grade;
                }
            }

            for(String s : result) {
                sb.append(s);
            }

            return sb.toString();
        }
    }
}
