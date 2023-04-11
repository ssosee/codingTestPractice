package backjoon.tree.binary_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Quiz2263 {
    public static void main(String[] args) throws IOException {
        // 중위 순회와, 후위 순회가 주어질 때 전위 순회를 출력
        // 중위 순회와 후위 순회를 이용하여 트리의 원형을 만들고 전위 순회를 출력 한다.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 노드 갯수
        int n = Integer.parseInt(br.readLine());

        // 중위 순회 저장
        int[] inorder = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
        }

        // 후위 순회 저장
        int[] postorder = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer>[] tree = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        boolean[] visited = new boolean[n+1];
        createTree(visited, tree, inorder, postorder, 0, 1, inorder.length - 1, 0, postorder.length - 1, n);

        System.out.println(1);
    }

    private static void createTree(boolean[] visited, List<Integer>[] tree, int[] inorder, int[] postorder, int depth, int inStart, int inEnd, int postStart, int postEnd, int n) {

    }


}
