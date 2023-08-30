package toss_assistant_algorithm_study.season2.week2;

public class 풍선_터트리기 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //System.out.println(solution.solution(new int[]{-16,27,65,-2,58,-92,-71,-68,-61,-33}));
        System.out.println(solution.solution(new int[]{9, -1, -5}));
    }

    static class Solution {
        /**
         * 아이디어
         *  풍선이 1개 남을때 까지 없앤다.
         *  인접한 풍선 2개를 고른다.
         *      1) 인접한 풍선 중 번호가 작은 풍선을 터트리는 행위는 최대 1번
         *      2) 이후에는 번호가 큰 풍선만 터트릴 수 잇음
         *  빈공간이 생기면, 빈공간이 없도록 재배열
         *  어떤 풍선이 최후에 남을 수 있는지?
         *  최후에 남을 수 있는 풍선 갯수 반환(불가능할 수도 있음)
         *
         *  [?, target, ?] target은 ?보다 커야 최후의 풍선이다.
         *  ?의 최악의 조건이 target 좌,우의 배열의 최솟값
         *  기본적으로 번호가 큰 풍선을 터트린다.
         *
         * 자료구조
         * int[]
         *
         * 시간복잡도
         *  N이 1,000,000 이기 때문에 O(N) 사용
         *
         */
        public int solution(int[] a) {
            int length = a.length;
            int[] lefts = new int[length];
            int[] rights = new int[length];
            int leftMin = Integer.MAX_VALUE;
            int rightMin = Integer.MAX_VALUE;

            // i를 기준으로 좌우 최소값 배열 설정
            for(int i = 0; i < length; i++) {
                leftMin = Math.min(leftMin, a[i]);
                rightMin = Math.min(rightMin, a[length - i - 1]);

                lefts[i] = leftMin;
                rights[length - i - 1] = rightMin;
            }

            int count = 0;
            for(int i = 0; i < length; i++) {
                int target = a[i];
                // target이 좌우 최솟값 보다 크면 최후의 풍선이 아님
                if(target > lefts[i] && target > rights[i]) count++;
            }

            // 최후의 풍선 갯수(전체 풍선 갯수 - 최후의 풍선이 아닌 풍선 갯수)
            return length - count;
        }
    }
}
