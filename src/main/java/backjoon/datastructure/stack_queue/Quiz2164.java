package backjoon.datastructure.stack_queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Quiz2164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 카드 숫자
        int n = Integer.parseInt(br.readLine());
        // 카드 넣기
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= n; i++) {
            q.add(i); // 4, 3, 2, 1
        }

        /**
         * 1. 맨위 카드 버리기(poll)
         * 2. 맨위 카드 아래에 넣기
         * 3. 1, 2 반복후 맨 마지막에 남는 카드 출력
         */
        while (q.size() > 1) {
            q.poll(); // 1 버림 4, 3, 2
            q.add(q.poll()); // 2를 맨아래에 넣음 2, 3, 4
        }
        System.out.println(q.poll());
    }
}
