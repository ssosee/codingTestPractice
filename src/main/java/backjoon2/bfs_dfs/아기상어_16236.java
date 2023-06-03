package backjoon2.bfs_dfs;

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
    static int time = 0;
    // 상어의 크기
    static int size = 2;
    static int eatCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 상어의 시작 위치
                if(map[i][j] == 9) {
                    startX = i;
                    startY = j;
                    map[i][j] = 0;
                }
            }
        }

        /**
         * 자신 보다 큰 물고기는 먹을 수 없고, 지나갈 수 없다.
         * 가까운 거리에 있는 물고기를 먹는다.
         * 동일한 거리에 물고기가 있을 경우 우선적으로 위쪽에 있는 물고기를 먹는다.
         * 가장 위쪽에 있는 물고기가 여러 마리 이면 가장 왼쪽의 물고기를 먹는다.
         * 자신의 크기와 동일한 물고기의 숫자를 먹으면 자신의 크기가 1 증가한다.
         * 더 이상 물고기를 먹을 수 없으면 종료
         * 이때 시간을 출력한다.
         *
         * 1. 자신보다 작은 물고기를 찾는다.(가능한 경로는 0이거나 자신보다 작은 곳으로 탐색 가능)
         * 2. 물고기를 만나면 먹는다.
         * 3. 상어의 탐색 시작 위치를 2에서 먹은 물고기로 바꾼다.
         * 4. 1-3을 반복하고 더 이상 물고기를 먹지 못하면 반복문을 탈출
         */

        bfs();
        System.out.print(time);
        br.close();

    }

    private static void bfs() {
        while (true) {
            Queue<Shark> queue = new PriorityQueue<>(new Comparator<Shark>() {
                @Override
                public int compare(Shark o1, Shark o2) {
                    // 동일한 거리가 아닐 경우
                    if (o1.distance != o2.distance) {
                        // 가장 가까운 물고기를 꺼낸다.
                        return Integer.compare(o1.distance, o2.distance);
                    }
                    // 동일한 높이가 아닌 경우
                    else if (o1.x != o2.x) {
                        // 가장 위에 있는 물고기를 꺼낸다.
                        return Integer.compare(o1.x, o2.x);
                    }
                    // 그외는 가장 왼쪽의 물고기를 꺼낸다.
                    return Integer.compare(o1.y, o2.y);
                }
            });

            queue.offer(new Shark(startX, startY, 0));
            boolean[][] visited = new boolean[n][n];
            visited[startX][startY] = true;

            boolean isEat = false;

            // 물고기 찾기
            while (!queue.isEmpty()) {
                Shark poll = queue.poll();
                int x = poll.x;
                int y = poll.y;
                int distance = poll.distance;

                // 상어보다 물고기가 작으면
                if (map[x][y] < size && map[x][y] != 0) {
                    // 상어의 탐색시작 위치를 갱신
                    startX = x;
                    startY = y;

                    map[x][y] = 0; // 물고기를 먹어버린다!
                    eatCount++; // 먹은 횟수
                    time += distance; // 시간 기록
                    isEat = true; // 물고기를 먹었는지 확인
                    System.out.println(x+", "+y+", "+time);
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int newX = x + moveX[i];
                    int newY = y + moveY[i];

                    // 수족관 범위 체크
                    if (newX < 0 || newY < 0 || newX >= n || newY >= n) continue;

                    // 방문이력이 없고, 상어보다 물고기가 작거나 같으면
                    if (!visited[newX][newY] && map[newX][newY] <= size) {
                        visited[newX][newY] = true;
                        queue.offer(new Shark(newX, newY, distance + 1));
                    }
                }
            }

            if (!isEat) {
                break;
            }

            // 자신의 크기만큼 물고기를 먹으면
            if(eatCount == size) {
                size++; // 상어의 크기를 증가
                eatCount = 0; // 먹은 횟수 초기화
            }
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
