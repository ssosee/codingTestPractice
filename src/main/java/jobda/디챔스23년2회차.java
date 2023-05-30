package jobda;

import java.util.*;

import static jobda.디챔스23년2회차.Solution.solution;

public class 디챔스23년2회차 {
    static List<Integer> arr;
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution(new int[][]{
                {1,0,0,1,0,0,1},
                {1, 1, 1, 1, 1, 1, 0},
                {1, 0, 1, 1, 0, 1, 1},
                {1, 0, 1, 1, 0, 1, 1}
        })));
    }

    static class Solution {
        static public int[] solution(int[][] Parameter) {
            int[] answer = {};

            List<Integer>[] lists = initList();

            int[][] clone = Parameter.clone();
            arr = new ArrayList<>();
            dfs(clone, lists, 0, 0, 0);
            dfs(clone, lists, 0, 1, 0);
            dfs(clone, lists, 0, 2, 0);
            System.out.println(1);



            return answer;

        }

        static public int[] convertSegment(int[][] Parameter) {

            int[] result = new int[4];

            int[][] segment ={
                  //{A, B, C, D, E, F, G}
                    {1, 1, 1, 1, 1, 1, 0}, // 0
                    {0, 1, 1, 0, 0, 0, 0}, // 1
                    {1, 1, 0, 1, 1, 0, 1}, // 2
                    {1, 1, 1, 1, 0, 0, 1}, // 3
                    {0, 1, 1, 0, 0, 1, 1}, // 4
                    {1, 0, 1, 1, 0, 1, 1}, // 5
                    {0, 0, 1, 1, 1, 1, 1}, // 6
                    {1, 1, 1, 0, 0, 0, 0}, // 7
                    {1, 1, 1, 1, 1, 1, 1}, // 8
                    {1, 1, 1, 1, 0, 1, 1} // 9
            };

            // segment 2 number
            for(int i = 0; i < Parameter.length; i++) {
                for(int j = 0; j < segment.length; j++) {
                    if (Arrays.equals(Parameter[i], segment[j])) {
                        result[i] = j;
                        break;
                    }
                }
            }

            return result;
        }

        static public int checkSegment(int[] param) {
            int[][] segment ={
                    //{A, B, C, D, E, F, G}
                    {1, 1, 1, 1, 1, 1, 0}, // 0
                    {0, 1, 1, 0, 0, 0, 0}, // 1
                    {1, 1, 0, 1, 1, 0, 1}, // 2
                    {1, 1, 1, 1, 0, 0, 1}, // 3
                    {0, 1, 1, 0, 0, 1, 1}, // 4
                    {1, 0, 1, 1, 0, 1, 1}, // 5
                    {0, 0, 1, 1, 1, 1, 1}, // 6
                    {1, 1, 1, 0, 0, 0, 0}, // 7
                    {1, 1, 1, 1, 1, 1, 1}, // 8
                    {1, 1, 1, 1, 0, 1, 1} // 9
            };

            // segment 2 number
            for(int j = 0; j < segment.length; j++) {
                if(Arrays.equals(param, segment[j])) {
                    return j;
                }
            }

            return -1;
        }

        static void dfs(int[][] clone, List<Integer>[] list, int depth, int target, int index) {
            // 시간의 앞 숫자는 0, 1, 2만 가능
            // 앞 숫자가 0 이면 뒷 숫자 0 ~ 9 가능
            // 앞 숫자가 1 이면 뒷 숫자 0 ~ 9 가능
            // 앞 숫자 2 이면 뒷 숫자 0 ~ 3 가능
            // 분의 앞 숫자는 0 ~ 5 가능
            // 분의 뒷 숫자는 0 ~ 9 가능

            if(checkSegment(clone[index]) == target) {

                return;
            }

            if(depth == clone[index].length) {
                return;
            }

            for(int i = 0; i < clone[index].length; i++) {
                if(clone[index][i] != 1) {
                    clone[index][i] = 1;
                    dfs(clone, list, depth + 1, target, index);
                    clone[index][i] = 0;
                }
            }

        }
    }

    static class Node {
        private int start;
        private int end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }


    }

    static private List<Integer>[] initList() {
        // 인접 리스트 초기화
        List<Integer>[] list = new ArrayList[7];
        for(int i = 0; i < 7; i++) {
            list[i] = new ArrayList<>();
        }

        list[0].add(1);
        list[0].add(5);
        list[0].add(6);

        list[1].add(0);
        list[1].add(6);
        list[1].add(2);

        list[2].add(1);
        list[2].add(3);
        list[2].add(6);

        list[3].add(2);
        list[3].add(4);
        list[3].add(6);

        list[4].add(3);
        list[4].add(5);
        list[4].add(6);

        list[5].add(0);
        list[5].add(4);
        list[5].add(6);

        list[6].add(0);
        list[6].add(1);
        list[6].add(2);
        list[6].add(3);
        list[6].add(4);
        list[6].add(5);
        list[6].add(6);

        return list;
    }
}
