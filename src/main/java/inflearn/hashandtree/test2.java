package inflearn.hashandtree;

import java.util.*;

public class test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        System.out.println(new Solution().solution(s1, s2));
    }

    static class Solution {

        public String solution2(String s1, String s2) {
            Map<Character, Integer> map = new HashMap<>();

            for(char key : s1.toCharArray()) {
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            for(char x : s2.toCharArray()) {
                if(!map.containsKey(x) || map.get(x) == 0) {
                    return "NO";
                }
                map.put(x, map.get(x) - 1);
            }
            return "YSE";
        }

        public String solution(String s1, String s2) {
            Map<Character, Integer> map1 = new HashMap<>();
            Map<Character, Integer> map2 = new HashMap<>();

            for(char c : s1.toCharArray()) {
                map1.put(c, map1.getOrDefault(c, 0)+1);
            }
            for(char c : s2.toCharArray()) {
                map2.put(c, map2.getOrDefault(c, 0)+1);
            }
            for(char k : map1.keySet()) {
                if(!map1.get(k).equals(map2.get(k))) return "NO";
            }
            return "YES";
        }
    }
}
