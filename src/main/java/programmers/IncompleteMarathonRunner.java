package programmers;
import java.util.*;

public class IncompleteMarathonRunner {
    public static void main(String[] args) {

    }

    /***
     * 완주하지 못한 선수의 이름을 return
     */
    class Solution {
        public String solution(String[] participant, String[] completion) {
            String answer = "";
            int count = 0;
            Map<String, Integer> map = new HashMap<>();
            for (String players : participant) {
                map.put(players, map.getOrDefault(players, 0)+1);
            }

            for(String players : completion) {
                map.put(players, map.get(players)-1);
            }

            for (String s : participant) {
                if(map.get(s) == 1) {
                    answer = s;
                    break;
                }
            }

            return answer;
        }
    }
}
