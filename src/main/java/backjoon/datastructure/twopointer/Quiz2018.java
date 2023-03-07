package backjoon.datastructure.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz2018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 배열의 크기
        int num = Integer.parseInt(br.readLine());

        // 연속된 자연수의 배열
        int[] numArr = new int[num];
        for(int i = 0; i < num; i++) {
            numArr[i] = i+1;
        }

        System.out.println(getCount(num, numArr));
    }

    private static int getCount(int num, int[] numArr) {
        int count = 1; // 결과
        int sum = 0; // 합
        int sp = 0; // 시작 포인터
        int ep = 0; // 엔드 포인터

        // ep가 num이 되기전에 탈출 하기 때문에
        // count를 1로 초기화 한다.(자기 자신도 count하기 때문)
        while (ep != num) {
            // 카운트 증가, sum에 엔드 포인터의 값 더하기
            if (sum == num) {
                sum += numArr[ep++];
                count++;
            }
            // sum에 시작 포인터의 값 빼주기
            else if (sum > num) {
                sum -= numArr[sp];
                sp++;
            }
            // sum에 엔드 포인터 값 더하기
            else if (sum < num) {
                sum += numArr[++ep];
            }
        }
        return count;
    }
}
