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
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] tree = new int[n];
        st = new StringTokenizer(br.readLine());
        long max = Long.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, tree[i]);
        }

        long result = getUpperBound(tree, m, max + 1);
        System.out.print(result - 1);

        br.close();
    }

    private static long getUpperBound(int[] tree, int target, long max) {
        long min = 0;
        while (min < max) {
            long mid = min + (max - min) / 2;
            long goal = 0;
            for(int i = 0; i < tree.length; i++) {
                if(tree[i] - mid > 0) {
                    goal += (tree[i] - mid);
                }
            }

            if(goal < target) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }
}
