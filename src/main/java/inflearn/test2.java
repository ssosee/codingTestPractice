package inflearn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <h1>대소문자 변환</h1>
 * <p>대문자와 소문자가 같이 존재하는 문자열을 입력받아 대문자는 소문자로 소문자는 대문자로 변환하여 출력하는 프로그램을 작성하세요.</p>
 */
public class test2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println(solution(input));
    }

    public static String solution(String input) {
        String answer = "";


        for(char x : input.toCharArray()) {
            if(Character.isUpperCase(x)) {
                answer += Character.toLowerCase(x);
            }
            else {
                answer += Character.toUpperCase(x);
            }
        }


        return answer;
    }

    /***
     * ASCII 활용
     * 대문자: 65 ~ 90
     * 소문자: 97 ~ 122
     * 소문자와 대문자의 차이 = 32
     */
    public static String solution2(String input) {
        String answer = "";

        for(char x : input.toCharArray()) {
            if (x >= 65 && x <= 90) answer += (char) (x + 32);
            else answer += (char) (x - 32);
        }

        return answer;
    }
}
