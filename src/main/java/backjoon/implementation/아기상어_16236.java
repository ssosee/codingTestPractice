package backjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아기상어_16236 {
    static int n;
    static int[] moveX = {1, -1, 0, 0};
    static int[] moveY = {0, 0, 1, -1};
    static int[][] map;
    static int startX;
    static int startY;
    static int totalDistance = 0;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    startX = i;
                    startY = j;
                    map[i][j] = 0;
                }
            }
        }

        bfs();
        System.out.print(totalDistance);
        br.close();
    }

    private static void bfs() {

        int size = 2;
        int eatCount = 0;

        while (true) {

            /**
             * 1. 거리가 가까운 물고기부터 먹는다.
             * 2. 거리가 동일하면 가장 위쪽의 물고기를 먹는다.
             * 3. 가장 위쪽의 물고기가 많으면 가장 왼쪽의 물고기를 먹는다.
             */
            Queue<Shark> queue = new PriorityQueue<>(new Comparator<Shark>() {
                @Override
                public int compare(Shark o1, Shark o2) {
                    // 거리가 동일하고 가장 위쪽의 물고기가 많으면
                    if (o1.distance == o2.distance && o1.x == o2.x) {
                        // 가장 왼쪽의 물고기 부터
                        return Integer.compare(o1.y, o2.y);
                    }
                    // 거리가 동일하면
                    else if (o1.distance == o2.distance) {
                        return Integer.compare(o1.x, o2.x);
                    }
                    // 가장 가까운 물고기부터 먹는다.
                    return Integer.compare(o1.distance, o2.distance);
                }
            });


            queue.offer(new Shark(startX, startY, 0));
            visited = new boolean[n][n];
            visited[startX][startY] = true;
            boolean isEat = false;

            while (!queue.isEmpty()) {
                Shark poll = queue.poll();
                int x = poll.x;
                int y = poll.y;
                int distance = poll.distance;

                // 물고기가 상어보다 작고, 벽이 아니면
                if (map[x][y] < size && map[x][y] != 0) {
                    // 물고기를 잡아 먹는다.
                    map[x][y] = 0;
                    eatCount++;
                    isEat = true;

                    // 상어의 위치를 물고기 잡아 먹은 위치로 갱신
                    startX = x;
                    startY = y;
                    totalDistance += distance;

                    // 상어의 크기와 물고기 잡아먹은 횟수가 같으면
                    if(size == eatCount) {
                        eatCount = 0;
                        size++; // 상어의 크기 증가
                    }

                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int newX = x + moveX[i];
                    int newY = y + moveY[i];

                    if (newX < 0 || newY < 0 || newX >= n || newY >= n) continue;

                    // 방문 이력이 없고, 상어보다 작거나 같은 물고기는 지나갈 수 있음
                    if (!visited[newX][newY] && map[newX][newY] <= size) {
                        visited[newX][newY] = true;
                        queue.offer(new Shark(newX, newY, distance + 1));
                    }
                }
            }

            // while문 동안 물고기를 잡아 먹은 적이 없다면
            if(!isEat) break; // 엄마를 불러야함
        }
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
