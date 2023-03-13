package backjoon.sort.insertion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz1427 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();

        // 삽입 정렬을 이용하여 내림차순으로 정렬
        char[] ch = n.toCharArray();
        /**
         * 타겟 설정
         * 타켓값과 비교 대상 범위의 값과 비교하며 shift 연산
         */
        for(int i = 1; i < ch.length; i++) {
            int index = i;
            char target = ch[index];
            while (index > 0 && target > ch[index-1]) {
                // [7, 3, 5] -> [7, 5, 3]
                ch[index] = ch[index - 1]; // shift
                index--;
            }
            ch[index] = target;
        }

        StringBuilder sb = new StringBuilder();
        for(char c : ch) {
            sb.append(c);
        }
        System.out.println(sb);
    }
}
