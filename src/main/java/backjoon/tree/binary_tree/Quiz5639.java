package backjoon.tree.binary_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.TreeSet;

public class Quiz5639 {
    static StringBuilder sb =  new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int node = Integer.parseInt(br.readLine());

        // 트리 초기화
        Tree tree = new Tree(new Node(node));
        Node root = tree.getRoot();

        while (true) {
            String input = br.readLine();
            if(input == null || input.equals("")) break;
            // 이진 트리 생성
            tree.createTree(root, Integer.parseInt(input));
        }

        // 후위 순회
        tree.postorder(root);
        System.out.print(sb);
        br.close();
    }

    static class Tree {
        private Node root;

        public Tree(Node root) {
            this.root = root;
        }

        public Node getRoot() {
            return root;
        }

        public void createTree(Node node, int data) {
            // 왼쪽에 배치
            if(node.getData() > data) {
                // 왼쪽이 비어있다면
                if(node.getLeft() == null) {
                    // 왼쪽에 삽입
                    node.setLeft(new Node(data));
                }
                // 왼쪽에 값이 있으면
                else {
                    // 재탐색
                    createTree(node.getLeft(), data);
                }
            }
            // 오른쪽에 배치
            else {
                // 오른쪽이 비어있다면
                if (node.getRight() == null) {
                    // 오른쪽에 삽입
                    node.setRight(new Node(data));
                } else {
                    // 재탐색
                    createTree(node.getRight(), data);
                }
            }
        }

        public void postorder(Node root) {
            // 왼쪽 -> 오른쪽 -> 루트
            if(root.getLeft() != null) postorder(root.getLeft());
            if(root.getRight() != null) postorder(root.getRight());
            sb.append(root.getData()).append("\n");
        }
    }

    static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
