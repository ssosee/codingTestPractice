package toss_assistant_algorithm_study.season7.week2;

public class GameOfLife {
    public static void main(String[] args) {

    }

    /**
     * 아이디어
     * - m x n 행렬이 주어진다.
     * - 1은 live, 0은 dead
     * - 상,하,좌,우,대각선 8방으로 영향력 행사
     * - 이웃에 의해서 살거나 죽거나
     *
     * 살아있는 세포
     *  - 1인 이웃이 2개 미만이면 사망
     *  - 1인 이웃이 2개이상 3개이하이면 그대로
     *  - 1인 이웃이 3개 초과이면 사망
     * 죽은 세포
     *  - 1인 이웃이 3개이면 부활
     *
     * 모든 과정을 탐색한 뒤 한번에 갱신해야한다...!
     */
    static class Solution {
        public static final int ALIVE = 1;
        public static final int DEATH = 0;

        // 상,하,좌,우,대각선 8방
        static int[][] xy = {
                {-1, -1},
                {-1, 0},
                {-1, 1},
                {0, -1},
                {0, 1},
                {1, -1},
                {1, 0},
                {1, 1}
        };
        public void gameOfLife(int[][] board) {
            // 실시간으로 변경되는 보드
            int[][] copyBoard = new int[board.length][board[0].length];
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[0].length; j++) {
                    copyBoard[i][j] = board[i][j];
                }
            }

            for(int i = 0; i < copyBoard.length; i++) {
                for(int j = 0; j < copyBoard[0].length; j++) {
                    int liveCellCount = 0;
                    for(int k = 0; k < xy.length; k++) {
                        int x = i + xy[k][0];
                        int y = j + xy[k][1];

                        // 배열 범위 체크
                        if(x < 0 || x >= copyBoard.length || y < 0 || y >= copyBoard[0].length) {
                            continue;
                        }

                        // 이웃이 살아있는 세포이면
                        if(copyBoard[x][y] == ALIVE) {
                            liveCellCount++;
                        }
                    }

                    // 살아있는 세포
                    if(copyBoard[i][j] == ALIVE) {
                        // 이웃에 살아있는 세포가 2개 미만
                        if(liveCellCount < 2) {
                            board[i][j] = DEATH; // 사망
                        }
                        // 이웃에 살아있는 세포가 3개 초과
                        else if(liveCellCount > 3) {
                            board[i][j] = DEATH; // 사망
                        }
                        // 그대로
                    }
                    // 죽은 세포이고 이웃에 살아있는 세포가 3개이면
                    else if (liveCellCount == 3) {
                        board[i][j] = ALIVE; // 부활
                    }
                }
            }
        }
    }
}
