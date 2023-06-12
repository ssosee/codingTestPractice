package baekjoon.sort.merge;

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

        // 임시 저장 공간
        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length-1);
        StringBuilder sb = new StringBuilder();
        for(int i : arr) {
              sb.append(i).append("\n");
        }

        System.out.println(sb);
    }

    private static void mergeSort(int[] arr, int[] temp, int start, int end) {
        if(start < end) {
            int mid = start + (end - start) / 2; // 배열을 가운데로 자름(overflow 방지)
            mergeSort(arr, temp, start, mid); // 분할
            mergeSort(arr, temp, mid+1, end); // 분할
            // 병합(왼쪽, 오른쪽 정렬 상태)
            merge(arr, temp, start, mid, end);
        }
    }

    private static void merge(int[] arr, int[] temp, int start, int mid, int end) {
        int index1 = start; // 왼쪽 배열 시작 위치
        int index2 = mid + 1; // 오른쪽 배열 시작 위치
        int resultIndex = start; // 결과 배열에 저장해야하는 위치
        while (index1 <= mid && index2 <= end) {
            if(arr[index1] <= arr[index2]) {
                temp[resultIndex] = arr[index1];
                index1++;
            } else {
                temp[resultIndex] = arr[index2];
                index2++;
            }
            resultIndex++;
        }

        // 남은 데이터 삽입
        // index1이 먼저 끝났다면 index2의 데이터 삽입
        if(index1 > mid) {
            for(int i = index2; i <= end; i++) {
                temp[resultIndex] = arr[i];
                resultIndex++;
            }
        } else {
            for(int i = index1; i <= mid; i++) {
                temp[resultIndex] = arr[i];
                resultIndex++;
            }
        }

        // 실제 배열로 옮기는 과정
        for(int i = start; i <= end; i++) {
            arr[i] = temp[i];
        }
    }
}
