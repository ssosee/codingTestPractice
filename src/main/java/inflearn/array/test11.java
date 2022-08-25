package inflearn.array;

import java.io.*;
import java.util.*;

/**
 * 임시반장 정하기
 */
public class test11 {

    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        int num = in.nextInt();
        int arr[][] = new int[num][5];

        // 값 저장.
        for(int i = 0; i < num; i++) {
            for(int j = 0; j < 5; j++) {
                arr[i][j] = in.nextInt();
            }
        }

        //비교
        int max = 1000;
        int answer = 0;
        //사람1
        for(int i = 0; i < num; i++) {
            int cnt = 0;
            //사람2
            for(int j = 0; j < num; j++) {
                //학년
                for(int k = 0; k < 5; k++) {
                    //반 비교
                    if(arr[i][k] == arr[j][k]) {
                        cnt++;
                        break;
                    }
                }
            }
            if(cnt > max) {
                cnt = max;
                answer = i;
            }
        }
        System.out.println(answer+1);
    }
}
