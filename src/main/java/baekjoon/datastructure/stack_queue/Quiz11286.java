package baekjoon.datastructure.stack_queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 연산 갯수
        int n = Integer.parseInt(br.readLine());
        // x
        int[] x = new int[n];
        for(int i = 0; i < n; i++) {
            x[i] = Integer.parseInt(br.readLine());
        }

        /**
         * 1. 절대값이 같으면 가장 작은수 부터 출력(입력값 기준 오름차순)
         * 2. 절댓값이 가장 작은 값부터 출력(절댓값 기준 오름차순)
         */
        Queue<Integer> queue = new PriorityQueue<>(
                (o1, o2) -> Math.abs(o1) == Math.abs(o2) ? Integer.compare(o1, o2) : Integer.compare(Math.abs(o1), Math.abs(o2))
        );

        StringBuilder sb = new StringBuilder();
        for(Integer v : x) {
            if(v == 0) {
                if(!queue.isEmpty()) {
                    sb.append(queue.poll()).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            } else {
                queue.add(v);
            }
        }
        System.out.println(sb);
    }
}
