package inflearn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <h1>특정 문자 뒤집기</h1>
 * <p>영어 알파벳과 특수문자로 구성된 문자열이 주어지면 영어 알파벳만 뒤집고,</p>
 * <p>특수문자는 자기 자리에 그대로 있는 문자열을 만들어 출력하는 프로그램을 작성하세요.</p>
 */
public class test5 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(solution(str));
    }

    public static String solution(String str) {
        String answer = "";

        char[] c = str.toCharArray();

        int lt = 0;
        int rt = str.length() - 1;

        while (lt < rt){
            if(!Character.isAlphabetic(c[lt])) {
                lt++;
            }
            else if(!Character.isAlphabetic(c[rt])) {
                rt--;
            }
            else {
                char temp = c[lt];
                c[lt] = c[rt];
                c[rt] = temp;
                lt++;
                rt--;
            }
        }
        answer = String.valueOf(c);

        return answer;
    }
}
