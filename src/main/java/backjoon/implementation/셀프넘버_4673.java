package backjoon.implementation;

public class 셀프넘버_4673 {
    public static void main(String[] args) {
        boolean[] check = new boolean[10_001];

        for(int i = 1; i < 10_001; i++) {
            // 생성자가 있는 수 생성
            int n = d(i);

            if(n <= 10000) {
                check[n] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < 10_001; i++) {
            // 생성자가 없는 수 이면
            if(!check[i]) sb.append(i).append("\n");
        }

        System.out.print(sb);
    }

    private static int d(int n) {
        int answer = 0;
        int temp = n;

        while (temp > 0) {
            answer += temp % 10;
            temp /= 10;
        }

        return answer + n;
    }
}
