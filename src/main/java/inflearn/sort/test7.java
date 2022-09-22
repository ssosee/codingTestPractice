package inflearn.sort;

import java.util.*;

/**
 * <a href="https://wjheo.tistory.com/entry/Java-%EC%A0%95%EB%A0%AC%EB%B0%A9%EB%B2%95-Collectionssort">
 *     좌표 정렬(참고)
 *     </a>
 */
public class test7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
//        int[][] arr = new int[n][2];
//        for(int i = 0; i < n; i++) {
//            for(int j = 0; j < 2; j++) {
//                arr[i][j] = sc.nextInt();
//            }
//        }
//
//        int[][] answer = new Solution().getAnswer(n, arr);
//        for(int i = 0; i < n; i++) {
//            System.out.println(answer[i][0]+" "+answer[i][1]);
//        }

        List<Point> arr = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            arr.add(new Point(sc.nextInt(), sc.nextInt()));
        }
        Collections.sort(arr);
        for(Point p : arr) {
            System.out.println(p.x+" "+p.y);
        }
    }
    static class Solution {

        public int[][] getAnswer(int n, int[][] arr) {
            for(int i = 0; i < n; i++) {
                for(int k = 0; k < n; k++) {
                    if (arr[i][0] < arr[k][0]) {
                        for (int j = 0; j < 2; j++) {
                            int tmp = arr[i][j];
                            arr[i][j] = arr[k][j];
                            arr[k][j] = tmp;
                        }
                    } else if(arr[i][0] == arr[k][0]) {
                        if (arr[i][1] < arr[k][1]) {
                            for (int j = 0; j < 2; j++) {
                                int tmp = arr[i][j];
                                arr[i][j] = arr[k][j];
                                arr[k][j] = tmp;
                            }
                        }
                    }
                }
            }
            return arr;
        }
    }

    static class Point implements Comparable<Point> {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * 암기..
         */
        @Override
        public int compareTo(Point p) {
            /**
             * 음수 리턴
             * 1. 오름차순 this - p
             * 2. 내림차순 p - this
             */
            if(this.x == p.x) return this.y - p.y;
            else return this.x - p.x;
        }
    }
}
