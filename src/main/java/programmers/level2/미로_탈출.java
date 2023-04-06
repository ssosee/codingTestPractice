package programmers.level2;

import java.util.*;

public class 미로_탈출 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new String[]{"SOOOO", "XXXXX", "LOOOO", "XXXXX", "EOOOO"}));
    }

    static class Solution {
        public int solution(String[] maps) {
            int answer = 0;
            int x = maps.length;
            int y = maps[0].length();

            // 출발점, 도착점
            int startX = 0;
            int startY = 0;
            int endX = 0;
            int endY = 0;
            int startXl = 0;
            int startYl = 0;
            // 2차원 배열로 미로 저장
            String[][] miro = new String[x][y];
            for(int i = 0; i < x; i++) {
                char[] chars = maps[i].toCharArray();
                for(int j = 0; j < y; j++) {
                    if(chars[j] == 'S') {
                        startX = i;
                        startY = j;
                    } else if (chars[j] == 'E') {
                        endX = i;
                        endY = j;
                    } else if(chars[j] == 'L') {
                        startXl = i;
                        startYl = j;
                    }
                    miro[i][j] = String.valueOf(chars[j]);
                }
            }

            // 인접 리스트 초기화
            List<Node>[][] list = new ArrayList[x][y];
            for(int i = 0; i < x; i++) {
                for(int j = 0; j < y; j++) {
                    list[i][j] = new ArrayList<>();
                }
            }

            // 그래프로 변환
            for(int i = 0; i < x; i++) {
                for(int j = 0; j < y; j++) {
                    if(!miro[i][j].equals("X")) {
                        list[i][j].add(new Node(i, j, 1));
                    }
                }
            }

            // 시작점에서 레버까지 최단경로
            int dijkstra1 = dijkstra(list, miro, startX, startY, startXl, startYl, x, y);
            if(dijkstra1 == Integer.MAX_VALUE) return -1;

            // 레버에서 도착점까지 최단경로
            int dijkstra2 = dijkstra(list, miro, startXl, startYl, endX, endY, x, y);
            if(dijkstra2 == Integer.MAX_VALUE) return -1;

            answer = dijkstra1 + dijkstra2;

            return answer;
        }

        private int dijkstra(List<Node>[][] list, String[][] miro, int startX, int startY, int endX, int endY, int x, int y) {
            Queue<Node> pq = new PriorityQueue<>(
                    (o1, o2) -> Integer.compare(o1.getWeight(), o2.getWeight())
            );

            // 최단거리 배열 초기화
            int[][] result = new int[x][y];
            for(int i = 0; i < x; i++) {
                for(int j = 0; j < y; j++) {
                    result[i][j] = Integer.MAX_VALUE;
                }
            }

            // 출발 지점 설정
            result[startX][startY] = 0;
            pq.add(new Node(startX, startY, result[startX][startY]));

            // 방문 이력 배열
            boolean[][] visited = new boolean[x][y];
            visited[startX][startY] = true;

            while (!pq.isEmpty()) {
                Node poll = pq.poll();
                // 상, 하, 좌, 우
                int[] ud = {1, -1, 0, 0};
                int[] lr = {0, 0, 1, -1};
                for(int i = 0; i < 4; i++) {
                    int newX = poll.getX() + ud[i];
                    int newY = poll.getY() + lr[i];

                    // 범위 체크
                    if(newX < 0 || newY < 0 || newX >= x || newY >= y) {
                        continue;
                    }

                    for(Node node : list[newX][newY]) {
                        // 방문 이력이 없고, 벽이 아니면
                        if(!visited[node.getX()][node.getY()] && !miro[node.getX()][node.getY()].equals("X")) {
                            // 최소 비용 계산
                            // min(선택한 노드(poll.X, poll.Y)의 최단거리 배열 값 + 탐색 대상(newX, newY)의 엣지 가중치, 탐색 대상(newX, newY)의 최단거리)
                            int min = Math.min(result[poll.getX()][poll.getY()] + node.getWeight(), result[node.getX()][node.getY()]);
                            result[node.getX()][node.getY()] = min;
                            // 방문 기록
                            visited[newX][newY] = true;
                            // 큐에 삽입
                            pq.add(new Node(newX, newY, min));
                        }
                    }
                }
            }
            return result[endX][endY];
        }
    }

    static class Node {
        private int x;
        private int y;
        private int weight;

        public Node(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWeight() {
            return weight;
        }
    }

}
