package backjoon.sort.insertion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz2750 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 1; i < n; i++) {
            int index = i;
            int target = arr[index]; // 선택
            while (index > 0 && arr[index-1] > target) {
                arr[index] = arr[index-1]; // shift
                index--;
            }
            arr[index] = target;
        }

        StringBuilder sb = new StringBuilder();
        for(int i : arr) {
            sb.append(i).append("\n");
        }

        System.out.println(sb);
    }
}
