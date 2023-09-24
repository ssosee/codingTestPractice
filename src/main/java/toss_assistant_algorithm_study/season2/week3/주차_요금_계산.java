package toss_assistant_algorithm_study.season2.week3;

import java.util.*;

public class 주차_요금_계산 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(new int[]{180, 5000, 10, 600},
                new String[]{"05:34 5961 IN", "06:00 0000 IN",
                        "06:34 0000 OUT", "07:59 5961 OUT",
                        "07:59 0148 IN", "18:59 0000 IN",
                        "19:09 0148 OUT", "22:59 5961 IN",
                        "23:00 5961 OUT"})));
    }

    static class Solution {
        /**
         * 아이디어
         *  입차, 출차 기록이 있을 때, 차량별로 주차요금 계산
         *  기본 시간, 기본 요금, 단위 시간, 단위 요금
         *
         *  주차 요금 = 기본 요금 + [(주차 누적시간 - 기본시간) / 단위 시간] * 단위 요금
         *  입차 이후 출차가 이루어지지 않으면 누적 주차 시간은 음수이다~!
         *
         * 자료구조
         *  Map, Set, List
         *
         * 시간복잡도
         *  O(N)
         */
        public int[] solution(int[] fees, String[] records) {

            Set<String> carNumbers = new HashSet<>();
            Map<String, Integer> carTimeMap = new HashMap<>();
            for(int i = 0; i < records.length; i++) {
                String record = records[i];
                String[] split = record.split(" ");

                // 시간, 차량 번호, 상태
                String time = split[0];
                String carNumber = split[1];
                String status = split[2];

                // 시, 분
                String[] timeSplit = time.split(":");
                String hour = timeSplit[0];
                String min = timeSplit[1];

                carNumbers.add(carNumber);

                // 누적 주차시간 계산
                int calTime = (status.equals("IN") ? -1 : 1) * (Integer.parseInt(hour) * 60 + Integer.parseInt(min));
                carTimeMap.put(carNumber, carTimeMap.getOrDefault(carNumber, 0) + calTime);
            }

            // Set to List
            List<String> carNumberList = new ArrayList<>(carNumbers);
            // 오름차순 정렬
            Collections.sort(carNumberList);

            int basicMinute = fees[0]; // 기본 시간(분)
            int basicFee = fees[1]; // 기본 요금
            int unitMinute = fees[2]; // 단위 시간(분)
            int unitFee = fees[3]; // 단위 요금
            int endTime = 23 * 60 + 59; // 23:59

            List<Integer> carFees = new ArrayList<>();
            for(String carNumber : carNumberList) {
                // 누적 시간
                int time = carTimeMap.get(carNumber);

                // 중간에 출차가 없는 경우 23:59로 출차
                // 입차 이후 출차가 이루어지지 않으면 음수!
                if(time <= 0) time = time + endTime;

                // 기본시간 이하이면 기본요금만 계산
                if(time <= basicMinute) carFees.add(basicFee);
                else {
                    // 주차 요금 = 기본 요금 + [(주차 누적시간 - 기본시간) / 단위 시간] * 단위 요금
                    int totalFee = (int) (basicFee + (Math.ceil((double) (time - basicMinute) / unitMinute)) * unitFee);
                    carFees.add(totalFee);
                }
            }

            return carFees.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}
