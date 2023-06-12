package baekjoon.datastructure.stack_queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Quiz11279 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 내림차순 정렬
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));

        int n = Integer.parseInt(br.readLine());

        int x = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            x = Integer.parseInt(br.readLine());
            if(x == 0) {
                if(!queue.isEmpty()) {
                    sb.append(queue.poll()).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            } else {
                queue.add(x);
            }
        }

        System.out.println(sb);
    }
}
