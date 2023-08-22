package toss_assistant_algorithm_study.season1.week3;

import java.util.ArrayList;
import java.util.List;

public class 표현_가능한_이진트리 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //solution.solution(new long[]{7, 42, 5});
        solution.solution(new long[]{5});
    }

    /**
     * 난이도
     * - 매우 어려움
     *
     * 아이디어
     * 1. 10진수를 2진수로 변환
     * 2. 포화 이진트리로 변환(2^n + 1개의 노드(n은 0부터))
     * 3. 가운데 0인지 확인(루트 노드가 0이면 자식노드는 0이어야 함)
     *
     * 시간복잡도
     * 1. O(N)
     *
     * 자료구조
     * 1. 배열
     * 2. 재귀
     */
    static class Solution {
        public int[] solution(long[] numbers) {
            List<Integer> result = new ArrayList<>();
            for(int i = 0; i < numbers.length; i++) {
                // 이진수로 변환
                String binaryString = Long.toBinaryString(numbers[i]);
                String fullBinary = createFullBinary(binaryString);

                boolean binaryTree = isBinaryTree(fullBinary);

                if(binaryTree) result.add(1);
                else result.add(0);
            }

            return result.stream().mapToInt(Integer::intValue).toArray();
        }

        // 포화이진트리 생성
        // 이진수 왼쪽에 0이 붙는지 안 붙는지 판별
        private String createFullBinary(String binaryString) {

            int length = binaryString.length();
            int nodeCount = 1;
            int level = 1;

            // 포화이진트리는 2^n + 1 개의 노드(n은 0부터)
            while (length > nodeCount) {
                level *= 2; // 2^n 개의 노드 추가
                nodeCount += level; // 포화 이진트리로 변환했을 때 노드 갯수
            }

            // 추가해야하는 '0' 갯수
            int addZeroCount = nodeCount - length;

            return "0".repeat(addZeroCount) + binaryString;
        }

        // 이진트리인지 확인
        // 루트 노드가 0이면 자식노드는 0이어야 한다.
        private boolean isBinaryTree(String binaryString) {
            int length = binaryString.length();
            if(length == 0) return true; // 리프노드인 경우

            int root = length / 2;
            String left = binaryString.substring(0, root);
            String right = binaryString.substring(root + 1);

            // 루트 노드가 0이면
            if(binaryString.charAt(root) == '0') {
                // 자식노드가 0 인지 확인
                return isZeroTree(left) && isZeroTree(right);
            }

            // 루트노드가 0이 아니면
            return isBinaryTree(left) && isBinaryTree(right);
        }

        // 해당 노드가 0인지 확인
        private boolean isZeroTree(String binaryString) {
            int length = binaryString.length();
            if(length == 0) return true;

            int root = length / 2;
            String left = binaryString.substring(0, root);
            String right = binaryString.substring(root + 1);

            // 루트노드가 1이면
            if(binaryString.charAt(root) == '1') {
                return false;
            }

            return isZeroTree(left) && isZeroTree(right);
        }
    }
}
