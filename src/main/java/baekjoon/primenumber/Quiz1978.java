package baekjoon.primenumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Quiz1978 {
    public static void main(String[] args) throws IOException {
        List<Integer>[] list1 = new ArrayList[10];
        List<Node> list = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            if(arr[i] == 1) continue;
            for(int j = 2; j <= Math.sqrt(arr[i]); j++) {
                if(arr[i] % j == 0) {
                    arr[i] = 1;
                }
            }
        }

        int count = 0;
        for(Integer i : arr) {
            if(i != 1) count++;
        }
        System.out.print(count);
        br.close();
    }
    static class Node {
        private int n;
        private int v;

        public Node(int n, int v) {
            this.n = n;
            this.v = v;
        }
    }
}
