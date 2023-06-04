package backjoon2.implementation;

public class 셀프넘버_4673 {
    public static void main(String[] args) {
        boolean[] check = new boolean[10_001];
        for(int i = 1; i <= 10_000; i++) {
            // 생성자가 존재하는 10,000이하의 숫자를 인덱스로 저장
            int number = d(i);
            if(number <= 10_000) {
                check[number] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= 10_000; i++) {
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
