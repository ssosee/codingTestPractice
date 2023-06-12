package baekjoon.sort.merge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Practice {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 병합 정렬을 이용
        int[] temp = new int[n]; // 임시 배열 저장소
        sort(arr, temp, 0, n-1);

        StringBuilder sb = new StringBuilder();
        for(int i : arr) {
            sb.append(i).append("\n");
        }

        System.out.println(sb);
    }

    private static void sort(int[] arr, int[] temp, int start, int end) {
        if(start < end) {
            int mid = start + (end - start) / 2;
            sort(arr, temp, start, mid); // 왼쪽으로 쪼개기
            sort(arr, temp, mid+1, end); // 오른쪽으로 쪼개기
            merge(arr, temp, start, mid, end); // 합치기
        }
    }

    private static void merge(int[] arr, int[] temp, int start, int mid, int end) {
        int index1 = start; // 왼쪽 배열 첫 시작점
        int index2 = mid + 1; // 오른쪽 배열 첫 시작점
        int originIndex = start; // 정렬을 원하는 배열의 시작점
        while (index1 <= mid && index2 <= end) {
            if(arr[index1] < arr[index2]) {
                temp[originIndex] = arr[index1];
                index1++;
            } else {
                temp[originIndex] = arr[index2];
                index2++;
            }
            originIndex++;
        }

        // 왼쪽이 먼저 다 넣어지면? 오른쪽 값을 채워야 함
        // 오른쪽이 먼저 다 넣어지면? 왼쪽 값을 채운다.
        if(index1 > mid) {
            for(int i = index2; i <= end; i++) {
                temp[originIndex] = arr[i];
                originIndex++;
            }
        } else {
            for(int i = index1; i <= mid; i++) {
                temp[originIndex] = arr[i];
                originIndex++;
            }
        }

        // 결과 배열에 정렬된 것을 적용
        for(int i = start; i <= end; i++) {
            arr[i] = temp[i];
        }
    }
}
