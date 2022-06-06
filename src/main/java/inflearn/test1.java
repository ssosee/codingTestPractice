package inflearn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <h1>문자 찾기</h1>
 * <p>한 개의 문자열을 입력받고, 특정 문자를 입력받아 해당 특정문자가 입력받은 문자열에 몇 개 존재하는지 알아내는 프로그램을 작성하세요.</p>
 */
public class test1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        char input2 = br.readLine().charAt(0);
        System.out.println(solution(input, input2));
    }

    public static int solution(String str, char t) {

        int answer = 0;
        str = str.toUpperCase();
        t = Character.toUpperCase(t);

        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == t) {
                answer++;
            }
        }

        return answer;
    }

    public static int solution2(String str, char t) {

        int answer = 0;
        str = str.toUpperCase();
        t = Character.toUpperCase(t);

        for(char x : str.toCharArray()) {
            if(x == t) answer++;
        }

        return answer;
    }
}
