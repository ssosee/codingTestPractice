package programmers.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 무인도_여행 {
    static int X = 0;
    static int Y = 0;
    static int day = 0;
    public static void main(String[] args) {
        // 섬에 머물 수 있는 기간 출력
        // DFS 사용!
        String[] maps = new String[]{"X591X","X1X5X","X231X", "1XXX1"};
        //String[] maps = new String[]{"XXX","XXX","XXX"};
        int[] solution = solution(maps);
        for(int i : solution) {
            System.out.println(i);
        }
    }

    static public int[] solution(String[] maps) {
        // 2차원 배열 map 생성
        X = maps.length;
        Y = maps[0].length();

        // maps를 2차원 int 배열로 변경
        int[][] map = new int[X][Y];
        for(int i = 0; i < X; i++) {
            char[] chars = maps[i].toCharArray();
            for(int j = 0; j < chars.length; j++) {
                if(chars[j] == 'X') {
                    map[i][j] = 0;
                } else {
                    map[i][j] = chars[j] - '0';
                }
            }
        }

        List<Integer> days = new ArrayList<>();
        boolean[][] visited = new boolean[X][Y];
        for(int i = 0; i < X; i++) {
            for(int j = 0; j < Y; j++) {
                // 방문 이력이 없고, 섬이면
                if(!visited[i][j] && map[i][j] > 0) {
                    dfs(map, visited, i, j);
                    days.add(day);
                    day = 0; // 머물 수 있는 날짜 초기화
                }
            }
        }

        // 섬이 없는 경우
        if(days.isEmpty()) {
             return new int[]{-1};
        }

        // 오름 차순
        Collections.sort(days);
        int[] answer = new int[days.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = days.get(i);
        }

        return answer;
    }

    private static void dfs(int[][] map, boolean[][] visited, int x, int y) {
        day += map[x][y];
        visited[x][y] = true;
        int[] upAndDown = {1, -1, 0, 0};
        int[] leftAndRight = {0, 0, 1, -1};

        for(int i = 0; i < 4; i++) {
            int newX = x + upAndDown[i];
            int newY = y + leftAndRight[i];

            // 범위 체크
            if(newX < 0 || newY < 0 || newX >= X || newY >= Y) {
                continue;
            }

            // 방문 이력이 없고 섬이면
            if(!visited[newX][newY] && map[newX][newY] > 0) {
                dfs(map, visited, newX, newY);
            }
        }
    }
}
