package baekjoon.graph.union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz1043 {
    /**
     * 지민이는 파티에 참가
     * 파티에는 지민이가 거짓말인지 아닌지 아는 사람이 있을 수도 없을 수도 있음
     * 거짓말을 아는 사람이 1명이라도 있을 경우 지민이는 해당 파티에서 진실만을 이야기 해야 한다.
     * 어떤 사람이 어떤 파티에서는 진실을 듣고, 또다른 파티에서는 과장된 이야기를 들었을 때도 지민이는 거짓말쟁이로 알려지게 된다
     *
     * 지민이가 진실만 이야기하는 파티의 최대 갯수
     */
    public static void main(String[] args) throws IOException {
        /**
         * 4 2          // 사람 수(4), 파티 수(2)
         * 1 1          // 거짓말인지 아는 사람의 수(1), 거짓말인지 아는 사람 번호(1)
         * 4 1 2 3 4    // 파티에 오는 사람 수(4), 파티에 오는 사람 번호(1, 2, 3, 4)
         * 3 1 2 3      // 파티에 오는 사람 수(3), 파티에 오는 사람 번호(1, 2, 3)
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 사람 수
        int n = Integer.parseInt(st.nextToken());
        // 파티 수
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        // 거짓말인지 아는 사람의 수
        int count = Integer.parseInt(st.nextToken());

        // 거짓말인지 아는 사람 번호
        boolean[] knowMan = new boolean[n+1];
        for(int i = 0; i < count; i++) {
            int num = Integer.parseInt(st.nextToken());
            // index가 사람 번호
            knowMan[num] = true;
        }

        // 대표 노드 배열 생성 및 초기화
        int[] arr = new int[n+1];
        for(int i = 1; i < arr.length; i++) {
            arr[i] = i;
        }

        // 파티 생성
        List<Integer>[] party = new ArrayList[m];
        for(int i = 0; i < m; i++) {
            party[i] = new ArrayList<>();
        }

        // 이전 입력 값
        // e.g) 2 1 2 가 입력이면
        int prev = 0;
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            // 파티에 오는 사람 수
            // 2
            int num = Integer.parseInt(st.nextToken());
            if(num > 0) {
                // 1
                prev = Integer.parseInt(st.nextToken());
                party[i].add(prev);
            }
            // num이 2 이기때문에 for문으로 들어간다
            for(int j = 1; j < num; j++) {
                // 2
                int current = Integer.parseInt(st.nextToken());
                party[i].add(current);
                // 같이 파티에 온 사람을 동일한 대표노드로 묶어 버린다.
                union(arr, prev, current);
                // 이전 입력값에 현재 입력값을 넣어줘서 이전 입력값으로 만든다.
                prev = current; // 2
            }
        }

        // 거짓말인지 아는 사람의 수가 0이면
        if(count == 0) {
            // 모든 파티에서 거짓말 가능
            System.out.print(m);
            br.close();
            return;
        }

        // 진실을 아는 사람들과 같이 파티를 참여했다면 그 사람도 진실을 알게됨
        for(int i = 1; i < knowMan.length; i++) {
            if(knowMan[i]) {
                // i가 진실을 아는 사람의 번호
                // i의 대표노드에 있는 사람도 진실을 알게 된다.
                knowMan[find(arr, i)] = true;
            }
        }

        // 진실을 아는 사람들과 파티를 같이 하지 않은 경우 구하기
        int result = 0;
        // 파티 탐색
        for(int i = 0; i < m; i++) {
            // i 번째 파티에 있는 인원 탐색
            for(int j = 0; j < party[i].size(); j++) {
                // 해당 파티의 사람의 대표노드를 꺼낸다.
                int ceoNode = find(arr, party[i].get(j));
                // 진실을 아는 사람이면
                if(!knowMan[ceoNode]) {
                    result++;
                    // 해당 파티에서는 거짓을 더이상 말하지 못하기 때문에 탈출!
                    break;
                }
            }
        }

        System.out.print(result);
        br.close();
    }

    // 대표 노드 설정
    private static void union(int[] arr, int index1, int index2) {
        // index의 대표 노드 반환
        int ceoNode1 = find(arr, index1);
        int ceoNode2 = find(arr, index2);

        // 대표노드가 다르면 대표노드 중 작은 값으로 대표노드로 설정
        if(ceoNode1 != ceoNode2) {
            if(ceoNode1 > ceoNode2) {
                arr[ceoNode1] = ceoNode2;
            } else {
                arr[ceoNode2] = ceoNode1;
            }
        }
    }

    // 대표 노드 반환
    private static int find(int[] arr, int index) {
        // index의 대표노드와 index와 같으면
        if(arr[index] == index) {
            // index가 대표노드!
            return index;
        } else {
            // index의 대표노드와 index와 다르면
            // ceoArr[index]를 index로 해서 대표 노드를 찾고
            // find 전 index의 대표노드로 갱신 해준다.
            return arr[index] = find(arr, arr[index]);
        }
    }
}
