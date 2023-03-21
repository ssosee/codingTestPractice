package backjoon.search.binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quiz2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 나무 갯수
        int treeNum = Integer.parseInt(st.nextToken());
        // 총 나무 길이(target)
        int target = Integer.parseInt(st.nextToken());

        // 나무
        st = new StringTokenizer(br.readLine());
        int[] tree = new int[treeNum];
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < treeNum; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, tree[i]);
        }

        // 절단기의 최대 높이(mid의 최대 높이 -> upperBound - 1)
        // max + 1 까지 탐색 해야함
        long result = getUpperBound(tree, target, max + 1);
        System.out.print(result - 1);
        br.close();
    }

    static private long getUpperBound(int[] tree, int target, long max) {
        long min = 0;
        while (min < max) {
            // overFlow 방지
            long mid = min + (max - min) / 2;
            /**
             * 나무 길이를 조절하면서 자른 나무 길이의 합을 탐색해야한다.
             */
            // 탐색한 나무의 길이 총합
            long sumLen = 0;
            for(int i = 0; i < tree.length; i++) {
                // 나무길이(tree[i]) 에서 우리가 정한 길이(mid)로 자른 합(len)을 구함
                // 문제 조건으로 나무를 안 잘라도 되기 때문에 양수인 것만 자른다.
                if(tree[i] - mid > 0) {
                    sumLen += (tree[i] - mid);
                }
            }

            // 원하는 나무 길이(target)가 방금 탐색한 나무 길이(sumLen) 총합 보다 크면
            if(target > sumLen) {
                // 나무를 잘랐을 때 이전 보다 더 커야함.
                // 자르는 길이(mid)를 줄이면 더 크게 자를 수 있다.
                // max를 줄이면 자르는 길이(mid)가 줄어 든다.
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }
}
