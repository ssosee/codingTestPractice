package backjoon.search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz14503 {
    static int n;
    static int m;
    static int startX;
    static int startY;
    static int front;
    static int[][] map;
    static int[] ud = {1, -1, 0, 0};
    static int[] lr = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        /**
         * 1. 현재 칸이 청소되지 않은 경우, 현재 칸 청소
         * 2. 근처 4칸이 모두 깨끗한 경우
         *  2.1) 바라보는 방향을 유지하고 한칸 후진
         *  2.2) 후진 못하면 멈춤
         * 3. 4칸 중 더러운 곳이 있으면
         *  3.1) 반시계 방향으로 90도 회전
         *  3.2) 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈칸인 경우 1칸 전진
         *  3.3) 1번 실행
         *
         * 로봇 청소기가 작동을 멈출때 까지 청소하는 칸의 갯수
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());
        // 0: 북, 1: 동, 2: 남, 3: 서
        front = Integer.parseInt(st.nextToken());

        // 지도 저장
        map = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int bfs = bfs();

        System.out.print(bfs);
        br.close();
    }

    private static int bfs() {
        Queue<Robot> queue = new ArrayDeque<>();
        queue.add(new Robot(startX, startY, front));
        int count = 0;

        while(!queue.isEmpty()) {
            Robot poll = queue.poll();
            int x = poll.getX();
            int y = poll.getY();
            int direction = poll.getDirection();

            // 현재 칸이 청소가 안되어 있으면 청소
            if(map[x][y] == 0) {
                map[x][y] = -1; // 청소 완료
                count++;
            }

            // 근처 4칸이 모두 깨끗하면
            if(!rangeCheck(x, y)) {
                // 북을 바라보면 남으로 이동
                if(direction == 0) {
                    // 후진 가능
                    if(map[x+1][y] != 1) {
                        // 바라보는 방향을 유지하고 한칸 후진
                        queue.add(new Robot(x + 1, y, direction));
                    }
                    // 후진 불가
                    else {
                        return count;
                    }
                }
                // 동을 바라보면 서로 이동
                else if(direction == 1) {
                    // 후진 가능
                    if(map[x][y-1] != 1) {
                        // 바라보는 방향을 유지하고 한칸 후진
                        queue.add(new Robot(x, y - 1, direction));
                    }
                    // 후진 불가
                    else {
                        return count;
                    }
                }
                // 남을 바라보면 북으로 이동
                else if(direction == 2) {
                    // 후진 가능
                    if(map[x-1][y] != 1) {
                        // 바라보는 방향을 유지하고 한칸 후진
                        queue.add(new Robot(x - 1, y, direction));
                    }
                    // 후진 불가
                    else {
                        return count;
                    }
                }
                // 서를 바라보면 동으로 이동
                else if(direction == 3) {
                    // 후진 가능
                    if(map[x][y+1] != 1) {
                        // 바라보는 방향을 유지하고 한칸 후진
                        queue.add(new Robot(x, y + 1, direction));
                    }
                    // 후진 불가
                    else {
                        return count;
                    }
                }
            }
            // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
            else {
                while (true) {
                    // 방향 전환
                    switch (direction) {
                        case 0:
                            direction = 3;
                            break;
                        case 1:
                            direction = 0;
                            break;
                        case 2:
                            direction = 1;
                            break;
                        case 3:
                            direction = 2;
                            break;
                    }

                    // 북
                    if (direction == 0) {
                        // 앞쪽 칸이 청소되지 않은 빈칸 인경우 한칸 전진
                        if (x - 1 >= 0 && x - 1 < n) {
                            if (map[x - 1][y] == 0) {
                                queue.add(new Robot(x - 1, y, direction));
                                break;
                            }
                        }
                    }
                    // 동
                    else if (direction == 1) {
                        // 앞쪽 칸이 청소되지 않은 빈칸 인경우 한칸 전진
                        if (y + 1 >= 0 && y + 1 < m) {
                            if (map[x][y + 1] == 0) {
                                queue.add(new Robot(x, y + 1, direction));
                                break;
                            }
                        }
                    }
                    // 서
                    else if (direction == 3) {
                        // 앞쪽 칸이 청소되지 않은 빈칸 인경우 한칸 전진
                        if (y - 1 >= 0 && y - 1 < m) {
                            if (map[x][y - 1] == 0) {
                                queue.add(new Robot(x, y - 1, direction));
                                break;
                            }
                        }
                    }
                    // 남
                    else if (direction == 2) {
                        // 앞쪽 칸이 청소되지 않은 빈칸 인경우 한칸 전진
                        if (x + 1 >= 0 && x + 1 < n) {
                            if (map[x + 1][y] == 0) {
                                queue.add(new Robot(x + 1, y, direction));
                                break;
                            }
                        }
                    }
                }
            }
        }

        return count;
    }

    private static boolean rangeCheck(int x, int y) {
        for(int i = 0; i < 4; i++) {
            int newX = x + ud[i];
            int newY = y + lr[i];

            // map을 벗어나는 경우
            if(newX < 0 || newY < 0 || newX >= n || newY >= m) continue;

            // 1칸이라도 청소할 곳이 있으면
            if(map[newX][newY] == 0) {
                return true;
            }
        }
        // 4칸 모두 깨끗한 경우
        return false;
    }

    static class Robot {
        private int x;
        private int y;
        private int direction;

        public Robot(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getDirection() {
            return direction;
        }
    }
}
