package programmers.level3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 네트워크 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
    }
    static class Solution {
        public int solution(int n, int[][] computers) {
            int answer = 0;

            // 대표 노드 초기화(노드는 1부터 시작)
            int[] parent = new int[n+1];
            for(int i = 1; i <= n; i++) {
                // 초기 대표 노드는 자기 자신
                parent[i] = i;
            }

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(i != j) {
                        if(computers[i][j] == 1) {
                            int node1 = i+1;
                            int node2 = j+1;
                            // 노드 끼리 연결 되어 있으면 유니온 연산
                            union(parent, node1, node2);
                        }
                    }
                }
            }

            // 대표노드가 같은 것을 제거하기 위해 Set에 저장
            Set<Integer> set = new HashSet<>();
            for(int i = 1; i <= n; i++) {
                set.add(find(parent, i));
            }

            answer = set.size();

            return answer;
        }

        // 유니온(서로 연결된 노드의 대표노드를 설정함)
        private void union(int[] parent, int node1, int node2) {
            // 대표 노드 찾기
            int ceoNode1 = find(parent, node1);
            int ceoNode2 = find(parent, node2);

            // 대표 노드가 다르면
            if(ceoNode1 != ceoNode2) {
                // 대표노드 값이 작은걸 대표노드로 설정
                if(ceoNode1 > ceoNode2) {
                    parent[ceoNode1] = ceoNode2;
                } else {
                    parent[ceoNode2] = ceoNode1;
                }
            }
        }

        // 파인드(대표노드를 찾는다)
        private int find(int[] parent, int node) {
            // 대표노드가 자신의 노드 이면
            if(parent[node] == node) {
                // 노드 반환(자기 자신이 대표노드이기 때문)
                return node;
            } else {
                // 대표 노드를 다시 찾는다
                return parent[node] = find(parent, parent[node]);
            }
        }
    }
}
