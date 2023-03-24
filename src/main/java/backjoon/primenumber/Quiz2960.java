package backjoon.primenumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz2960 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for(int i = 1; i <= arr.length; i++) {
            arr[i-1] = i; // [1, 2, 3, 4, 5]
        }

        int count = 0;
        // 소수도 지워야하기 때문에 n까지 탐색
        for(int i = 0; i < n; i++) {
            if(arr[i] == 1) continue;
            else {
                count++;
                if (count == k) {
                    System.out.print(arr[i]);
                    break;
                }
                for (int j = i + 1; j < n; j++) {
                    if (arr[j] != 1 && arr[j] % arr[i] == 0) {
                        count++;
                        if (count == k) {
                            System.out.print(arr[j]);
                            break;
                        }
                        arr[j] = 1;
                    }
                }
            }
        }
        br.close();
    }
}
