package baekjoon.datastructure.array_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Quiz1546 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int size = Integer.parseInt(br.readLine());
//        String score = br.readLine();
//
//        // " "으로 문자열 나누기
//        String[] scoreArr = score.split(" ");
//        if(size != scoreArr.length) throw new IllegalArgumentException();
//        System.out.println(getAvgNewScore1(scoreArr));

        int size = Integer.parseInt(br.readLine());
        int[] score = new int[size];
        // StringTokenizer 객체 선언
        // "1 3 5 7" 식으로 공란 포함 String Line일시 StringTokenizer 이용
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < size; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(getAvgNewScore2(score));
    }

    public static float getAvgNewScore1(String[] score) {
        // String to Integer
        List<Integer> intArrayScore = Arrays.stream(score)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        // 최댓값 구하기
        int maxScore = intArrayScore.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow(NoSuchElementException::new);

        // 합 구하기
        int sum = intArrayScore.stream()
                .mapToInt(Integer::intValue)
                .sum();

        // 일일이 변환 점수를 구하지 말고 총합을 이용해서 평균점수를 구함
        return  (sum * 100f / maxScore) / intArrayScore.size();
    }

    public static float getAvgNewScore2(int[] score) {
        long sum = 0;
        long max = 0;

        // max와 sum 계산
        for(int i = 0; i < score.length; i++) {
            if(score[i] > max) {
                max = score[i];
            }
            sum += score[i];
        }

        return (sum * 100f / max) / score.length;
    }
}
