package backjoon2.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 그룹단어체커_1316 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int count = 0;
        for(int i = 0; i < n; i++) {
            if(check(br.readLine())) {
                count++;
            }
        }

        System.out.print(count);
        br.close();
    }

    private static boolean check(String str) {
        char[] chars = str.toCharArray();
        char prevCh = ' ';
        boolean[] isCh = new boolean[26];

        for(char ch : chars) {
            // 이전 단어와 다르다면
            if(prevCh != ch) {
                // 처음 등장인 경우
                if(!isCh[ch - 'a']) {
                    isCh[ch - 'a'] = true;
                    prevCh = ch;
                } else {
                    // 이전에 등장한 적이 있다.
                    return false;
                }
            }
        }
        return true;
    }
}
