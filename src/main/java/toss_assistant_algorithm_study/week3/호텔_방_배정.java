package toss_assistant_algorithm_study.week3;

import java.util.*;

public class 호텔_방_배정 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(10, new long[]{1, 3, 4, 1, 3, 1}));
    }

    static class Solution {
        /**
         * 1. 한번에 한명씩 방 배정
         * 2. 고객이 원하는 방이 있으면 즉시 배정
         * 3. 고객이 원하는 방에 이미 사람이 있으면, 고객이 원하는 방 번호 보다 크고 빈방중 가장 번호가 작은 방 배정
         *
         * 아이디어
         * 시간 복잡도가 O(10^12)으로 매우 크다.
         * Map을 사용하여 다음 빈방번호에 바로 접근할 수 있도록 한다.
         *
         * 자료구조
         *  - Map<현재 방번호, 다음 방번호>
         *
         *  시간복잡도
         *  O(N)
         */
        static Map<Long, Long> roomMap;
        static List<Long> result;
        public long[] solution(long k, long[] room_number) {
            roomMap = new HashMap<>();
            result = new ArrayList<>();
            for(int i = 0; i < room_number.length; i++) {
                long emptyRoom = findEmptyRoom(room_number[i]);
                result.add(emptyRoom);
            }

            return result.stream().mapToLong(Long::longValue).toArray();
        }

        private long findEmptyRoom(long roomNumber) {
            // 빈방이면
            if(!roomMap.containsKey(roomNumber)) {
                roomMap.put(roomNumber, roomNumber + 1);
                return roomNumber;
            }
            // 빈방이 아니면
            Long nextRoomNumber = roomMap.get(roomNumber);
            long emptyRoomNumber = findEmptyRoom(nextRoomNumber);
            roomMap.put(roomNumber, nextRoomNumber);
            return emptyRoomNumber;
        }
    }
}
