package programmers.level2;

import java.util.ArrayList;
import java.util.List;

public class 점_찍기 {
    public static void main(String[] args) {
        //System.out.println(solution(2, 4));
        System.out.println(solution(1, 5));
    }

    static public long solution(int k, int d) {
        long answer = 0;

        for(long x = 0; x <= d; x += k) {
            // 최대로 가질수 있는 y 값
            // x^2 + y^2 = d^2 -> y^2 = d^2 - x^2
            long maxY = (long) Math.sqrt(Math.pow(d, 2) - Math.pow(x, 2));
            // y에서 k배 단위로만 점을 찍을 수 있기 때문에 k로 나눈다.
            // 1을 더하는 이유는 x가 0인 (0, y)를 고려한다.
            answer += (maxY / k) + 1;
        }

        return answer;
    }
}
