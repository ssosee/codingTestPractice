package baekjoon.sort.quick;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz1427 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();

        // 퀵 정렬을 이용하여 내림차순으로 정렬
        char[] ch = n.toCharArray();

        int start = 0;
        int end = ch.length - 1;
        qucikSortReverse(ch, start, end);

        StringBuilder sb = new StringBuilder();
        for(char c : ch) {
            sb.append(c);
        }
        System.out.print(sb);
    }

    private static void qucikSortReverse(char[] ch, int start, int end) {
        if(start >= end) return;
        int mid = partition(ch, start, end);
        qucikSortReverse(ch, start, mid - 1);
        qucikSortReverse(ch, mid, end);
    }

    private static int partition(char[] ch, int start, int end) {
        char pivot = ch[(start + end) / 2];
        while (start <= end) {
            while (pivot < ch[start]) start++;
            while (pivot > ch[end]) end--;
            if(start <= end) {
                swap(ch, start++, end--);
            }
        }
        return start;
    }

    private static void swap(char[] ch, int start, int end) {
        char temp = ch[start];
        ch[start] = ch[end];
        ch[end] = temp;
    }
}
