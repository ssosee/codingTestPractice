package programmers.level3;

import java.util.Arrays;

public class 단어_변환 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        //System.out.println(solution.solution("hit", "cog", new String[]{"cog", "log", "lot", "dog", "dot", "hot"}));
    }

    static class Solution {
        int answer = Integer.MAX_VALUE;
        public int solution(String begin, String target, String[] words) {

            // words에 target이 있는지 확인
            boolean flag = false;
            for(String word : words) {
                if(word.equals(target)) {
                    flag = true;
                }
            }
            // words에 target이 없으면 0 반환
            if(!flag) return 0;

            boolean[] visited = new boolean[words.length];
            // dfs 수행
            dfs(begin, target, 0, words, visited, begin);

            if(answer == Integer.MAX_VALUE) return 0;

            return answer;
        }

        private void dfs(String begin, String target, int depth, String[] words, boolean[] visited, String path) {

            // 첫 dfs에 begin과 target이 같으면 안됨
            if(depth != 0 && begin.equals(target)) {
                answer = Math.min(answer, depth);
                //System.out.println(path); // 탐색 경로를 볼 수 있다.
                return;
            }

            // words의 배열 갯수만큼 탐색
            for(int i = 0; i < words.length; i++) {
                // 다른 글자를 세기위한 카운트
                int count = 0;
                for(int k = 0; k < words[i].length(); k++) {
                    // 해당 위치에 글자가 1개만 다르면
                    // 나머지 글자는 위치가 같아야 한다. hot, hto는 다른 글자!!!!
                    if (begin.charAt(k) != words[i].charAt(k)) {
                        count++;
                    }
                }

                // 방문 이력이 없고, 글자 1개만 다르면
                if(!visited[i] && count == 1 && begin.length() == words[i].length()) {
                    // 방문
                    visited[i] = true;
                    // dfs 수행
                    dfs(words[i], target, depth + 1, words, visited, path +"->"+ words[i]);
                    // 방문 이력 초기화
                    visited[i] = false;
                }
            }
        }
    }
}
