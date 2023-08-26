package toss_assistant_algorithm_study.season2.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href="https://yabmoons.tistory.com/536">참고</a>
 */
public class 타일_채우기 {

    /**
     * 아이디어
     * 1. DP를 이용하여 해결한다.
     * 2. n이 짝수인 경우만 타일 채우기 가능
     * 3. n이 4일 때부터 특별한 모양 2개가 존재
     *
     * 1) n = 2 -> 3
     *
     * 2) n = 4 -> 타일(3*4) -> 타일(3*2) + 타일(3*2) + 특이한 타일(2) -> 3 * 3 + 2 = 11
     *
     * 3) n = 6
     *  3-1) 타일(3*4) + 타일(3*2) -> 11 * 3 = 33
     *  3-2) 타일(3*2) + n=4의 특이한 타일(3*4) -> 3 * 2 = 6
     *      33 + 6 + 특이한 타일(2) = 41
     *
     * 4) n = 8
     *  4-1) 타일(3*2) + 타일(3*6) -> 3 * 41 = 123
     *  4-2) 타일(3*4) + 특이한 타일(3*4) -> 11 * 2 = 22
     *  4-3) 타일(3*2) + 특이한 타일(3*6) -> 3 * 2 = 6
     *      따라서 4-1, 4-2, 4-3 합치면 123 + 22 + 6 + 특이한 타일(2) = 153
     *  tile(8) = tile(6) * 3 + tile(4) * 2 + tile(2) * 2 + tile(0) * 2
     *
     *  자료구조
     *  배열
     *
     *  시간복잡도
     *  O(N^2)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] tile = new int[n + 1];

        // 홀수이면
        if(n % 2 == 1) {
            System.out.print(0);
            br.close();
            return;
        }

        // n = 0 이면 타일을 안쓰면 되는 경우의 수 1개라고 가정~
        tile[0] = 1;
        for(int i = 2; i <= n; i+=2){
            tile[i] = tile[i-2] * 3;
            for(int j = i-4; j >= 0; j-=2){
                tile[i] += tile[j] * 2;
            }
        }

        System.out.print(tile[n]);
        br.close();
    }
}
