package backjoon.datastructure.stack_queue;

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
         * x가 0이 아닐 경우
         *  배열에 값 추가
         * x가 0인 경우
         *  만약 배열이 비어있는 경우 0을 출력
         *  배열에서 절댓값이 가장 작은 값 출력하고(여러개면 가장 작은 수 출력) 배열에서 제거
         *
         *  우선순위 큐를 이용함
         *  우선순위 큐는 기본적으로 min heap
         *
         *  기본 값
         *  o1 < o2 : -1
         *  o1 == o2 : 0
         *  o1 > o2  : 1
         *  [o1, o2, o3, o4 , ...]
         */
        // 우선순위 큐 정렬설정 변경
        // 절대값이 같으면 오름차순 정렬(1, -1) -> [1, -1]
        // 아니면 절대값으로 오름차순 정렬(-2, -4) -> [4, 2]
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            /**
             * 1. 절댓값이 가장 작은 값부터 출력
             * 2. 절대값이 같으면 가장 작은수 부터 출력
             */
            @Override
            public int compare(Integer o1, Integer o2) {
                // 절대값이 같으면 그중에서 작은 수로 오름차순
                if(Math.abs(o1) == Math.abs(o2)) {
                    return Integer.compare(o1, o2);
                }
                // 아니면 절대값이 작은 수로 오름차순
                else {
                    return Integer.compare(Math.abs(o1), Math.abs(o2));
                }
            }
        });

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
