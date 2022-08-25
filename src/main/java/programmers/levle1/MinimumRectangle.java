package programmers.levle1;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/86491">
 *     최소 직사각형
 * </a>
 */
public class MinimumRectangle {
    public static void main(String[] args) {

    }
    static class Solution {
        public int solution(int[][] sizes) {
            int result = 0;
            int w = 0;
            int h = 0;

            for(int i = 0; i < sizes.length; i++) {
                int tempW = Math.max(sizes[i][0], sizes[i][1]);
                int tempH = Math.min(sizes[i][0], sizes[i][1]);
                w = Math.max(tempW, w);
                h = Math.max(tempH, h);
            }
            result = w*h;
            return result;
        }
    }
}
