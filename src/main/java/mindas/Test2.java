package mindas;

import java.util.*;

public class Test2 {
    public static void main(String[] args) {
        long solution = solution(2, 100, 10, new String[]{"fan", "Fan"}, new int[]{1, 2});
        // long solution = solution(2, 1, 10000, new String[]{"fan", "Fan"}, new int[]{1, 2});
        System.out.print(solution);
    }
    static public long solution(int n, int c, int d, String[] a, int[] b) {
        boolean flagG1 = false;
        // G1 세율
        double taxG1 = c / 100.0d;
        // G2 세율
        double taxG2 = d / 100.0d;

        // 세율로 그룹 나누기
        double minTax = Double.MIN_VALUE;
        if(taxG1 > taxG2) {
            minTax = taxG2;
            flagG1 = false;

        } else {
            minTax = taxG1;
            flagG1 = true;
        }

        List<Double> refund = new ArrayList<>();
        // G1 일경우
        if(flagG1) {
            for(int i = 0; i < n; i++) {
                double temp = b[i] - (b[i] * minTax);
                refund.add(temp);
            }
            double sum = refund.stream()
                    .mapToDouble(r -> r.doubleValue())
                    .sum();
            return (long) (sum * 100.0d);
        }
        // G2일 경우
        else {
            double sum = Arrays.stream(b).sum();
            sum = sum - (minTax * Math.pow(sum, 2));
            return (long) (sum * 100.0d);
        }
    }
}
