package algorithm_challenge.programmers;


public class 광고_삽입 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String result = solution.solution("02:03:55",
                "00:14:15",
                new String[]{"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"});

        System.out.print(result);
    }

    static class Solution {
        /**
         * 1. 시간을 초로 변환
         * 2. 겹치는 시간을 구한다.
         * 3. 가장 많이 겹치는 구간의 시작 시간을 출력
         */
        public String solution(String play_time, String adv_time, String[] logs) {
            String answer = "";
            int playTime = toSec(play_time);
            int advTime = toSec(adv_time);
            int[] accumTimeTable = new int[playTime+1];

            for(String s : logs) {
                String[] time = s.split("-");
                String start = time[0];
                String end = time[1];

                int startTime = toSec(start);
                int endTime = toSec(end);

                // 전체 겹치는 시간 계산
                // 인덱스에 시간(초)를 넣는다.
                for(int i = startTime; i < endTime; i++) {
                    accumTimeTable[i]++;
                }
            }

            // 0부터 광고시간까지 광고 시청시간 계산
            // 일종의 초기화 과정
            long advAccumTime = 0;
            for(int i = 0; i < advTime; i++) {
                advAccumTime += accumTimeTable[i];
            }

            long maxSum = advAccumTime;
            int startTime = 0;
            for (int i = advTime; i < playTime; i++) {
                // 오른쪽 한 칸 이동
                // 왼쪽의 값  : accumTimeTable[i - advTime]
                // 오른쪽의 값 : accumTimeTable[i]
                advAccumTime = advAccumTime + accumTimeTable[i] - accumTimeTable[i - advTime];
                if(advAccumTime > maxSum) {
                    maxSum = advAccumTime;
                    startTime = i - advTime + 1; // 광고삽입 시작시간
                }
            }

            answer = toTime(startTime);

            return answer;
        }

        private String toTime(int startTime) {
            int hour = startTime / 3600;
            int min = startTime / 60 % 60;
            int sec = startTime % 60;

            return String.format("%02d:%02d:%02d", hour, min, sec);
        }

        public int toSec(String time) {
            // time = 01:20:15
            String[] splitTime = time.split(":");
            String hour = splitTime[0];
            String min = splitTime[1];
            String sec = splitTime[2];

            int second = 0;
            second += Integer.parseInt(hour) * 3600;
            second += Integer.parseInt(min) * 60;
            second += Integer.parseInt(sec);

            return second;
        }
    }
}
