package backjoon.tree.binary_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz9934 {
    static int k = 0;
    public static void main(String[] args) throws IOException {
        // 상근이가 중위 순회로 방문함
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());

        // 노드 갯수
        int nodeNum = (int) Math.pow(2, k) - 1;

        // 경로 저장
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] path = new int[nodeNum];
        for(int i = 0; i < nodeNum; i++) {
            int node = Integer.parseInt(st.nextToken());
            path[i] = node;
        }

        // 지도 초기화
        List<Integer>[] map = new ArrayList[k];
        for(int i = 0; i < k; i++) {
            map[i] = new ArrayList<>();
        }

        // 지도 생성
        createMap(map, path, 0, 0, path.length - 1);

        // 지도 출력
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < k; i++) {
            for(Integer building : map[i]) {
                sb.append(building).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
        sb.setLength(0);
        br.close();
    }

    private static void createMap(List<Integer>[] map, int[] path, int depth, int start, int end) {

        // 완전 이진트리이기 때문에 k 만큼의 깊이가 생김
        if(depth == k) return;

        // 상근이가 중위 순회로 방문(왼쪽 서브트리 순회 -> 현재 방문 -> 오른쪽 서브트리 순회)
        // 방문 배열의 중간이 부모 노드
        int mid = (start + end) / 2;
        // 경로 저장
        map[depth].add(path[mid]);

        // 왼쪽으로 탐색
        createMap(map, path, depth + 1, start, mid - 1);
        // 오른쪽으로 탐색
        createMap(map, path, depth + 1, mid + 1, end);
    }
}