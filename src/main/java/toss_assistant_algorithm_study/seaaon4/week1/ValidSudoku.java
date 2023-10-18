package toss_assistant_algorithm_study.seaaon4.week1;

public class ValidSudoku {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.isValidSudoku(new char[][]{
//                {'8','3','.','.','7','.','.','.','.'},
//                {'6','.','.','1','9','5','.','.','.'},
//                {'.','9','8','.','.','.','.','6','.'},
//                {'8','.','.','.','6','.','.','.','3'},
//                {'4','.','.','8','.','3','.','.','1'},
//                {'7','.','.','.','2','.','.','.','6'},
//                {'.','6','.','.','.','.','2','8','.'},
//                {'.','.','.','4','1','9','.','.','5'},
//                {'.','.','.','.','8','.','.','7','9'}
//        }));

        System.out.println(solution.isValidSudoku(new char[][]{
                {'.','.','.','.','5','.','.','1','.'},
                {'.','4','.','3','.','.','.','.','.'},
                {'.','.','.','.','.','3','.','.','1'},
                {'8','.','.','.','.','.','.','2','.'},
                {'.','.','2','.','7','.','.','.','.'},
                {'.','1','5','.','.','.','.','.','.'},
                {'.','.','.','.','.','2','.','.','.'},
                {'.','2','.','9','.','.','.','.','.'},
                {'.','.','4','.','.','.','.','.','.'}
        }));
    }

    static class Solution {
        /**
         * 아이디어
         * - 구현
         * - 가로, 세로, 3*3 에 각각 1~9의 숫자 중 중복해서 들어가면 안된다.
         * - boolean[스도쿠 숫자] = true ? false 로 판단
         *
         * 자료구조
         * - 배열
         *
         * 시간복잡도
         * - O(N^2)
         */
        public boolean isValidSudoku(char[][] board) {
            for(int i = 0; i < board.length; i ++) {
                boolean[] horizon = new boolean[10];
                boolean[] vertical = new boolean[10];

                for(int j = 0; j < board[0].length; j++) {
                    if(board[i][j] != '.') {
                        if (horizon[board[i][j] - '0']) return false;
                        else horizon[board[i][j] - '0'] = true;
                    }
                    if(board[j][i] != '.') {
                        if (vertical[board[j][i] - '0']) return false;
                        else vertical[board[j][i] - '0'] = true;
                    }
                }
            }

            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if (!validation3By3(board, i * 3, i * 3 + 3, j * 3, j * 3 + 3)) return false;
                }
            }

            return true;
        }

        public boolean validation3By3(char[][] board, int startX, int endX, int startY, int endY) {
            boolean[] horizon = new boolean[10];
            boolean[] vertical = new boolean[10];

            System.out.println("startX="+startX+", endX="+endX);
            System.out.println("startY="+startY+", endY="+endY);

            for(int i = startX; i < endX; i++) {
                for(int j = startY; j < endY; j++) {
                    if(board[i][j] != '.') {
                        if (horizon[board[i][j] - '0']) return false;
                        else horizon[board[i][j] - '0'] = true;
                    }
                    if(board[j][i] != '.') {
                        if (vertical[board[j][i] - '0']) return false;
                        else vertical[board[j][i] - '0'] = true;
                    }
                }
            }

            return true;
        }
    }
}
