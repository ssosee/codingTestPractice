package toss_assistant_algorithm_study.season1.week1;

public class 호텔_대실 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new String[][]{
                {"15:00", "17:00"},
                {"16:40", "18:20"},
                {"14:20", "15:20"},
                {"14:10", "19:20"},
                {"18:20", "21:20"}
        }));
    }

    static class Solution {
        // 최소한의 객실만을 사용하여 예약 손님들을 받으려고 함
        // 코니에게 필요한 최소 객실 수 ?
        // 1번 사용한 객실은 퇴실 시간을 기준으로 10분간 청소를 한다.
        public int solution(String[][] book_time) {

            /**
             * 1. 시작시간 ~ 종료시간 + 10분 -> 분으로 변경
             * 2. 배열의 인덱스에 1초단위로 표현
             * 3. 겹치는 시간이 있으면 방 1개 증가
             */
            int roomNumber = 0;
            int[] timeTable = new int[1500]; // 24 * 60 + 59 + 1
            int cleanMin = 10;
            for(int i = 0; i < book_time.length; i++) {
                int startMin = calculateTimeToMin(book_time[i][0]);
                int endMin = calculateTimeToMin(book_time[i][1]);
                for(int j = startMin; j < endMin + cleanMin; j++) {
                    timeTable[j]++;
                    roomNumber = Math.max(timeTable[j], roomNumber);
                }
            }

            return roomNumber;
        }

        private int calculateTimeToMin(String time) {
            String[] startTimeSplits = time.split(":");
            String startHour = startTimeSplits[0];
            String startMin = startTimeSplits[1];

            int startHourInt = Integer.parseInt(startHour) * 60;
            int startMinInt = Integer.parseInt(startMin);

            return startHourInt + startMinInt;
        }
    }
}
