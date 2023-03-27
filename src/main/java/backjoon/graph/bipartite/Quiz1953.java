package backjoon.graph.bipartite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz1953 {
    /**
     * 5        // 학생수
     * 1 3      // 1번이 싫어하는 사람 수 1명, 3번이랑 팀 X
     * 1 5      // 2번이 싫어하는 사람 수 1명, 5번이랑 팀 X
     * 2 1 4    // 3번이 싫어하는 사람 수 2명, 1, 4번이랑 팀 X
     * 1 3      // 4번이 싫어하는 사람 수 1명, 3번이랑 팀 X
     * 1 2      // 5번이 싫어하는 사람 수 1명, 2번이랑 팀 X
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 학생 수
        int n = Integer.parseInt(br.readLine());
        
        // 인접 리스트 초기화
        List<Integer>[] list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        
        // 인접 리스트 저장
        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 싫어하는 사람 수
            int num = Integer.parseInt(st.nextToken());
            for(int j = 0; j < num; j++) {
                // 싫어하는 사람
                int hateMan = Integer.parseInt(st.nextToken());
                
                // 서로 싫어함
                list[i].add(hateMan);
                list[hateMan].add(i);
            }
        }
        
        // 탐색 이력
        boolean[] visited = new boolean[n+1];
        // 팀
        int[] group = new int[n+1];
        // 학생 수 만큼 탐색
        for(int i = 1; i <= n; i++) {
            dfs(list, visited, group, i);
        }

        // 청팀, 백팀
        // group의 index가 사람, group[index]가 팀
        int count0 = 0;
        int count1 = 0;
        List<Integer> count0List = new ArrayList<>();
        List<Integer> count1List = new ArrayList<>();
        for(int i = 1; i <= group.length - 1; i++) {
            if(group[i] == 0) {
                count0++;
                count0List.add(i);
            } else {
                count1++;
                count1List.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(count0).append("\n");
        for(Integer i : count0List) {
            sb.append(i).append(" ");
        }

        sb.append("\n").append(count1).append("\n");
        for(Integer i : count1List) {
            sb.append(i).append(" ");
        }

        System.out.print(sb);
        br.close();
    }

    private static void dfs(List<Integer>[] list, boolean[] visited, int[] group, int start) {
        visited[start] = true;
        for(int i = 0; i < list[start].size(); i++) {
            // 인접 노드
            Integer adjacentNode = list[start].get(i);
            // 인접 노드에 방문 이력이 없으면
            if(!visited[adjacentNode]) {
                // 서로 같은 팀이면 청팀, 백팀 구분한다.
                if(group[adjacentNode] == group[start]) {
                    group[adjacentNode] = (group[start] + 1) % 2;
                }
                dfs(list, visited, group, adjacentNode);
            }
        }
    }
}
