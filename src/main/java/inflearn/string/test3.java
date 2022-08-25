package inflearn.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <h1>문장 속 단어</h1>
 * <p>한개의 문장이 주어지면 그 문장 속에서 가장 긴 단어를 출력하는 프로그램을 작성 하시오.</p>
 * <p>문장은 공백으로 구분 됩니다.</p>
 * <p>첫 줄에 가장 긴 단어를 출력한다. 가장 길이가 긴 단어가 여러개일 경우 문장속에서 가장 앞쪽에 위치한 단어를 답으로 한다.</p>
 */
public class test3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println(solution(input));
    }

    public static String solution(String input) {
        String answer = "";
        int m = Integer.MIN_VALUE;

        String[] str = input.split(" ");
        for(String s : str) {
            int len = s.length(); //단어의 길이
            if(len > m) {
                m = len;
                answer = s;
            }
        }
        return answer;
    }

    /***
     * indexOf: 참이면 return index, 거짓이면 return -1
     * index: 최초발견 문자열의 index를 의미
     */
    public static String solution2(String input) {
        String answer = "";
        int pos = 0;
        int m = Integer.MIN_VALUE;
        while((pos = input.indexOf(' ')) != -1) {
            String tmp = input.substring(0, pos); //0부터 pos전까지 잘라내기
            int len = tmp.length();
            if(len > m) {
                m = len;
                answer = tmp;
            }
            input = input.substring(pos+1);
        }
        //마지막 단어 처리
        if(input.length() > m) answer = input;
        return answer;
    }
}
