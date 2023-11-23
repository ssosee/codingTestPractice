package toss_assistant_algorithm_study.season5.week1;

import java.util.ArrayList;
import java.util.List;

public class SetMatrixZeroes {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.setZeroes(new int[][]{
                {0,1,2,0},
                {3,4,5,2},
                {1,3,1,5}
        });
    }

    /**
     * 아이디어
     * - 0을 갖고 있는 인덱스를 찾는다.
     * - 인덱스를 기준으로 가로, 세로를 0으로 만든다.
     *
     * 자료구조
     * - 2차원 배열
     *
     * 시간복잡도
     * - O(N^2)
     */
    static class Solution {
        public void setZeroes(int[][] matrix) {
            List<Graph> Graphs = new ArrayList<>();
            int vLength = matrix.length; // 세로
            int hLength = matrix[0].length; // 가로

            // 0을 갖고 있는 index 찾기
            for(int y = 0; y < vLength; y++) {
                for(int x = 0; x < hLength; x++) {
                    int value = matrix[y][x];
                    if(value == 0) {
                        Graphs.add(new Graph(x, y));
                    }
                }
            }

            for (Graph graph : Graphs) {
                int x = graph.x;
                int y = graph.y;

                // 세로 0으로 만들기
                for(int i = 0; i < vLength; i++) {
                    matrix[i][x] = 0;
                }

                // 가로 0으로 만들기
                for(int i = 0; i < hLength; i++) {
                    matrix[y][i] = 0;
                }
            }
        }

        static class Graph {
            private int x;
            private int y;

            public Graph(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }
}
