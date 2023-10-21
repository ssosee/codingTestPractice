package toss_assistant_algorithm_study.seaaon4.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 루돌프의_반란 {

    // 상,하,좌,우 순으로
    static int[] santaMoveX = {0, 0, -1, 1};
    static int[] santaMoveY = {-1, 1, 0, 0};

    // 상,하,좌,우,대각
    static int[] rudolphMoveX = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static int[] rudolphMoveY = {-1, -1, -1, 0, 0, 0, 1, 1, 1};

    static int currentTurnCount = 1;

    static int[][] board;
    public static void main(String[] args) throws IOException {
        /**
         * 아이디어
         * - (r,c)에서 아래로 가면 r 증가, 오른쪽 가면 c 증가, 좌상단은 (1,1)
         * - 매턴 마다 루돌프와 산타들이 1회씩 이동
         * - 기절 or 격자 밖 이면 이동 불가
         * - 루돌프가 한 번 이동한 뒤, 1번 ~ P번 산타까지 순서대로 이동
         * - (r1,c1) ~ (r2,c2) 의 거리는 (r1 - r2)^2 + (c1 - c2)^2
         *
         * - 루돌프는 탈락하지 않은 산타 중 가장 가까운 산타를 향해 1칸 이동
         * - 가장 가까운 산타가 2명 이상이면, r 좌표가 큰 산타쪽으로 이동, r도 같다면 c좌표가 큰 산타쪽으로 이동
         * - 루돌프는 상,하,좌,우,대각선을 포함하여 이동 가능(대각선도 1칸 전진으로 취급)
         *
         * - 산타는 루돌프에게 거리가 가장 가까워지는 방향으로 1칸 이동
         * - 이동이 불가하면 이동하지 않음
         * - 산타는 상,하,좌,우 이동 가능(가장 가까워지는 방향이 여러개이면 상,하,좌,우 순으로 이동)
         *
         * - 산타와 루돌프가 같은 칸에 있으면 충돌
         * - 루돌프가 움직여서 충돌이 일어나면 산타는 C만큼 점수 획득
         *  - 이때, 산타는 루돌프가 이동해온 방향으로 C칸 만큼 밀려남(*대각이면 대각으로 밀려남)
         * - 산타가 움직여서 충돌이 일어나면 산타는 D만큼 점수 획득
         *  - 이때, 산타는 자신이 이동한 방향 반대로 D칸 만큼 밀려남
         * - 밀려날 때 중간 과정에서 충돌은 없다.
         * - 밀려난 위치가 게임판 밖이면 탈락
         *
         * - 완전히 밀려난 후, 해당 칸에 다른 산타가 있으면 다른 산타는 1칸 해당 방향으로 밀려남
         *  - 연쇄적으로 반응이 일어난다.
         *  - 이때, 산타가 밖으로 밀려나면 탈락
         *
         * - 루돌프와 충돌한 산타는 기절
         * - k번째 턴이었다면, k+1번째 턴까지 기절
         *
         * - 매 턴 이후 탈락하지 않은 산타들에게 1점씩 점수 부여
         *
         * 시간복잡도
         *
         * 자료구조
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int rudolphStartX = Integer.parseInt(st.nextToken());
        int rudolphStartY = Integer.parseInt(st.nextToken());

        Queue<Santa> santas = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.id, o2.id));
        for(int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int santaStartX = Integer.parseInt(st.nextToken());
            int santaStartY = Integer.parseInt(st.nextToken());
            santas.offer(new Santa(id, santaStartX, santaStartY, 0, false, 0, false));
        }

        board = new int[n+1][n+1];

        Queue<Rudolph> rudolphs = new ArrayDeque<>();
        rudolphs.offer(new Rudolph(rudolphStartX, rudolphStartY, false));

        Rudolph rudolph = rudolphs.poll();

        // 루돌프의 이동
        while (!rudolphs.isEmpty()) {
            // 산타와 제일 가까운 곳으로 이동
        }
        // 산타의 이동
    }

    public void dijkstra() {

    }

    public static Santa minDistanceFormRudolphToSanta(Rudolph rudolph, Queue<Santa> santa) {
        int min = Integer.MAX_VALUE;
        int minSantaX = 0;
        int minSantaY = 0;

        while (!santa.isEmpty()) {
            Santa poll = santa.poll();
            int santaX = poll.x;
            int santaY = poll.y;

            // 거리 계산
            int distance = (int) (Math.pow(Math.abs(rudolph.x - santaX), 2) + Math.pow(Math.abs(rudolph.y - santaY), 2));
            // 이전 산타의 거리보다 현재 거리보다 작으면?
            if(distance < min) {
                min = distance;
                minSantaX = santaX;
                minSantaY = santaY;
            }
            // 거리가 같으면
            else if(distance == min) {
                // x가 큰 산타의 거리
                if(santaX > minSantaX) {
                    minSantaX = santaX;
                    minSantaY = santaY;
                } else if(santaX == minSantaX) {
                    if(santaY > minSantaY) {
                        minSantaX = santaX;
                        minSantaY = santaY;
                    }
                }
            }
        }

        return new Santa(minSantaX, minSantaY);
    }

    static class Santa {
        private int id;
        private int x;
        private int y;
        private int score;
        private boolean isComa;
        private int blockTurnCount;
        private boolean isOut;

        public Santa(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Santa(int id, int x, int y, int score, boolean isComa, int blockTurnCount, boolean isOut) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.score = score;
            this.isComa = isComa;
            this.blockTurnCount = blockTurnCount;
            this.isOut = isOut;
        }
    }

    static class Rudolph {
        private int x;
        private int y;
        private boolean isCross;
        public Rudolph(int x, int y, boolean isCross) {
            this.x = x;
            this.y = y;
            this.isCross = isCross;
        }
    }
}
