package backjoon.sort.quick;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz1427 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();

        // 퀵 정렬을 이용하여 내림차순으로 정렬
        char[] ch = n.toCharArray();
        /**
         * pivot 설정
         * A[pivot] > A[start] ->
         *   start++;
         *
         * A[pivot] < A[end] ->
         *   end--;
         *
         * A[pivot] < A[start] && A[pivot] > A[end] ->
         *   A[start] swap A[end]; start++; end--;
         *
         * start == end ->
         *   A[pivot] > A[start] -> A[pivot] swap A[++start],
         *   A[pivot] < A[start] -> A[pivot] swap A[--start]
         */
        int start = 0;
        int end = ch.length-2;
        int pivot = ch.length-1;
        while (start <= end) {

        }



        StringBuilder sb = new StringBuilder();
        for(char c : ch) {
            sb.append(c);
        }
        System.out.println(sb);
    }
}
