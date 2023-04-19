package backjoon.search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Quiz9205 {
    static int[][] path;
    static boolean[] beer = new boolean[20];
    static String result = "happy";

    static int endX;
    static int endY;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            path = new int[n+1][2];
            for(int i = 0; i < n+1; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                //System.out.println(i+", x="+x+", y="+y);
                path[i][0] = x;
                path[i][1] = y;
            }

            Arrays.sort(path, (o1, o2) -> Integer.compare(o1[0], o2[0]));
//
//            st = new StringTokenizer(br.readLine());
//            path[n][0] = Integer.parseInt(st.nextToken());
//            path[n][1] = Integer.parseInt(st.nextToken());


            // 맥주 채워 넣기
            buyBeer(20);
            dfs(startX, startY, 0, "");

            System.out.println(result);
        }

        br.close();
    }

    private static void dfs(int startX, int startY, int depth, String status) {

        if(depth >= path.length) {
            result = status;
            return;
        }

        int newEndX = path[depth][0];
        int newEndY = path[depth][1];

        // 거리계산(맨해튼 거리)
        int distance = Math.abs(startX - newEndX) + Math.abs(startY - newEndY);

        int count = getDrunkBeerCount(distance);
        if(count > 20) {
            result = "sad";
            return;
        }

        buyBeer(count);
        dfs(newEndX, newEndY, depth + 1, "happy");
    }

    private static int getDrunkBeerCount(int distance) {
        // 필요한 맥주 갯수 계산
        int needCount = distance / 50;

        // 필요한 맥주가 20개 보다 많으면
        if(needCount > beer.length) return needCount;

        for(int i = 0; i < needCount; i++) {
            beer[i] = false;
        }

        return needCount;
    }

    private static void buyBeer(int count) {
        for(int i = 0; i < count; i++) {
            if(!beer[i]) {
                beer[i] = true;
            }
        }
    }
}
