package inflearn.hashandtree;

import java.util.*;

public class test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String v = sc.next();
        //System.out.println(new Solution().getNumber(n, v));
        System.out.println(new Solution().solution(n, v));
    }
    static class Solution {

        public char solution(int n, String v) {
            char answer = ' ';
            Map<Character, Integer> map = new HashMap<>();
            for(char x : v.toCharArray()) {
                map.put(x, map.getOrDefault(x, 0)+1);
            }
            int max = 0;
            for(char x : map.keySet()) {
                if(map.get(x) > max) {
                    max = map.get(x);
                    answer = x;
                }
            }
            return answer;
        }

        public String getNumber(int n, String v) {
            String answer = "";
            int[] r = new int[5];
            Map<String, Integer> map = new HashMap<String, Integer>() {{
                put("A", 0);
                put("B", 0);
                put("C", 0);
                put("D", 0);
                put("E", 0);
            }};

            for(int i = 0; i < n; i++) {
                String s = String.valueOf(v.charAt(i));
                if("A".equalsIgnoreCase(s)) {
                    map.put(s, map.get(s)+1);
                    r[0]++;
                } else if("B".equalsIgnoreCase(s)) {
                    map.put(s, map.get(s)+1);
                    r[1]++;
                } else if("C".equalsIgnoreCase(s)) {
                    map.put(s, map.get(s)+1);
                    r[2]++;
                } else if("D".equalsIgnoreCase(s)) {
                    map.put(s, map.get(s)+1);
                    r[3]++;
                } else if("E".equalsIgnoreCase(s)) {
                    map.put(s, map.get(s)+1);
                    r[4]++;
                }
            }

            for(int i = 0; i < 5; i++) {
                if(r[i] == Collections.max(map.values())) {
                    if(i == 0) answer = "A";
                    else if(i == 1) answer = "B";
                    else if(i == 2) answer = "C";
                    else if(i == 3) answer = "D";
                    else if(i == 4) answer = "E";
                }
            }

            return answer;
        }
    }
}
