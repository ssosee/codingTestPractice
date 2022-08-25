package inflearn.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * <h1>회문 문자열</h1>
 * <p>앞에서 읽을 때나 뒤에서 읽을 때나 같은 문자열을 회문 문자열이라고 합니다.
 * 문자열이 입력되면 해당 문자열이 회문 문자열이면 "YES", 회문 문자열이 아니면 “NO"를 출력하는 프로그램을 작성하세요.</p>
 */
public class test7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(solution(str));
    }

    public static String solution(String str) {
        String answer = "NO";

        //대소문자 구분을 안하기 위함
        str = str.toUpperCase();

        String reverse = String.valueOf(new StringBuilder(str).reverse());

        if(reverse.equals(str)) return "YES";

        return answer;
    }

    /***
     * 강의 풀이 1
     */
    public static String solution2(String str) {
        String answer = "YES";
        int len = str.length();
        str = str.toUpperCase();
        str.split(" ");

        for(int i = 0; i < len/2; i++) {
            if(str.charAt(i) != str.charAt(len - i - 1)) {
                return "NO";
            }
        }

        return answer;
    }

    /***
     * 강의 풀이 2
     */
    public static String solution3(String str) {
        String answer = "NO";
        String tmp = new StringBuilder(str).reverse().toString();

        if(str.equalsIgnoreCase(tmp)) return "YES";

        return answer;
    }
}
