package backjoon.sort.selection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz1427 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();

        // 선택 정렬을 이용하여 내림차순으로 정렬
        char[] ch = n.toCharArray();
        char max = 0;
        int index = 0;
        for(int i = 0; i < ch.length; i++) {
            for(int j = i; j < ch.length; j++) {
                if(ch[j] > max) {
                    max = ch[j];
                    index = j;
                }
            }
            char temp = ch[i];
            ch[i] = max;
            ch[index] = temp;
            max = 0;
        }

        StringBuilder sb = new StringBuilder();
        for(char c : ch) {
            sb.append(c);
        }
        System.out.println(sb);
    }
}
