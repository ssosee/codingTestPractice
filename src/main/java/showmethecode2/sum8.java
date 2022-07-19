package showmethecode2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class sum8 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        String str3 = br.readLine();

        System.out.println(new Solution().solution(str1, str2, str3));
    }

    static class Solution {
        public Long solution(String str1, String str2, String str3) {
            Long count = 0L;

            Long n = Long.parseLong(str1);
            String[] a = str2.split(" ");
            String[] b = str3.split(" ");

            for(int i = 0; i < n; i++) {
                for(int j = i; j < n; j++) {

                    Long sum1 = sum(i, j, a);
                    Long sum2 = sum(i, j, b);
                    //result1 += Long.parseLong(a[i]);
                    //result2 += Long.parseLong(b[i]);
                    System.out.println("i = "+i+", j = "+j+", sum1 = "+sum1+", sum2 = "+sum2);
                    if(sum1 != 0 && sum2 != 0) {
                        if(sum1.equals(sum2)) {
                            count++;
                        }
                    }
                }
            }

            return count;
        }

        public Long sum(int i, int j, String[] str) {

            Long result = 0L;

            for(; i <= j; i++) {
                result += Long.parseLong(str[i]);
            }

            return result;
        }
    }
}
