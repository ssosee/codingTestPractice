package inflearn.array;

/**
 * 봉우리
 */
import java.util.*;
public class test10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int map[][] = new int[size][size];

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        System.out.println(new Solution().getTopCount(map));
    }

    static class Solution {
        public int getTopCount(int[][] map) {
            int[][] newMap = new int[map.length+2][map.length+2];
            int top = 0;
            //초기화
            for(int i = 0; i < map.length+2; i++) {
                for(int j = 0; j < map.length+2; j++) {
                    newMap[i][j] = 0;
                }
            }
            //신맵에 기존 맵 저장
            for(int i = 0; i < map.length; i++) {
                for(int j = 0; j < map.length; j++) {
                    newMap[i+1][j+1] = map[i][j];
                }
            }

            //자신의 숫자가 상, 하, 좌, 우 보다 크면 봉우리
            for(int i = 1; i < newMap.length-1; i++) {
                for(int j = 1; j < newMap.length-1; j++) {
                    //상
                    if(newMap[i][j] > newMap[i-1][j]) {
                        //좌
                        if(newMap[i][j] > newMap[i][j-1]) {
                            //하
                            if(newMap[i][j] > newMap[i+1][j]) {
                                //우
                                if(newMap[i][j] > newMap[i][j+1]) {
                                    top++; //봉우리
                                }
                            }
                        }
                    }
                }
            }

            return top;
        }
    }
}
