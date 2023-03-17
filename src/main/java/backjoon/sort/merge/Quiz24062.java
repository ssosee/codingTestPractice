package backjoon.sort.merge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quiz24062 {
    static int result = 0;
    static String arr2 = "";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 배열 크기
        int n = Integer.parseInt(br.readLine());
        // 배열1
        int[] arr1 = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        // 배열2
//        int[] arr2 = new int[n];
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(st.nextToken());
        }
        arr2 = sb.toString();

        int[] temp = new int[n];
        mergeSort(arr1, temp, 0,  n-1);
        System.out.print(result);

        br.close();
    }

    private static void mergeSort(int[] arr1, int[] temp, int start, int end) {
        // 병합 정렬 과정에서 arr1과 arr2가 같은 경우가 발생하면 1, 아니면 0 출력
        if(start < end) {
            int mid = start + (end - start) / 2;
            mergeSort(arr1, temp, start, mid);
            mergeSort(arr1, temp, mid+1, end);
            merge(arr1, temp, start, end, mid);
        }
    }

    private static void merge(int[] arr1, int[] temp, int start, int end, int mid) {
        int index1 = start;
        int index2 = mid + 1;
        int resultIndex = start;

        while (index1 <= mid && index2 <= end) {
            if(arr1[index1] > arr1[index2]) {
                temp[resultIndex] = arr1[index2++];
            } else {
                temp[resultIndex] = arr1[index1++];
            }
            resultIndex++;
        }

        if(index1 > mid) {
            for(int i = index2; i <= end; i++) {
                temp[resultIndex++] = arr1[i];
            }
        } else {
            for(int i = index1; i <= mid; i++) {
                temp[resultIndex++] = arr1[i];
            }
        }

        for(int i = start; i <= end; i++) {
            arr1[i] = temp[i];
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= end; i++) {
            sb.append(arr1[i]);
        }

        if(sb.toString().equals(arr2)) {
            result = 1;
            return;
        }

    }
}
