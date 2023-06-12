package baekjoon.tree.binary_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz2263 {
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int[] inorder;
    static int[] postorder;
    static int[] inorderIndex;
    public static void main(String[] args) throws IOException {
        // 중위 순회와, 후위 순회가 주어질 때 전위 순회를 출력
        // 중위 순회와 후위 순회를 이용하여 트리의 원형을 만들고 전위 순회를 출력 한다.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 노드 갯수
        n = Integer.parseInt(br.readLine());

        // 중위 순회 저장
        inorder = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
        }

        // 후위 순회 저장
        postorder = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }

        // 중위 순회 노드의 인덱스를 저장하는 배열
        inorderIndex = new int[n+1];
        for(int i = 1; i <= n ; i++) {
            // 인덱스: 중위순회 노드, 값: 중위순회 인덱스
            inorderIndex[inorder[i]] = i;
        }

        createTree(1, inorder.length - 1, 1, postorder.length - 1);

        System.out.print(sb);
        br.close();
    }

    private static void createTree(int inStart, int inEnd, int postStart, int postEnd) {
        //System.out.println("inStart="+inStart+", inEnd="+inEnd+", postStart="+postStart+", postEnd="+postEnd +", 정지 여부="+(postStart > postEnd || inStart > inEnd));
        // 중위, 후위 순회의 시작점이 종료점 보다 크면
        if(postStart > postEnd || inStart > inEnd) return;

        int parent = postorder[postEnd];
        // 중위 순회의 부모노드 인덱스
        int parentIndex = inorderIndex[parent];

        // 출력
        sb.append(parent).append(" ");

        // 중위 순회에서 루트 기준 왼쪽에 몇개가 있는지 계산
        int leftSize = parentIndex - inStart;

        // 좌측 탐색
        // 좌측 배열 끝점 = postStart + leftSize - 1 = 후위순위 시작점 + 좌측 탐색 대상의 배열 길이 - 루트노드 길이
        createTree(inStart, parentIndex - 1, postStart, postStart + leftSize - 1);
        // 우측 탐색
        // 우측 배열 시작점 = postStart + leftSize = 후위 배열 시작점 + 좌측 탐색 대상의 배열 길이
        createTree(parentIndex + 1, inEnd, postStart + leftSize, postEnd - 1);

    }
}
