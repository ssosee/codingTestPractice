package baekjoon.search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz2606 {
    // 1번 노드와 연관 있는 컴퓨터 갯수 출력
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 노드 수(컴퓨터 갯수)
        int nodeNum = Integer.parseInt(br.readLine());
        // 간선 갯수(네트워크 연결 수)
        int edgeNum = Integer.parseInt(br.readLine());

        // 인접 리스트 생성
        // 컴퓨터는 1번부터 시작(0번 index를 사용하지 않는다.)
        List<Integer>[] linkedList = new ArrayList[nodeNum+1];
        // 초기화
        for(int i = 1; i <= nodeNum; i++ ){
            linkedList[i] = new ArrayList<>();
        }

        for(int i = 1; i <= edgeNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            // 양방향 관계
            linkedList[start].add(end);
            linkedList[end].add(start);
        }

        // 방문 이력 배열 생성(컴퓨터는 1번 부터 시작, index가 컴퓨터 번호임)
        boolean[] visited = new boolean[nodeNum+1];
        // 1번 컴퓨터만 dfs 실행
        dfs(linkedList, visited, 1);
        System.out.println(count);
    }

    private static void dfs(List<Integer>[] linkedList, boolean[] visited, int i) {
        if(!visited[i]) {
            visited[i] = true;
            for (int k = 0; k < linkedList[i].size(); k++) {
                Integer v = linkedList[i].get(k);
                if (!visited[v]) {
                    dfs(linkedList, visited, v);
                    count++;
                }
            }
        }
    }
}
