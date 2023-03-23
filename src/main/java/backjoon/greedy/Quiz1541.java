package backjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        char[] ch = str.toCharArray();

        // - 뒤에 - 이면 그대로 둔다.
        // - 뒤에 + 이면 -로 변경
        // 10-10-10+10-10 -> 10-10-(10+10)-10 -> 10-10-10-10
        char frontCh = ' ';
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ch.length; i++) {
            // 숫자이면 저장
            if(ch[i] != '-' && ch[i] != '+') {
                sb.append(ch[i]);
            }
            // 부호를 만나면 저장
            else {
                // 앞전에 부호이 -이고 현재 숫자가 아니면 빼준다.
                if(frontCh == '-' && !Character.isDigit(ch[i])) {
                    sum -= Integer.parseInt(sb.toString());
                }
                // 마이너스를 만나지 않으면 계속 더한다.
                else {
                    // 마이너스 저장
                    frontCh = ch[i];
                    // 앞에 숫자 계산
                    sum += Integer.parseInt(sb.toString());
                }
                // 초기화
                sb.setLength(0);
            }
        }

        if(frontCh == '-') {
            sum -= Integer.parseInt(sb.toString());
        } else {
            sum += Integer.parseInt(sb.toString());
        }
        sb.setLength(0);
        System.out.print(sum);
        br.close();
    }
}
