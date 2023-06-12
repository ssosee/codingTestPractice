package baekjoon.datastructure.stack_queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 수열이 담긴 배열
        int n = Integer.parseInt(br.readLine());
        int[] query = new int[n];
        for(int i = 0; i < n; i++) {
            query[i] = Integer.parseInt(br.readLine()); // 수열이 담긴 배열
        }

        /**
         * 1. 수열이 담긴 배열의 크기만큼 반복
         *  1-1. 만약 자연수의 값이 수열의 값보다 작거나 같으면
         *   1-1-1. 자연수의 값이 수열의 값보다 작거나 같을때까지 반복(num <= query[i])
         *    1-1-1-1. push(자연수++), result(+)
         *   1-1-2. pop, result(-)
         *  1-2. 그외
         *   1-2-1. pop
         *   1-2-2. 만약 pop한 값이 수열의 값보다 크면 NO
         *   1-2-3. 그외 result(-)
         */
        Stack<Integer> stack = new Stack<>();
        StringBuilder result = new StringBuilder(); // 단일스레드거나 동기화 고려X, StringBuffer는 멀티스레드 동기화 고려O
        int num = 1; // 자연수
        for(int i = 0; i < n; i++) {
            if(query[i] >= num) {
                while (query[i] >= num) {
                    stack.push(num++);
                    result.append("+\n");
                }
                stack.pop();
                result.append("-\n");
            } else {
                Integer pop = stack.pop();
                if(pop > query[i]) {
                    System.out.println("NO");
                    return;
                }
                result.append("-\n");
            }
        }


        System.out.println(result);

        br.close();
    }
}
