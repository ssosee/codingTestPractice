package algorithm_challenge.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 복_숫자_야구 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        List<Game> games = new ArrayList<>();
        boolean[] checks = new boolean[987 + 1];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());

            games.add(new Game(number, strike, ball));
        }

        for(Game g : games) {

            int number = g.number;
            int strike = g.strike;
            int ball = g.ball;

            int digit1 = number % 100 % 10;
            int digit10 = number % 100 / 10;
            int digit100 = number / 100;

            for (int i = 123; i < checks.length; i++) {
                int tempStrike = 0;
                int tempBall = 0;

                int tempDigit1 = i % 100 % 10;
                int tempDigit10 = i % 100 / 10;
                int tempDigit100 = i / 100;


                // 민혁이의 숫자와 같거나 자릿수에 중복숫자가 있는 경우(e.g 224, 242, 244)
                if(number == i || tempDigit1 == tempDigit10 || tempDigit1 == tempDigit100 || tempDigit10 == tempDigit100 || tempDigit1 == 0 || tempDigit10 == 0) {
                    checks[i] = true;
                    continue;
                }

                // strike
                if(digit1 == tempDigit1) tempStrike++;
                if(digit10 == tempDigit10) tempStrike++;
                if(digit100 == tempDigit100) tempStrike++;

                // ball
                if(digit1 == tempDigit10 || digit1 == tempDigit100) tempBall++;
                if(digit10 == tempDigit1 || digit10 == tempDigit100) tempBall++;
                if(digit100 == tempDigit1 || digit100 == tempDigit10) tempBall++;

                // 추측한 숫자와 strike, ball이 다르면
                if(strike != tempStrike || ball != tempBall) checks[i] = true;
            }
        }

        int cnt = 0;
        for(int i = 123; i < checks.length; i++) {
            if(!checks[i]) {
                cnt++;
                System.out.println(i);
            }
        }

        System.out.print(cnt);
        br.close();

    }

    static class Game {
        private int number;
        private int strike;
        private int ball;

        public Game(int number, int strike, int ball) {
            this.number = number;
            this.strike = strike;
            this.ball = ball;
        }
    }
}
