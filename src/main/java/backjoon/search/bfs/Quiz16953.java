package backjoon.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Quiz16953 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int number = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        long dfs = bfs(number, target);
        System.out.print(dfs + 1);
        br.close();
    }
    private static long bfs(int number, int target) {
        Queue<Node> q = new ArrayDeque<>();
        int count = 0;
        q.offer(new Node(number, count));

        while (!q.isEmpty()) {
            Node poll = q.poll();

            // 타겟 값을 찾으면
            if(poll.getNumber() == target) {
                return poll.getCount() + 1;
            }

            count = poll.getCount() + 1;
            long case1 = poll.getNumber() * 2L;
            long case2 = poll.getNumber() * 10L + 1L;

            // 타겟값보다 작은 숫자만 큐에 삽입
            if(case1 <= target) {
                q.add(new Node(case1, count));
            }
            if(case2 <= target) {
                q.add(new Node(case2, count));
            }
        }

        return -1;
    }

    static class Node {
        private long number;
        private int count;

        public Node(long number, int count) {
            this.number = number;
            this.count = count;
        }

        public long getNumber() {
            return number;
        }

        public int getCount() {
            return count;
        }
    }
}
