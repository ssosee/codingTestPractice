package inflearn.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * <h1>단어 뒤집기</h1>
 * <p>N개의 단어가 주어지면 각 단어를 뒤집어 출력하는 프로그램을 작성하세요.</p>
 * <p>첫 줄에 자연수 N(3<=N<=20)이 주어집니다.
 * 두 번째 줄부터 N개의 단어가 각 줄에 하나씩 주어집니다. 단어는 영어 알파벳으로만 구성되어 있습니다.</p>
 * <p>N개의 단어를 입력된 순서대로 한 줄에 하나씩 뒤집어서 출력합니다.</p>
 */
public class test4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input1 = br.readLine();
        String[] input2Array = new String[Integer.parseInt(input1)];

        for(int i = 0; i < Integer.parseInt(input1); i++) {
            String input2 = br.readLine();
            input2Array[i] = input2;
        }

        for(String x : solution(Integer.parseInt(input1), input2Array)) {
            System.out.println(x);
        }
    }

    public static ArrayList<String> solution(int n, String[] input2Array) {
        ArrayList<String> answer = new ArrayList<>();

        for(String s : input2Array) {
            String tmp = new StringBuilder(s).reverse().toString();
            answer.add(tmp);
        }

        return answer;
    }

    public static ArrayList<String> solution2(int n, String[] input2Array) {
        ArrayList<String> answer = new ArrayList<>();

        for(String s : input2Array) {
            char[] c = s.toCharArray();
            int lt = 0;
            int rt = s.length() - 1;

            while (lt < rt) {
                char tmp = c[lt];
                c[lt] = c[rt];
                c[rt] = tmp;
                lt++;
                rt--;
            }

            String tmp = String.valueOf(c);
            answer.add(tmp);
        }

        return answer;
    }
}
