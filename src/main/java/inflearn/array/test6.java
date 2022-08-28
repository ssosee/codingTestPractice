package inflearn.array;

import java.util.*;

/**
 * 뒤집은 소수
 */
public class test6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        List<Integer> primeNumbers = new ArrayList<>();
        Solution solution = new Solution();
        for(int i = 0; i < arr.length; i++) {
            int number = solution.reverseInteger(arr[i]);
            if(solution.isPrimeNumber(number)) {
                primeNumbers.add(number);
            }
        }

        for(Integer number : primeNumbers) {
            System.out.print(number+" ");
        }
    }

    static class Solution {
        //숫자 뒤집기
        public int reverseInteger(int value) {
            int temp = value;
            int result = 0;
            while (temp > 0) {
                int tail = temp % 10; //1의 자리
                result = result*10 + tail;
                temp = temp/10; //자릿수 내리기
            }
            return result;
        }
        //소수 확인
        public boolean isPrimeNumber(int value) {

            if(value == 1) return false;
            for(int i = 2; i < value; i++) {
                if(value % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
