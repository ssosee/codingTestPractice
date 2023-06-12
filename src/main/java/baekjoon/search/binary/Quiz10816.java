package baekjoon.search.binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz10816 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // <카드 숫자, 카드 갯수>
        Map<Integer, Integer> card = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int key = Integer.parseInt(st.nextToken());
            card.merge(key, 1, Integer::sum);
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            sb.append(card.getOrDefault(Integer.parseInt(st.nextToken()), 0)).append(" ");
        }
        System.out.print(sb);
        br.close();
    }
}
