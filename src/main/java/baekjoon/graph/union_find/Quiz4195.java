package baekjoon.graph.union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz4195 {
    public static void main(String[] args) throws IOException {
        /**
         * 친구 관계가 생긴 순서대로 입력값이 주어진다.
         * 두 사람의 친구 네트워크에 몇 명이 있는지 출력
         *
         * *친구 네트워크란 친구 관계만으로 이동할 수 있음
         * 즉, 다른 사람이 동일한 친구를 관계가 있다면 친구 네트워크
         * 서로 다른 사람(노드)이 친구(대표노드)가 같으면 친구네트워크!
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 테스트 케이스
        int testCase = Integer.parseInt(br.readLine());
        for(int t = 0; t < testCase; t++) {
            // 친구 관계 수
            int frc = Integer.parseInt(br.readLine());

            // 친구 배열<친구 아이디, 친구 고유 번호>
            // 친구를 고유 번호로 표현하기 위함.
            Map<String, Integer> friends = new HashMap<>();
            // 친구 고유 번호
            int pk = 0;

            // 대표친구 고유 번호 배열 초기화
            int[] parent = new int[frc*2];
            // 네트워크에 있는 친구 수
            int[] count = new int[frc*2];
            for(int i = 0; i < parent.length; i++) {
                parent[i] = i;
                count[i] = 1; // 친구 네트워크에는 처음에 혼자이기 때문에 1로 초기화
            }

            // 친구 아이디
            String[] ids = new String[frc];
            for(int f = 0; f < frc; f++) {
                ids[f] = br.readLine();
                String[] id = ids[f].split(" ");

                // key, value 형태로 친구id, pk
                // 친구 id가 없다면
                if(!friends.containsKey(id[0])) {
                    friends.put(id[0], pk++);
                }
                if (!friends.containsKey(id[1])) {
                    friends.put(id[1], pk++);
                }

                // 친구 네트워크 형성
                int result = union(parent, count, friends.get(id[0]), friends.get(id[1]));

                // 친구 네트워크에 있는 친구 수 세기
                sb.append(result).append("\n");
            }
        }
        System.out.print(sb);
        br.close();
    }

    // 친구 네트워크 형성
    private static int union(int[] parent, int[] count, int pk1, int pk2) {
        // 대표 친구 번호
        int friend1 = find(parent, pk1);
        int friend2 = find(parent, pk2);

        // 대표 친구가 다르면 대표 친구 번호가 작은순으로 설정(항상 friend1 < friend2)
        if(friend1 != friend2) {
            parent[friend2] = friend1;
            // friend2에 있는 친구의 숫자를 더해줌
            count[friend1] += count[friend2];
            // friend2에 있는 친구 숫자를 1로 초기화
            count[friend2] = 1;
        }
        // 대표노드의 친구 수 반환
        return count[friend1];
    }

    // 대표 친구 고유 번호 찾기
    private static int find(int[] parent, int pk) {
        if(parent[pk] == pk) {
            return pk;
        } else {
            return parent[pk] = find(parent, parent[pk]);
        }
    }
}
