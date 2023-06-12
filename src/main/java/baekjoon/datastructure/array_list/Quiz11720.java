package baekjoon.datastructure.array_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz11720 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        // N의 범위가 1 ~ 100(int, long 형 불가)
        // String으로 저장
        String num = br.readLine();

        // String을 char로 변환
        char[] chars = num.toCharArray();
        int sum = 0; // 결과

        for(int i = 0; i < size; i++) {
//            sum += Integer.parseInt(Character.toString(chars[i]));
//            sum += Integer.parseInt(String.valueOf(num.charAt(i)));
            //sum += (num.charAt(i) - 48);
            sum += (chars[i] - 48);
        }

        System.out.println(sum);
    }
}
