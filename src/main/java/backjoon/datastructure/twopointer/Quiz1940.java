package backjoon.datastructure.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quiz1940 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 재료 갯수
        int n = Integer.parseInt(br.readLine());
        // 갑옷을 만드는데 필요한 숫자
        int m = Integer.parseInt(br.readLine());
        // 재료의 고유한 번호
        StringTokenizer st = new StringTokenizer(br.readLine());
        int arr[] = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 오름차순 정렬
        Arrays.sort(arr);
        // 1 2 3 4 5 7

        // 2개의 재료의 고유한 번호의 합이 m이 되어야 함
        // 재료를 사용하면 없어짐
        // 양끝점에 포인터를 설정
        int sp = 0;
        int ep = n-1;
        int count = 0;
        int sum = 0;
        while (sp < ep) {
            sum = arr[sp] + arr[ep];
            if(sum == m) {
                count++;
                sp++;
                ep--;
            } else if (sum < m) {
                sp++;
            } else if (sum > m) {
                ep--;
            }
        }
        System.out.println(count);
    }
}
