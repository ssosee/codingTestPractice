package backjoon.sort.merge;

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
        // 임시 저장소에 정렬이 된 배열 복사
        for(int i = start; i <= end; i++) {
            temp[i] = arr[i];
        }
        int index1 = start; // 왼쪽 배열 위치
        int index2 = mid + 1; // 오른쪽 배열 위치
        int resultIndex = start; // 결과 배열에 저장해야하는 위치
        while (index1 <= mid && index2 <= end) {
            if(temp[index1] <= temp[index2]) {
                arr[resultIndex] = temp[index1];
                index1++;
            } else {
                arr[resultIndex] = temp[index2];
                index2++;
            }
            resultIndex++;
        }
        // 만약 뒷쪽 배열(temp)은 비었고, 앞쪽 배열(temp)에 데이터가 남은 경우
        for(int i = 0; i <= mid - index1; i++) {
            arr[resultIndex + i] = temp[index1 + i];
        }
        // 만약 앞쪽 배열(temp)은 비었고, 뒷쪽 배열(temp)에 데이터가 남은 경우
        // 뒷쪽 배열은 이미 arr의 뒷쪽에 자리하고 있음
    }
}
