package toss_assistant_algorithm_study.week2;

public class 광고_삽입 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String result = solution.solution("02:03:55",
                "00:14:15",
                new String[]{"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"});
        System.out.println(result);
    }

    static class Solution {
        public String solution(String play_time, String adv_time, String[] logs) {
            String answer = "";

            int playTimeToSec = createSec(play_time);
            int advTimeToSec = createSec(adv_time);
            int[] timeTable = new int[playTimeToSec + 1];

            for(String log : logs) {

                String[] split = log.split("-");
                int start = createSec(split[0]);
                int end = createSec(split[1]);

                for(int i = start; i < end; i++) {
                    timeTable[i]++;
                }
            }

            // 0부터 광고시간까지 광고 시청시간 계산
            // 일종의 초기화 과정
            int maxTime = 0;
            for(int i = 0; i < advTimeToSec; i++) {
                maxTime += timeTable[i];
            }

            // 광고시간 부터 영상전체시간 까지 계산
            long maxSum = maxTime;
            int startTime = 0;
            for (int i = advTimeToSec; i < playTimeToSec; i++) {
                // 오른쪽 한 칸 이동
                // 왼쪽의 값  : timeTable[i - advTimeToSec]
                // 오른쪽의 값 : timeTable[i]
                maxTime = maxTime + timeTable[i] - timeTable[i - advTimeToSec];
                if(maxTime > maxSum) {
                    maxSum = maxTime;
                    startTime = i - advTimeToSec + 1; // 광고삽입 시작시간
                }
            }

            answer = toTime(startTime);

            return answer;
        }

        private int createSec(String playTime) {
            String[] split = playTime.split(":");
            int hour = Integer.parseInt(split[0]);
            int minute = Integer.parseInt(split[1]);
            int sec = Integer.parseInt(split[2]);

            hour = hour * 3600;
            minute = minute * 60;

            return hour + minute + sec;
        }

        private String toTime(int startTime) {
            int hour = startTime / 3600;
            int min = startTime / 60 % 60;
            int sec = startTime % 60;

            return String.format("%02d:%02d:%02d", hour, min, sec);
        }
    }
}
