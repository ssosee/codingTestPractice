package inflearn.array;

/**
 * 멘토링
 */
import java.util.*;
public class test12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int count = sc.nextInt();
        int arr[][] = new int[num][count];
        for(int i = 0; i < num; i++) {
            for(int j = 0; j < count; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        Solution solution = new Solution();
        int result = solution.solution(num, count, arr);
        System.out.println(result);
    }
    static class Solution {

        public int solution(int num, int count, int[][] arr) {
            int answer = 0;
            int pi = 0;
            int pj = 0;
            //학생 번호
            for(int i = 1; i <= num; i++) {
                //학생 번호
                for(int j = 1; j <= num; j++) {
                    int cnt = 0;
                    //행(=테스트 케이스)
                    for(int k = 0; k < count; k++) {
                        //열(=등수)
                        for(int s = 0; s < num; s++) {
                            if(arr[k][s] == i) {
                                pi = s; //등수 삽입
                            }
                            else if(arr[k][s] == j) {
                                pj = s; //등수 삽입
                            }
                        }
                        if(pi < pj) {
                            cnt++;
                        }
                    }
                    //모든 케이스를 만족한다.
                    if(cnt == count) {
                        answer++;
                    }
                }
            }
            return answer;
        }

        /**
         * 다른 사람 풀이
         */
        public int solution2(int[][] arr, int student_size, int test_size) {
            int answer = 0;
            ArrayList<Integer> possible = new ArrayList<>();

            for (int i = 0; i < student_size; i++) {
                possible.clear();
                for(int init = 1; init < student_size+1; init++) possible.add(init); //학생 번호 삽입

                int my = i+1;
                for(int test = 0; test < test_size; test++) {
                    boolean islow = false;
                    for (int rank = 0; rank < student_size; rank++) {
                        if (!islow && possible.contains(arr[test][rank])) possible.remove(Integer.valueOf(arr[test][rank]));
                        if (arr[test][rank] == my) islow = true;
                    }
                }
                answer += possible.size();
            }

            return answer;
        }
    }
}
