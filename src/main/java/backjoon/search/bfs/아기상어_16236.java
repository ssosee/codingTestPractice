package backjoon.search.bfs;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 아기상어_16236 {

    static int n;
    static int[][] map;
    static int INF = 987654321;
    static boolean[][] visited;
    static int startX;
    static int startY;
    static int[] moveX = {1, -1, 0, 0};
    static int[] moveY = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        int[] cur = null;
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 아기상어 위치 저장
                if(map[i][j] == 9) {
                    startX = i;
                    startY = j;
                    map[i][j] = 0;
                }
            }
        }

        /**
         * 1. 현재 아기 상어가 먹을 수 있는 물고기의 위치를 찾는다.
         * 2. 물고기가 1마리 이상이면 거리가 가까운 물고기의 위치로 간다.
         * 3. 거리가 동일한 물고기가 있으면 가장 위에 있는 물고기 위치로 간다.
         * 4. 가장 위에있는 물고기가 1마리 이상이면 가장 왼쪽에 있는 물고기의 위치로 간다.
         * 5. 1 - 4를 반복한다.
         *
         * 아기 상어의 크기는 2이고 1초에 1칸씩 움직일수 있다.
         * 아기 상어는 자신보다 큰 물고기가 있는 곳으로는 갈 수 없다.
         * 자신과 동일한 크기의 물고기의 칸으로는 이동할 수 있다.
         * 아기 상어는 자신보다 작은 물고기만 먹을 수 있다.
         * 아기 상어는 자신의 크기와 동일한 숫자의 물고기를 먹으면 크기가 1 증가 한다.
         *
         */
        bfs();
        br.close();
    }

    private static void bfs() {

        int size = 2;
        int eat = 0;
        int move = 0;

        while (true) {
            Queue<Shark> queue = new PriorityQueue<>(new Comparator<Shark>() {
                @Override
                public int compare(Shark o1, Shark o2) {
                    // 거리가 다르다면
                    if(o1.distance != o2.distance) {
                        // 거리순으로 오름차순
                        return Integer.compare(o1.distance, o2.distance);
                    }
                    // 높이가 다르면
                    else if(o1.x != o2.x) {
                        // x를 기준으로 오름차순(가장 위쪽부터)
                        return Integer.compare(o1.x, o2.x);
                    }
                    // y를 기준으로 오름차순(가장 왼쪽부터)
                    return Integer.compare(o1.y, o2.y);
                }
            });

            visited = new boolean[n][n];
            queue.offer(new Shark(startX, startY, 0));
            visited[startX][startY] = true;
            boolean isEat = false;

            while (!queue.isEmpty()) {
                Shark poll = queue.poll();
                int x = poll.x;
                int y = poll.y;
                int distance = poll.distance;

                // 먹을 수 있는 물고기를 만나면
                // 물고기를 먹으면 상어의 위치를 갱신한다.
                if(map[x][y] != 0 && map[x][y] < size) {
                    map[x][y] = 0;
                    eat++;
                    move += distance;
                    isEat = true;
                    startX = x;
                    startY = y;
                    break;
                }

                for(int i = 0; i < 4; i++) {
                    int newX = x + moveX[i];
                    int newY = y + moveY[i];

                    // 범위체크
                    if(newX < 0 || newY < 0 || newX >= n || newY >= n) continue;

                    // 방문이력이 없고, 상어보다 물고기가 작거나 같으면 이동 가능
                    if(!visited[newX][newY] && map[newX][newY] <= size) {
                        queue.offer(new Shark(newX, newY, distance + 1));
                        visited[newX][newY] = true;
                    }
                }
            }

            if(!isEat) break;

            if(eat == size) {
                size++;
                eat = 0;
            }
        }
        System.out.print(move);
    }

    static class Shark {
        private int x;
        private int y;
        private int distance;

        public Shark(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

}