package toss_assistant_algorithm_study.season5.week3;

import java.util.ArrayList;
import java.util.List;

public class WordSearch {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.exist(new char[][]{
//                {'A','B','C','E'},
//                {'S','F','C','S'},
//                {'A','D','E','E'}
//        }, "ABCCED"));
//        System.out.println(solution.exist(new char[][]{
//                {'a','a'}
//        }, "aaa"));

//        System.out.println(solution.exist(new char[][]{
//                {'a','b'}
//        }, "ba"));

        System.out.println(solution.exist(new char[][]{
                {'b'},{'a'},{'b'},{'b'},{'a'}
        }, "baa"));
    }

    /**
     * 아이디어
     * - 완전탐색
     *
     * 자료구조
     * - 배열, 문자열
     *
     * 시간복잡도
     * - O(n * m * l) ?
     *
     */
    static class Solution {
        static int n;
        static int m;
        static int wordLength;
        static int[] moveX = {1, -1, 0, 0};
        static int[] moveY = {0, 0, 1, -1};
        boolean[][] visited;
        static List<String> words;
        public boolean exist(char[][] board, String word) {
            n = board.length;
            m = board[0].length;
            wordLength = word.length();

            words = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    visited = new boolean[n][m];
                    visited[i][j] = true;

                    // 탐색 시작
                    dfs(i, j, board, 1, String.valueOf(board[i][j]));

                    for (String findWord : words) {
                        if (findWord.equals(word)) {
                            return true;
                        }
                    }
                    // 초기화
                    words = new ArrayList<>();
                }
            }

            return false;
        }

        private void dfs(int x, int y, char[][] board, int depth, String matchWord) {

            if(wordLength == depth) {
                words.add(matchWord);
                return;
            }

            for(int i = 0; i < 4; i++) {
                int newX = moveX[i] + x;
                int newY = moveY[i] + y;

                if(newX < 0 || newY < 0 || newX >= n || newY >= m) continue;

                if(!visited[newX][newY]) {
                    char ch = board[newX][newY];
                    visited[newX][newY] = true;
                    dfs(newX, newY, board, depth + 1, matchWord + ch);
                    visited[newX][newY] = false;
                }
            }
        }
    }
}
