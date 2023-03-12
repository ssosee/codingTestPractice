package backjoon.sort.bubble;

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

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n-1; i++) {
            // j < n-1-i 정렬 완료된 부분은 swap 시도를 하지 않음
            for(int j = 0; j < n-1-i; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        for(int i : arr) {
            sb.append(i).append("\n");
        }

        System.out.println(sb);
    }
}
