package baekjoon.sort.quick;

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

        quickSort(arr, 0, arr.length-1);
        StringBuilder sb = new StringBuilder();
        for(int i : arr) {
            sb.append(i).append("\n");
        }

        System.out.println(sb);
    }

    private static void quickSort(int[] arr, int start, int end) {
        if(start >= end) return;
        int mid = partitionSort(arr, start, end); // 오른쪽 그룹의 첫 번째 index
        /**
         * 오른쪽 파티션이 시작점 바로 다음에서 시작한다면
         * 왼쪽 파티션의 데이터가 1개 뿐이니 정렬할 필요가 없음
         * 따라서 오른쪽 파티션의 시작점(mid)이 왼쪽 파티션의 시작점(start)보다 최소 2는 커야함
         */
        if(mid > start + 1) {
            quickSort(arr, start, mid - 1); //왼쪽 그룹
        }
        /**
         * 오른쪽 파티션이 1개 이상일 때만 호출해야함
         * 오른쪽 파티션의 시작점(mid)이 끝점(end) 보다 작을때만 호출한다.
         */
        if(mid < end) {
            quickSort(arr, mid, end); // 오른쪽 그룹
        }
    }

    private static int partitionSort(int[] arr, int start, int end) {
        // 리스트의 가운데 있는 pivot 값을 선택
        int pivot = arr[(start + end) / 2];

        // 시작 인덱스(start)는 계속 증가 시키고,
        // 끝 인덱스(end)는 계속 감소 시키기위한 while 루프를
        // 두 인덱스가 서로 교차해서 지나칠 때까지 반복
        while (start <= end) {
            // 시작 인덱스(start)가 가리키는 값과 pivot 값을 비교해서
            // 더 작은 경우 반복해서 시작 인덱스 값을 증가
            // (pivot 값보다 큰데 좌측에 있는 값을 찾기 위해)
            while (arr[start] < pivot) start++;

            // 끝 인덱스(end)가 가리키는 값과 pivot 값을 비교해서
            // 더 작은 경우 반복해서 끝 인덱스 값을 감소
            // (pivot 값보다 작은데 우측에 있는 값을 찾기 위해)
            while (arr[end] > pivot) end--;

            // 두 인덱스가 아직 서로 교차해서 지나치치 않았다면
            // 시작 인덱스(start)가 가리키는 값과 끝 인덱스(end)가 가리키는 값을 swap
            // (잘못된 위치에 있는 두 값의 위치를 바꾸기 위해)
            if(start <= end) {
                // swap 후, 다음 값을 가리키기 위해 두 인덱스를 각자 진행 방향으로 한 칸씩 이동
                swap(arr, start++, end--);
            }
        }
        // 두 인덱스가 서로 교차해서 지나치게 되어 while 루프를 빠져나왔다면
        // 다음 재귀 호출의 분할 기준점이될 시작 인덱스를 리턴합니다.
        return start;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
