package baekjoon.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 덩치_7568 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Member> members = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            members.add(new Member(m, h));
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            int rank = 1;
            Member member1 = members.get(i);
            int m1 = member1.m;
            int h1 = member1.h;
            for(int j = 0; j < n; j++) {

                if(i == j) continue;

                Member member2 = members.get(j);

                int m2 = member2.m;
                int h2 = member2.h;

                // 덩치가 작으면 순위를 올린다.
                if(m1 < m2 && h1 < h2) rank++;
            }
            sb.append(rank).append(" ");
        }

        System.out.print(sb);
        br.close();
    }

    static class Member {
        private int m;
        private int h;

        public Member(int m, int h) {
            this.m = m;
            this.h = h;
        }
    }
}
