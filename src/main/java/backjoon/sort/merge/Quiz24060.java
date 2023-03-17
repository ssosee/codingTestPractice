package backjoon.sort.merge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Quiz24060 {
    static int k = 0;
    static int count = 0;
    static int result = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 배열 크기
        int n = Integer.parseInt(st.nextToken());
        // k번째
        k = Integer.parseInt(st.nextToken());

        // 배열
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 임시저장소
        int[] temp = new int[n];
        mergeSort(arr, temp, 0, n-1);
        System.out.print(result);

        br.close();
    }

    private static void mergeSort(int[] arr, int[] temp, int start, int end) {
        if(start < end) {
            int mid = start + (end - start) / 2;
            mergeSort(arr, temp, start, mid); // 왼쪽 분할
            mergeSort(arr, temp, mid+1, end); // 오른쪽 분할
            merge(arr, temp, start, end, mid); // 병합
        }
    }

    private static void merge(int[] arr, int[] temp, int start, int end, int mid) {
        int index1 = start; // 왼쪽 배열 첫 시작점
        int index2 = mid + 1; // 오른쪽 배열 첫 시작점
        int resultIndex = start;

        while (index1 <= mid && index2 <= end) {
            if(arr[index1] > arr[index2]) {
                temp[resultIndex] = arr[index2++];
            } else {
                temp[resultIndex] = arr[index1++];
            }
            resultIndex++;
        }

        // 왼쪽이 먼저 끝났을 경우 오른쪽 배열을 채워야함.
        if(index1 > mid) {
            for(int i = index2; i <= end; i++) {
                temp[resultIndex++] = arr[i];
            }
        } else {
            for(int i = index1; i <= mid; i++) {
                temp[resultIndex++] = arr[i];
            }
        }

        // 임시배열에서 결과배열로 값 복사
        for(int i = start; i <= end; i++) {
            count++;
            arr[i] = temp[i];
            if(k == count) {
                result = arr[i];
                break;
            }
        }
    }
}
