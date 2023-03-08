package backjoon.datastructure.slidingwindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Quiz12891 {
    /**
     * 9 8
     * CCTGGATTG
     * 2 0 1 1
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정답
        int result = 0;
        // 4가 되면 result를 1 증가
        int checkSecret = 0;

        // 문자열의 크기
        int S = Integer.parseInt(st.nextToken());

        // 부분 문자열의 크기
        int P = Integer.parseInt(st.nextToken());

        // 문자열 데이터
        char[] A = br.readLine().toCharArray();

        // 비밀번호 체크 배열
        int[] checkArr = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            checkArr[i] = Integer.parseInt(st.nextToken());
            if (checkArr[i] == 0) checkSecret++;
        }

        // 현재상태 배열
        int[] myArr = new int[4];

        for (int i = 0; i < P; i++) {
            switch (A[i]) {
                case 'A':
                    myArr[0]++;
                    if (myArr[0] == checkArr[0]) checkSecret++;
                    break;
                case 'C':
                    myArr[1]++;
                    if (myArr[1] == checkArr[1]) checkSecret++;
                    break;
                case 'G':
                    myArr[2]++;
                    if (myArr[2] == checkArr[2]) checkSecret++;
                    break;
                case 'T':
                    myArr[3]++;
                    if (myArr[3] == checkArr[3]) checkSecret++;
                    break;
            }
        }

        if (checkSecret == 4) result++;
        /**
         * 슬라이딩 윈도우
         * i = start point
         * j = end point
         */
        for (int i = P; i < S; i++) {
            int j = i - P;
            switch (A[i]) {
                case 'A':
                    myArr[0]++;
                    if (myArr[0] == checkArr[0]) checkSecret++;
                    break;
                case 'C':
                    myArr[1]++;
                    if (myArr[1] == checkArr[1]) checkSecret++;
                    break;
                case 'G':
                    myArr[2]++;
                    if (myArr[2] == checkArr[2]) checkSecret++;
                    break;
                case 'T':
                    myArr[3]++;
                    if (myArr[3] == checkArr[3]) checkSecret++;
                    break;
            }

            switch (A[j]) {
                case 'A':
                    if (myArr[0] == checkArr[0]) checkSecret--;
                    myArr[0]--;
                    break;
                case 'C':
                    if (myArr[1] == checkArr[1]) checkSecret--;
                    myArr[1]--;
                    break;
                case 'G':
                    if (myArr[2] == checkArr[2]) checkSecret--;
                    myArr[2]--;
                    break;
                case 'T':
                    if (myArr[3] == checkArr[3]) checkSecret--;
                    myArr[3]--;
                    break;
            }
            if(checkSecret == 4) result++;
        }

        System.out.println(result);
        br.close();
    }
}
