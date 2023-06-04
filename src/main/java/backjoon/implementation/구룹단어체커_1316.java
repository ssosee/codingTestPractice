package backjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 구룹단어체커_1316 {

    static int n;
    public static void main(String[] args) throws IOException {
        /**
         * 1. 단어가 1개만 있으면 그룹단어
         * 2. 단어가 연속으로 있다가 끊어지면 그룹단어가 아님
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        List<String> arr = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            arr.add(br.readLine());
        }

        int count = 0;
        for(String str : arr) {
            if(check(str)) {
                count++;
            }
        }

        System.out.print(count);
        br.close();
    }

    private static boolean check(String str) {
        // 알파벳은 26개
        boolean[] check = new boolean[26];
        char prevCh = ' ';

        for(int i = 0; i < str.length(); i++) {
            char currentCh = str.charAt(i);

            // 현재 문자와 이전 문자가 다르면
            if(prevCh != currentCh) {
                // 문자가 처음 나온 경우
                if(!check[currentCh - 'a']) {
                    check[currentCh - 'a'] = true;
                    prevCh = currentCh;
                }
                // 처음이 아닌경우 연속된 문자가 아님
                else {
                    return false;
                }
            }
        }
        return true;
    }
}
