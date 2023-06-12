package baekjoon.datastructure.slidingwindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Quiz12891 {
    /**
     * 9 8
     * CCTGGATTG
     * 2 0 1 1
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int result = 0;
        int count = 0;

        // 문자열 길이
        int len = Integer.parseInt(st.nextToken());

        // 부분 문자열 길이
        int partLen = Integer.parseInt(st.nextToken());

        // 랜덤 문자열
        String str = br.readLine();

        // 필수 문자 갯수 배열
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] == 0) count++;
        }

        int[] newArr = new int[4];
        char[] ch = str.toCharArray();
        for (int i = 0; i < partLen; i++) {
            switch (ch[i]) {
                case 'A':
                    newArr[0]++;
                    if (newArr[0] == arr[0]) count++;
                    break;
                case 'C':
                    newArr[1]++;
                    if (newArr[1] == arr[1]) count++;
                    break;
                case 'G':
                    newArr[2]++;
                    if (newArr[2] == arr[2]) count++;
                    break;
                case 'T':
                    newArr[3]++;
                    if (newArr[3] == arr[3]) count++;
                    break;
            }
        }

        if(count == 4) result++;

        /**
         * 슬라이딩 윈도우
         * i = start point
         * j = end point
         */
        for(int i = partLen; i < len; i++) {
            int j = i - partLen;
            // 윈도우 범위에 새롭게 들어간 값 추가
            switch (ch[i]) {
                case 'A':
                    newArr[0]++;
                    if (newArr[0] == arr[0]) count++;
                    break;
                case 'C':
                    newArr[1]++;
                    if (newArr[1] == arr[1]) count++;
                    break;
                case 'G':
                    newArr[2]++;
                    if (newArr[2] == arr[2]) count++;
                    break;
                case 'T':
                    newArr[3]++;
                    if (newArr[3] == arr[3]) count++;
                    break;
            }

            // 윈도우 범위에 벗어난 값 제거
            switch (ch[j]) {
                case 'A':
                    if (newArr[0] == arr[0]) count--;
                    newArr[0]--;
                    break;
                case 'C':
                    if (newArr[1] == arr[1]) count--;
                    newArr[1]--;
                    break;
                case 'G':
                    if (newArr[2] == arr[2]) count--;
                    newArr[2]--;
                    break;
                case 'T':
                    if (newArr[3] == arr[3]) count--;
                    newArr[3]--;
                    break;
            }
            if(count == 4) result++;
        }
        System.out.println(result);
    }
}
