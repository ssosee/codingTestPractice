package toss_assistant_algorithm_study.season7.week1;

public class RotateImage {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.rotate(new int[][]{
                {5,1,9,11},
                {2,4,8,10},
                {13,3,6,7},
                {15,14,12,16}
        });
    }

    /**
     * 아이디어
     * - 2차원 배열을 다른 배열의 자료구조에 할당하지 않고 90도 시계방향으로 돌리기
     *
     * 먼저 배열의 가운데를 기준[4,5,6]으로 가로로 선을 그어서 뒤집고,
     * 대각선을 기준[1,5,9]으로 배열을 뒤집으면 90도 회전하게 된다.
     * [1, 2, 3]        [7, 8, 9]      [7, 4, 1]
     * [4, 5, 6]    ->  [4, 5, 6]  ->  [8, 5, 2]
     * [7, 8, 9]        [1, 2, 3]      [9, 6, 3]
     *
     * 자료구조
     * - 2차원 배열
     *
     * 시간복잡도
     * - O(N)
     */
    static class Solution {
        public void rotate(int[][] matrix) {
            int n = matrix.length;

            // 가운데선을 기준으로 위아래 반전
            // n/2 가운데를 기준으로 위아래 반전하기 때문에
            for(int i = 0; i < n / 2; i++) {
                for(int j = 0; j < n; j++) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[n - i - 1][j];
                    matrix[n - i - 1][j] = temp;
                }
            }

            // 대각선을 기준으로 반전
            for(int i = 0; i < n; i++) {
                // j = i 이면 대각선을 기준으로 반전할 수 있다.
                for(int j = i; j < n; j++) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }
    }
}
