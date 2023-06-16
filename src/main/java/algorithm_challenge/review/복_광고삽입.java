package algorithm_challenge.review;

public class 복_광고삽입 {

    public static void main(String[] args) {
        Solution s = new Solution();
        String result = s.solution("02:03:55",
                "00:14:15",
                new String[]{"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"});

        System.out.println(result);
    }

    static class Solution {
        public String solution(String play_time, String adv_time, String[] logs) {
            String answer = "";

            /**
             * 1. 시청자들의 누적시간(초)를 구한다.
             * 2. 광고시간의 누적시간을 계산한다.
             * 3. 시청자들의 누적시간에서 광고누적시간이 가장 많은 곳을 출력한다.
             */
            int playTime = toSec(play_time);
            int advTime = toSec(adv_time);

            // 시청자들의 전체 누적시간
            int[] accumTimeTable = new int[playTime];
            for(String time : logs) {
                String[] split = time.split("-");

                int start = toSec(split[0]);
                int end = toSec(split[1]);

                for(int i = start; i < end; i++) {
                    accumTimeTable[i]++;
                }
            }

            // 광고누적시간 초기화
            long accumAdvTime = 0;
            for(int i = 0; i < advTime; i++) {
                accumAdvTime++;
            }

            int startTime = 0;
            long max = Long.MIN_VALUE;
            for(int i = advTime; i < playTime; i++) {
                accumAdvTime += accumTimeTable[i] - accumTimeTable[i - advTime];
                if(accumAdvTime > max) {
                    max = accumAdvTime;
                    startTime = i - advTime + 1;
                }
            }

            answer = toTime(startTime);

            return answer;
        }

        public int toSec(String time) {
            // 01:30:59
            String[] split = time.split(":");
            String hour = split[0];
            String min = split[1];
            String second = split[2];

            int sumTime = 0;
            sumTime += Integer.parseInt(hour) * 3600;
            sumTime += Integer.parseInt(min) * 60;
            sumTime += Integer.parseInt(second);

            return sumTime;
        }

        public String toTime(int second) {
            int hour = second / 3600;
            int min = second % 3600 / 60;
            int sec = second % 60;

            return String.format("%02d:%02d:%02d", hour, min, sec);
        }
    }
}
