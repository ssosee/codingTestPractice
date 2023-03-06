package dev;

import javafx.scene.SubScene;

import java.util.*;

public class test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(new Solution().getAnswer(s));
    }

    static class Solution {
        public String getAnswer(String s) {
            //첫문자는 무조건 표시
            String answer = String.valueOf(s.charAt(0));
            //아스키 'A' = 97
            int cnt0 = 0;
            int cnt1 = 0;
            for(char ch : s.toCharArray()) {
                if(ch == '1') {
                    if(cnt0 > 0) {
                        answer += (char) (cnt0+96);
                        cnt0 = 0;
                    }
                    cnt1++;
                } else if(ch == '0') {
                    if(cnt1 > 0) {
                        answer += (char) (cnt1+96);
                        cnt1 = 0;
                    }
                    cnt0++;
                }
            }

            if(cnt0 > 0) {
                answer += (char) (cnt0+96);
                cnt0 = 0;
            } else if(cnt1 > 0) {
                answer += (char) (cnt1+96);
                cnt1 = 0;
            }

            return answer.toUpperCase();
        }
    }
}
