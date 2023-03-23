package backjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz1026 {
    public static void main(String[] args) throws IOException {
        // S = A[0] × B[0] + ... + A[N-1] × B[N-1]
        // S 값이 최소가 되게 A를 정렬
        // S가 최소가 되기 위해서는 B의 최댓값과 A의 최솟값을 곱하면 된다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 배열의 크기
        int n = Integer.parseInt(br.readLine());

        // 배열 A
        Integer[] A = new Integer[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        // 배열 B
        // B 배열은 재정렬하면 안됨.
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            queue.add(Integer.parseInt(st.nextToken()));
        }

        int result = 0;
        int i = 0;
        while (!queue.isEmpty()) {
            result += A[i] * queue.poll();
            i++;
        }

        System.out.print(result);
        br.close();
    }

    static class B {
        private int num;

        public B(int num) {
            this.num = num;
        }
    }
}
