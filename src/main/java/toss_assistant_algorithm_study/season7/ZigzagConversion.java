package toss_assistant_algorithm_study.season7;

import java.util.ArrayList;
import java.util.List;

public class ZigzagConversion {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.convert("PAYPALISHIRING", 3));
        //System.out.println(solution.convert("AB", 1));
    }

    /**
     * 아이디어
     * - 문자열이 지그재그로 배치된다.
     * e.g) PAYPALISHIRING 이고, numRows가 3이면
     *      ⬇️ ↗️ ⬇️ 이런식으로 지그재그로 문자열이 표현된다.
     *      ⬇️P     ⬇️A     ⬇️H
     *      ⬇️A ↗️P ⬇️L ↗️S
     *      ↗️Y     ↗️I
     *
     * - 인접리스트를 이용한다.
     */
    static class Solution {
        public String convert(String s, int numRows) {
            if(numRows == 1) {
                return s;
            }
            StringBuilder sb = new StringBuilder();
            List<Character>[] rows = new List[numRows];
            int index = 0;
            // 음수이면 아래로, 양수이면 대각선
            int direction = -1;

            // 인접리스트 초기화
            for (int i = 0; i < numRows; ++i) {
                rows[i] = new ArrayList<>();
            }

            for (final char c : s.toCharArray()) {
                rows[index].add(c);
                // 배열의 최상단 또는 최하단이면
                if (index == 0 || index == numRows - 1) {
                    // 아래로 갈지 대각선으로 갈지 변경
                    direction *= -1;
                }
                index += direction;
            }

            for (List<Character> row : rows) {
                for (final char c : row) {
                    sb.append(c);
                }
            }

            return sb.toString();
        }

        public String failConvert(String s, int numRows) {

            if (numRows == 1 || s.length() <= numRows) {
                return s;
            }

            char[][] zigzags = new char[numRows][s.length()];
            int row = 0;
            int col = 0;
            for(int i = 0; i < s.length(); i++) {
                // 내려가기
                if(row <= numRows - 1) {
                    zigzags[row++][col] = s.charAt(i);
                }
                // 대각선 올라가기
                else {
                    if(row == numRows) {
                        row--;
                    }
                    while (row > 0) {
                        row--;
                        col++;
                        zigzags[row][col] = s.charAt(i++);
                    }
                    row++;
                    i--;
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < zigzags.length; i++) {
                for(int j = 0; j < zigzags[0].length; j++) {
                    if(Character.isAlphabetic(zigzags[i][j])) {
                        sb.append(zigzags[i][j]);
                    }
                }
            }

            return sb.toString();
        }
    }
}
