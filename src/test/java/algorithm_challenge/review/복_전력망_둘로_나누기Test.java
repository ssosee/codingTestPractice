package algorithm_challenge.review;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class 복_전력망_둘로_나누기Test {

    private 복_전력망_둘로_나누기.Solution solution = new 복_전력망_둘로_나누기.Solution();

    @Test
    void 전력망_둘로_나누기() {
        System.out.println(solution.solution(9, new int[][]{{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}}));
    }
}