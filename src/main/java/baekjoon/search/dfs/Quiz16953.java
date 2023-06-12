package baekjoon.search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Quiz16953 {
    static int myDepth = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int number = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

//        dfs(number, target, 0);
//
//        if(myDepth == 0) myDepth = -1;
//        System.out.println(myDepth);


        System.out.println(dfsWithStack(number, target));
        br.close();
    }

    private static void dfs(long number, int target, int depth) {
        if(number == target) {
            myDepth = depth + 1;
            return;
        }

        long case1 = number * 2L;
        long case2 = number * 10L + 1L;
        if(case1 <= target) {
            dfs(case1, target, depth + 1);
        }
        if(case2 <= target) {
            dfs(case2, target, depth + 1);
        }
    }

    private static int dfsWithStack(long number, int target) {
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(number, 0));

        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            if(pop.getNumber() == target) {
                return pop.getDepth() + 1;
            }

            long case1 = pop.getNumber() * 2L;
            long case2 = pop.getNumber() * 10L + 1L;
            int depth = pop.getDepth() + 1;

            if(case1 <= target) {
                stack.push(new Node(case1, depth));
            }
            if(case2 <= target) {
                stack.push(new Node(case2, depth));
            }
        }

        return -1;
    }

    static class Node {
        private long number;
        private int depth;

        public Node(long number, int depth) {
            this.number = number;
            this.depth = depth;
        }

        public long getNumber() {
            return number;
        }

        public int getDepth() {
            return depth;
        }
    }
}
