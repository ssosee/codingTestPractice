package groom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/***
 * 요구사항
 * 1. 가위, 바위, 보 게임을 5명이 참가한다.
 * 2. 승리하는 사람의 숫자를 판단한다.
 *
 * 구현
 * 1. 1, 2, 3중 누가 이겼는지
 * 2. 이긴 사람이 몇명인지
 * 3. 이긴 사람이 없으면 0
 */
public class RockScissorsPaper {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        Game game = new Game(input);
        System.out.println(game.winnerCount());

    }

    static class Game {
        String str;
        public Game(String str) {
            this.str = str;
        }

        public int winnerCount() {

            int scissorsCount = countString('1');
            int rockCount = countString('2');
            int paperCount = countString('3');

            if(scissorsCount > 0 && rockCount > 0 && paperCount == 0) return rockCount;
            else if(scissorsCount > 0 && rockCount == 0 && paperCount > 0) return scissorsCount;
            else if(scissorsCount == 0 && rockCount > 0 && paperCount > 0) return paperCount;

            //그외 무승부
            return 0;
        }
        
        private int countString(char chr) {
            int count = 0;
            
            for(int i = 0; i < str.length(); i++) {
                if(str.charAt(i) == chr) {
                    count++;
                }
            }
            return count;
        }
    }
}

