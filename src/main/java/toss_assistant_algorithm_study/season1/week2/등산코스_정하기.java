package toss_assistant_algorithm_study.season1.week2;

import java.util.*;

public class 등산코스_정하기 {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.solution(5,
//                new int[][]{{1, 3, 10}, {1, 4, 20}, {2, 3, 4}, {2, 4, 6}, {3, 5, 20}, {4, 5, 6}},
//                new int[]{1, 2},
//                new int[]{5});

        solution.solution(6,
                new int[][]{{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}},
                new int[]{1, 3},
                new int[]{5});
    }

    /**
     * https://tech.kakao.com/2022/07/13/2022-coding-test-summer-internship/
     *
     * 1. 아이디어
     *  양방향, 일정시간 소요, 등산 코스대로 이동(휴식도 가능)
     *  휴식 없이 이동하는 시간 중 가장 긴 시간을 해당 등산코드의 intensity 라고 함
     *  출입구에서 출발하여 산봉우리 중 한곳만 방문후 다시 원래의 출입구로 돌아오는 등산코를 정해야함
     *  출입구는 처음과 끝에 한번씩, 산봉우리는 1번만 포함
     *  이때 itentsity가 최소가 되도록
     *
     *  입구에서 정상까지 가는 경로가 최소이면 돌아오는 경로도 똑같이 최소인 경로로 돌아오면 되므로
     *  입구에서 산봉우리까지 가는 경우의 경로만 생각하시면 됩니다.
     *
     * 2. 시간복잡도
     *  O(V + E) -> O(50000 + 200000)
     * 3. 자료구조
     *  Queue 사용
     */
    static class Solution {

        static List<Mountain>[] nodes;
        static int[] minIntensity;
        public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

            // 인접리스트로 변환
            nodes = new ArrayList[n + 1];
            for(int i = 1; i <= n; i++) {
                nodes[i] = new ArrayList<>();
            }

            for(int i = 0; i < paths.length; i++) {
                int start = paths[i][0];
                int end = paths[i][1];
                int time = paths[i][2];

                // 시작점이 입구이거나, 끝점이 봉우리이면 단방향
                if(isGate(start, gates) || isSummit(end, summits)) {
                    nodes[start].add(new Mountain(end, time));
                }
                // 끝점이 입구이거나, 시작점이 산봉우리이면 단방향
                else if (isGate(end, gates) || isSummit(start, summits)) {
                    nodes[end].add(new Mountain(start, time));
                }
                // 시작점, 끝점이 일반 등산로 이면 양방향
                else {
                    nodes[start].add(new Mountain(end, time));
                    nodes[end].add(new Mountain(start, time));
                }
            }

            // 정상까지 갔을 때 최소이면 돌아올때도 같은 경로로 돌아오면 됨
            return dijkstra(n, gates, summits);
        }

        private int[] dijkstra(int n, int[] gates, int[] summits) {
            Queue<Mountain> queue = new ArrayDeque<>();
            minIntensity = new int[n + 1];
            Arrays.fill(minIntensity, Integer.MAX_VALUE);

            for(int gate : gates) {
                queue.offer(new Mountain(gate, 0));
                minIntensity[gate] = 0;
            }

            while (!queue.isEmpty()) {
                Mountain poll = queue.poll();
                int location = poll.location;

                for(Mountain m : nodes[location]) {
                    int newLocation = m.location;
                    int newTime = m.time;
                    // 휴식없이 이동해야하는 시간 중 가장 긴 시간
                    // intensity = max(출발위치의 최소비용, 도착위치의 비용)
                    int intensity = Math.max(minIntensity[location], newTime);

                    // 새로운 위치의 최소 intensity가 현재 intensity보다 크면
                    if(minIntensity[newLocation] > intensity) {
                        minIntensity[newLocation] = intensity;
                        queue.offer(new Mountain(newLocation, minIntensity[newLocation]));
                    }
                }
            }

            int summit = Integer.MAX_VALUE;
            int minTime = Integer.MAX_VALUE;

            Arrays.sort(summits);

            for(int sm : summits) {
                if(minIntensity[sm] < minTime) {
                    summit = sm;
                    minTime = minIntensity[sm];
                }
            }

            return new int[]{summit, minTime};
        }

        // num이 입구인지 확인
        private static boolean isGate(int num, int[] gates) {
            for (int gate : gates) {
                if (num == gate) return true;
            }
            return false;
        }

        // num이 산봉우리인지 확인
        private static boolean isSummit(int num, int[] submits) {
            for (int submit : submits) {
                if (num == submit) return true;
            }
            return false;
        }

        class Mountain {
            private int location;
            private int time;

            public Mountain(int location, int time) {
                this.location = location;
                this.time = time;
            }
        }
    }
}
