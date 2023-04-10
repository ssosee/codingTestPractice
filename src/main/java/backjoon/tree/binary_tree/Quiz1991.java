package backjoon.tree.binary_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz1991 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 노드 갯수
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String parent = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();
        }
    }

    static class Node {
        private String value;
        private Node left;
        private Node right;

        public Node(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
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

    static class Tree {
        private Node root;

        public Node getRoot() {
            return root;
        }

        public void createNode(Node root, String value, String left, String right) {
            // 루트 노드인 경우
            if(root == null) {
                Node node = new Node(value);
                if(!left.equals(".")) {
                    node.setLeft(new Node(left));
                }
                if(!right.equals(".")) {
                    node.setRight(new Node(right));
                }
            } else {
                // 노드를 찾는다
                searchNode(root, value, left, right);
            }
        }

        private void searchNode(Node root, String value, String left, String right) {
            if(root == null) {
                return;
            }
            // 해당 노드에 탐색대상이 있으면
            else if(root.getValue().equals(value)) {
                if(!left.equals(".")) {
                    root.setLeft(new Node(left));
                }
                if(!right.equals(".")) {
                    root.setRight(new Node(right));
                }
            }
            // 탐색 대상이 없으면
            else {
                // 왼쪽으로 탐색
                searchNode(root.getLeft(), value, left, right);
                // 오른쪽으로 탐색
                searchNode(root.getRight(), value, left, right);
            }
        }

        public void preOrder(Node root) {
            System.out.print(root.getValue());
            if(root.getLeft() != null) preOrder(root);
            if(root.getRight() != null) preOrder(root);
        }

        public void inOrder(Node root) {
            if(root.getLeft() != null) inOrder(root);
            System.out.print(root.getValue());
            if(root.getRight() != null) inOrder(root);
        }

        public void postOrder(Node root) {
            if(root.getLeft() != null) postOrder(root);
            if(root.getRight() != null) postOrder(root);
            System.out.print(root.getValue());
        }
    }
}
