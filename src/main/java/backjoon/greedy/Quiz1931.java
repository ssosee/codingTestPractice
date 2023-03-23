package backjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Quiz1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 회의 수
        int n = Integer.parseInt(br.readLine());

        /**
         * 각 회의 시간이 겹치면 안됨
         * 회의실 사용할 수 있는 최대 갯수
         */
        List<List<Integer>> table = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            table.add(new ArrayList<>());
        }
        // 회의 시간표 저장
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            table.get(i).add(start);
            table.get(i).add(end);
        }

        // 회의 종료 시간 기준으로 오름차순 정렬
        Collections.sort(table, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                // 종료시간이 같을 경우에는 회의 시작시간 기준으로 오름차순 정렬
                if(o1.get(1) == o2.get(1)) {
                    return Integer.compare(o1.get(0), o2.get(0));
                } else {
                    return Integer.compare(o1.get(1), o2.get(1));
                }
            }
        });

        // 첫 회의를 배정한다.
        // 첫 회의의 끝나는 시간과 다음 회의 시작 시간 비교
        int count = 1;
        // 선두 회의의 끝나는 시간
        int endTime = table.get(0).get(1);
        for(int i = 1; i < n; i++) {
            // 후발 회의의 시작 시간
            int startTime = table.get(i).get(0);
            if(endTime <= startTime) {
                count++;
                // 선두 회의의 끝나는 시간
                endTime = table.get(i).get(1);
            }
        }
        System.out.print(count);
        br.close();
    }
}
