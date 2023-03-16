package backjoon.search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 연결 요소의 갯수
public class Quiz11724 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 노드 갯수
        int n = Integer.parseInt(st.nextToken());
        // 엣지 갯수
        int m = Integer.parseInt(st.nextToken());

        // 인접 리스트 생성(index가 해당 노드 값, 값이 해당 노드의 인접 노드 값)
        // 노드 값이 1부터 시작하기 때문에(u가 1이상) index = 0 은 사용하지 않음
        // 초기화
        List<Integer>[] linkedList = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            linkedList[i] = new ArrayList<>();
        }
        // 인접 리스트 저장
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            // 양 끝점
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            // 방향이 없기 때문에 양쪽으로 저장
            linkedList[u].add(v);
            linkedList[v].add(u);
        }
        // [null, [2, 5], [1, 5], [4], [3, 6], [2, 1] [4]]

        // 방문 확인 배열 생성(u가 1부터 시작하기 때문에 index = 0은 사용 X)
        boolean[] visited = new boolean[n+1];
        int result = 0; // 요소 갯수(완전 탐색이 완료되면 1씩 증가 한다)
        // u = 1 부터 dfs 시작
        for(int i = 1; i <= n; i++) {
            // 방문하지 않은 경우에만 dfs 실행
            if(!visited[i]) {
                dfs(linkedList, visited, i);
                result++;
            }
        }
        System.out.println(result);
    }

    private static void dfs(List<Integer>[] linkedList, boolean[] visited, int i) {
        // 방문한 이력이 있으면 실행 종료
        if(visited[i]) return;
        // 방문한 이력이 없으면 dfs 실행
        else {
            // 방문했다고 변경
            visited[i] = true;
            // 인접 노드의 크기만큼 탐색
            for(int k = 0; k < linkedList[i].size(); k++) {
                // 탐색을 원하는 노드의 인접 노드
                Integer v = linkedList[i].get(k);
                // 인접 노드가 방문 이력이 없으면 dfs 실행
                if(!visited[v]) {
                    dfs(linkedList, visited, v);
                }
            }
        }
    }
}
