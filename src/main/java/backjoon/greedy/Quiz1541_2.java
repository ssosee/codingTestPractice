package backjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz1541_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        // -를 만나기 전에는 다 더하고, -를 만나면 이후에는 다 빼면 된다.

        // -앞 문자열, -뒤 문자열로 나눔
        // 55+10-50+40-30 -> [50+10, 55+40, 30]
        String[] strArr = str.split("-");
        int result = 0;
        // -를 만나면 크기가 2이상인 배열이 된다.
        for(int i = 0; i < strArr.length; i++) {
            int temp = mySum(strArr[i]);
            // 첫 문자열은 더함(-를 만나기 전 문자열)
            if(i == 0) result += temp;
            // 이후 문자열은 뺀다(이후 문자열은 -를 만났기 때문에 빼야한다.)
            else result -= temp;
        }

        System.out.print(result);
    }

    private static int mySum(String s) {
        int sum = 0;
        // +를 기준으로 문자열을 나눈다.
        // 50+40 -> [50, 40] -> 90
        String[] temp = s.split("[+]");
        for(String str : temp) {
            sum += Integer.parseInt(str);
        }
        return sum;
    }
}
